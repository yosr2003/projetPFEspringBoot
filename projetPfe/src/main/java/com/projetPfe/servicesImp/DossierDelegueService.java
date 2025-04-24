package com.projetPfe.servicesImp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
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
import com.projetPfe.Iservices.IserviceDossierDelegue;
import com.projetPfe.dto.ResponseBodyDTO;
import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Participant;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertPermanent;
import com.projetPfe.repositories.*;
@Service
public class DossierDelegueService implements IserviceDossierDelegue{
	  @Autowired
	    private dossierDelegueRepository dossierDelegueRepo;
	  @Autowired
	    private TransfertRepository TransfertRepository;
	  @Autowired
	    private TransfertPermanentRepository transfertPermanent;
	@Override
	public ResponseEntity<Map<String, Object>> dupliquerDossier(String id) {
	    Optional<DossierDelegue> optionalDossier = dossierDelegueRepo.findById(id);

	    if (optionalDossier.isPresent()) {
	        DossierDelegue dossier = optionalDossier.get();

	        if (dossier.getEtatDossier().equals(EtatDoss.VALIDE)) {
	            String newId = genererIdentifiantUnique("DOS");

	            // Utilisation du polymorphisme
	            DossierDelegue copie = dossier.dupliquerAvecNouveauId(newId);
	            dossierDelegueRepo.save(copie);

	            Map<String, Object> response = new HashMap<>();
	            ResponseHeaderDTO header = new ResponseHeaderDTO(200, "SERVICE_OK", "dupliqué avec succès");
	            response.put("header", header);

	            ResponseBodyDTO body = new ResponseBodyDTO(newId);
	            response.put("body", body);

	            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        } else {
	            ResponseHeaderDTO header = new ResponseHeaderDTO(400, "BAD_REQUEST", "Échec : le dossier n'est pas validé");
	            Map<String, Object> response = new HashMap<>();
	            response.put("header", header);
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }
	    }

	    ResponseHeaderDTO header = new ResponseHeaderDTO(404, "NOT_FOUND", "Dossier non trouvé");
	    Map<String, Object> response = new HashMap<>();
	    response.put("header", header);
	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	
	
	private String genererIdentifiantUnique(String prefix) {
	    String newId;
	    Random random = new Random();

	    do {
	        String randomDigits = String.format("%08d", random.nextInt(100_000_000));
	        newId = prefix + randomDigits;
	    } while (dossierDelegueRepo.existsById(newId));

	    return newId;
	}



	@Override
	public ResponseEntity<?> getAllDossiers() {
	    List<DossierDelegue> dossiers = dossierDelegueRepo.findAll();

	    if (dossiers.isEmpty()) {
	        Map<String, Object> response = new HashMap<>();
	        ResponseHeaderDTO header = new ResponseHeaderDTO(204, "NO_CONTENT", "Aucun dossier trouvé");
	        response.put("header", header);
	        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	    }

	    Map<String, Object> response = new HashMap<>();
	    ResponseHeaderDTO header = new ResponseHeaderDTO(200, "SERVICE_OK", "Liste des dossiers récupérée avec succès");
	    response.put("header", header);
	    response.put("body", dossiers);  // ou tu peux envelopper dans un DTO si tu préfères

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}




	@Override
	public ResponseEntity<Map<String, Object>> cloturerDossier(String id, LocalDate dateCloture, String motifcloture) {
	    Map<String, Object> response = new HashMap<>();

	    Optional<DossierDelegue> optionalDossier = dossierDelegueRepo.findById(id);
	    if (optionalDossier.isEmpty()) {
	        response.put("header", new ResponseHeaderDTO(404, "NOT_FOUND", "Dossier non trouvé"));
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    DossierDelegue dossier = optionalDossier.get();

	    if (!dossier.getEtatDossier().equals(EtatDoss.VALIDE)) {
	        response.put("header", new ResponseHeaderDTO(400, "BAD_REQUEST", "Le dossier n'est pas validé"));
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    if (dateCloture != null && dateCloture.isAfter(dossier.getDateExpiration())) {
	        response.put("header", new ResponseHeaderDTO(400, "BAD_REQUEST", "La date de clôture dépasse la date d'expiration"));
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    dossier.setDateCloture(dateCloture);
	    dossier.setMotifcloture(motifcloture);
	    dossier.setEtatDossier(EtatDoss.CLOTURE);

	    dossierDelegueRepo.save(dossier);

	    response.put("header", new ResponseHeaderDTO(200, "SERVICE_OK", "Clôturé avec succès"));
	    response.put("body", new ResponseBodyDTO(dossier));
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}



	
	@Override
	public ResponseEntity<?> getDossierById(String id) {
		Optional<DossierDelegue> d= dossierDelegueRepo.findById(id);
		if(d.isPresent()) {
			return ResponseEntity.ok().body(d.get());}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dossier non trouvé");
	}

/*----------------------------------------------------------------------------------------------*/
	
	@Override
	public ResponseEntity<?> genererRapportMouvement(String idDossier) throws Exception {
		List<TransfertPermanent> transferts = transfertPermanent.findByDossierDelegue_IdDossier(idDossier);

		if (transferts.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        .body("Ce Dossier Délégué ne contient pas encore de transferts permanents");
		}

	    Optional<DossierDelegue> d = dossierDelegueRepo.findById(idDossier);
	    if (!d.isPresent()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce Dossier Délégué n'existe pas");
	    }

	    DossierDelegue dossier = d.get();

	    if (dossier.getRapportMouvement() != null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ce Dossier Délégué a déjà un rapport de mouvement financier");
	    }

	    if ((dossier.getDateCloture() != null && LocalDate.now().isBefore(dossier.getDateCloture()))
	        || (dossier.getDateCloture() == null && LocalDate.now().isBefore(dossier.getDateExpiration()))) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ce dossier n'est pas encore clôturé");
	    }


	    StringBuilder xml = genererContenuXml(transferts.stream()
	    	    .map(t -> (Transfert) t)
	    	    .collect(Collectors.toList()));

	    org.w3c.dom.Document xmlDoc = DocumentBuilderFactory.newInstance()
	            .newDocumentBuilder()
	            .parse(new ByteArrayInputStream(xml.toString().getBytes()));

	    boolean isPersonneMorale = transferts.stream()
	            .map(t -> t.getCompteBancaire_source().getParticipant())
	            .allMatch(p -> p instanceof PersonneMorale);

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    com.itextpdf.text.Document pdfDoc = new com.itextpdf.text.Document();
	    PdfWriter.getInstance(pdfDoc, outputStream);
	    pdfDoc.open();

	    pdfDoc.add(new Paragraph("RAPPORT DES MOUVEMENTS FINANCIERS D'UN DOSSIER DELEGUE"));
	    pdfDoc.add(Chunk.NEWLINE);


	    dossier.ajouterInfosSpecifiquesAuRapport(pdfDoc);

	    pdfDoc.add(new Paragraph("Période allant du " +
	            dossier.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
	            " au " + dossier.getDateExpiration().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
	    pdfDoc.add(new Paragraph("N° Dossier : " + dossier.getIdDossier()));
	    pdfDoc.add(new Paragraph("Pays du bénéficiaire : " +
	            transferts.get(0).getCompteBancaire_cible().getBanque().getPays()));
	    pdfDoc.add(Chunk.NEWLINE);

	    PdfPTable table = new PdfPTable(6);
	    table.setWidthPercentage(100);
	    addHeaderRow(table, isPersonneMorale);

	    for (int i = 0; i < xmlDoc.getElementsByTagName("ligne").getLength(); i++) {
	        Node ligne = xmlDoc.getElementsByTagName("ligne").item(i);
	        if (ligne.getNodeType() == Node.ELEMENT_NODE) {
	            addDataRow(table, (Element) ligne);
	        }
	    }

	    pdfDoc.add(table);
	    pdfDoc.close();

	    dossier.setRapportMouvement(outputStream.toByteArray());
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
          table.addCell("Date");
          table.addCell("Nature d'operation");
          table.addCell("Monatant DT");
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




}
