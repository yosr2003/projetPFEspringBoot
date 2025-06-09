package com.projetPfe.servicesImp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetPfe.Iservices.IRapportMvmntFinanciersService;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.Participant;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.RapportMouvementsFinanciers;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertPermanent;
import com.projetPfe.repositories.RapportMouvementFinanciersRepository;
import com.projetPfe.repositories.TransfertPermanentRepository;
import com.projetPfe.repositories.dossierDelegueRepository;

@Service
public class RapportMvmntFinanciersService implements IRapportMvmntFinanciersService{
   @Autowired
   private dossierDelegueRepository dossierDelegueRepo;
   @Autowired 
   private TransfertPermanentRepository transfertPermanentRepo;
   @Autowired
   private RapportMouvementFinanciersRepository rapportRepo;
	
	@Override
	public ResponseEntity<?> genererRapportMouvement(String idDossier) throws Exception {
		// Vérifie si le dossier délégué existe
		Optional<DossierDelegue> d = dossierDelegueRepo.findById(idDossier);
	    if (!d.isPresent()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce Dossier Délégué n'existe pas");
	    }
	    // Récupère les transferts permanents liés à ce dossier
		List<TransfertPermanent> transferts = transfertPermanentRepo.findAll();
		transferts = transferts.stream()
		        .filter(t -> t.getDossierDelegue() != null)
		        .filter(t -> idDossier.equals(t.getDossierDelegue().getIdDossier()))
		        .collect(Collectors.toList());
		// Si aucun transfert trouvé, on ne peut pas générer de rapport
		if (transferts.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        .body("Ce Dossier Délégué ne contient pas encore de transferts permanents");
		}

	    DossierDelegue dossier = d.get();
	    // Vérifie si un rapport a déjà été généré
	    if (dossier.getRapportMouvementFinanciers() != null) {
	       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ce Dossier Délégué a déjà un rapport de mouvement financier");
	    }
	    // Vérifie si le dossier est clôturé ou non
	    if ((dossier.getDateCloture() != null && LocalDate.now().isBefore(dossier.getDateCloture()))
	        || (dossier.getDateCloture() == null && LocalDate.now().isBefore(dossier.getDateExpiration()))) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ce dossier n'est pas encore clôturé");
	    }

	    // Génère le contenu XML du PDF relative aux transferts 
	    StringBuilder xml = genererContenuXml(transferts.stream().map(t -> (Transfert) t)
	    	    .collect(Collectors.toList()));
	    // Prépare le document PDF avec iText
	    org.w3c.dom.Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
	            .parse(new ByteArrayInputStream(xml.toString().getBytes()));
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    com.itextpdf.text.Document pdfDoc = new com.itextpdf.text.Document();
	    PdfWriter.getInstance(pdfDoc, outputStream);
	    
	    // Ajoute les infos spécifiques au dossier dans le PDF
	    pdfDoc.open();
	    pdfDoc.add(new Paragraph("RAPPORT DES MOUVEMENTS FINANCIERS D'UN DOSSIER DELEGUE"));
	    pdfDoc.add(Chunk.NEWLINE);
	    dossier.ajouterInfosSpecifiquesAuRapport(pdfDoc);
	    pdfDoc.add(new Paragraph("Période allant du " + dossier.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
	               " au " + dossier.getDateExpiration().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
	    pdfDoc.add(new Paragraph("N° Dossier : " + dossier.getIdDossier()));
	    pdfDoc.add(new Paragraph("Pays du bénéficiaire : " + transferts.get(0).getCompteBancaire_cible().getBanque().getPays().getPays()));
	    pdfDoc.add(Chunk.NEWLINE);
	    
	    
	    
	    // preparer le tableau de transferts dans le PDF
	    PdfPTable table = new PdfPTable(6);
	    table.setWidthPercentage(100);
	    
	    boolean isPersonneMorale = transferts.stream().map(t -> t.getCompteBancaire_source().getParticipant())
	    		.allMatch(p -> p instanceof PersonneMorale);
	    
	    addHeaderRow(table, isPersonneMorale);
	    
	    for (int i = 0; i < xmlDoc.getElementsByTagName("ligne").getLength(); i++) {
	        Node ligne = xmlDoc.getElementsByTagName("ligne").item(i);
	        if (ligne.getNodeType() == Node.ELEMENT_NODE) {
	            addDataRow(table, (Element) ligne);
	            }
	        }
	    
	    pdfDoc.add(table);
	    pdfDoc.close();
	    
	    // Sauvegarder lu rapport en base de données et retourne le PDF généré en pièce jointe
        RapportMouvementsFinanciers r= new RapportMouvementsFinanciers();
        r.setDossier(dossier);
        r.setRapportMouvement(outputStream.toByteArray());
	    rapportRepo.save(r);
	    dossier.setRapportMouvementFinanciers(r);
	    dossierDelegueRepo.save(dossier);

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=etat_investissement.pdf")
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(outputStream.toByteArray());
	}

	
	private StringBuilder genererContenuXml(List<? extends Transfert> transferts) {
	    StringBuilder xml = new StringBuilder();
	    xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	    xml.append("<etatDeclaration>\n");

	    for (Transfert transfert : transferts) {
	        xml.append("<ligne>\n");

	        xml.append("    <colonne1>").append(transfert.getDatecre().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("</colonne1>\n");

	        if (transfert instanceof TransfertPermanent) {
	            TransfertPermanent tp = (TransfertPermanent) transfert;
	            xml.append("    <colonne2>").append(tp.getNatureOperation()).append("</colonne2>\n");
	        } else {
	            xml.append("    <colonne2>").append("N/A").append("</colonne2>\n"); 
	        }

	        xml.append("    <colonne3>").append(transfert.getMontantTransfert()).append("</colonne3>\n");
	        xml.append("    <colonne4>").append(transfert.getCompteBancaire_source().getNumeroCompte()).append("</colonne4>\n");

	        Participant emetteur = transfert.getCompteBancaire_source().getParticipant();
	        if (emetteur instanceof PersonneMorale) {
	            xml.append("    <colonne5>").append(((PersonneMorale) emetteur).getRaisonSociale()).append("</colonne5>\n");
	        } else if (emetteur instanceof PersonnePhysique) {
	            xml.append("    <colonne5>").append(((PersonnePhysique) emetteur).getNom()).append(" ")
	               .append(((PersonnePhysique) emetteur).getPrenom()).append("</colonne5>\n");
	        }

	        Participant beneficiaire = transfert.getCompteBancaire_cible().getParticipant();
	        if (beneficiaire instanceof PersonneMorale) {
	            xml.append("    <colonne6>").append(((PersonneMorale) beneficiaire).getRaisonSociale()).append("</colonne6>\n");
	        } else if (beneficiaire instanceof PersonnePhysique) {
	            xml.append("    <colonne6>").append(((PersonnePhysique) beneficiaire).getNom()).append(" ")
	               .append(((PersonnePhysique) beneficiaire).getPrenom()).append("</colonne6>\n");
	        }

	        xml.append("</ligne>\n");
	    }

	    xml.append("</etatDeclaration>");

	    return xml;
	}

        private void addHeaderRow(PdfPTable table,boolean isPersonnemorale) {
          table.addCell("Date création");
          table.addCell("Nature d'operation");
          table.addCell("Montant DT");
          table.addCell("N° compte donneur d'ordre");
          table.addCell(isPersonnemorale ? "Raison Sociale" : "Nom et prénom du donneur d'ordre");
          table.addCell("Bénéficiaire");
        
	  }
        private void addDataRow(PdfPTable table, Element elem) {
    		table.addCell(elem.getElementsByTagName("colonne1").item(0).getTextContent());
            table.addCell(elem.getElementsByTagName("colonne2").item(0).getTextContent());
            table.addCell(elem.getElementsByTagName("colonne3").item(0).getTextContent());
            table.addCell(elem.getElementsByTagName("colonne4").item(0).getTextContent());
            table.addCell(elem.getElementsByTagName("colonne5").item(0).getTextContent());
            table.addCell(elem.getElementsByTagName("colonne6").item(0).getTextContent());
    	}
        
        @Override
        public ResponseEntity<?> consulterRapportMouvement(String idDossier) {
            Optional<DossierDelegue> dossierOpt = dossierDelegueRepo.findById(idDossier);

            if (!dossierOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce Dossier Délégué n'existe pas.");
            }

            DossierDelegue dossier = dossierOpt.get();
            RapportMouvementsFinanciers rapport = dossier.getRapportMouvementFinanciers();

            if (rapport == null || rapport.getRapportMouvement() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun rapport de mouvement n’a été généré pour ce dossier.");
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapport_mouvement_" + idDossier + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(rapport.getRapportMouvement());
        }

        

}
