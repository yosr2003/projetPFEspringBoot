package com.projetPfe.servicesImp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetPfe.Iservices.EtatDeclarationIservice;
import com.projetPfe.entities.DossierContratCommercial;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierEconomieSurSalaire;
import com.projetPfe.entities.DossierEmpreint;
import com.projetPfe.entities.DossierFormationProfessionnelle;
import com.projetPfe.entities.DossierInvestissement;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.entities.DossierSoinMedical;
import com.projetPfe.entities.EtatDeclarationBCT;
import com.projetPfe.entities.Participant;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertPermanent;
import com.projetPfe.entities.TransfertPonctuel;
import com.projetPfe.repositories.EtatDeclaraionBCTRepository;
import com.projetPfe.repositories.TransfertRepository;

@Service
public class EtatDeclarationBCTServiceImp implements EtatDeclarationIservice {
	@Autowired
	private EtatDeclaraionBCTRepository etatDecRepo;
	@Autowired
	private TransfertRepository transfertRepo;
	
	private String getTypeFromFirstTransfert(List<Transfert> transferts) {
	    if (transferts == null || transferts.isEmpty())
	        return null;

	    Transfert premier = transferts.get(0);

	    if (premier instanceof TransfertPonctuel) {
	        return ((TransfertPonctuel) premier).getTypeTransfert().toString(); 
	    } else if (premier instanceof TransfertPermanent) {
	        DossierDelegue dossier = ((TransfertPermanent) premier).getDossierDelegue();
	        if (dossier instanceof DossierSoinMedical) return "SOINS_MEDICAUX";
	        if (dossier instanceof DossierScolarité) return "SCOLARITE";
	        if (dossier instanceof DossierEmpreint) return "EMPREINT";
	        if (dossier instanceof DossierFormationProfessionnelle) return "FORMATION_PROFESSIONNELLE";
	        if (dossier instanceof DossierContratCommercial) return "COMMERCIAL";
	        if (dossier instanceof DossierInvestissement) return "IVESTISSMENT";
	        if (dossier instanceof DossierEconomieSurSalaire) return "ECONOMIE_SUR_SALAIRE";
	      
	    }
	    return null;
	}

	
	@Override
	public ResponseEntity<?> genererEtatDeclaration(String trimestre, String typeDeclaration) throws Exception {
		List<EtatDeclarationBCT> decalarations=etatDecRepo.findAll();
		EtatDeclarationBCT etat = new EtatDeclarationBCT(trimestre);
		// filtrer les transferts par trimestre
		List<Transfert> transferts = filtreTransfertsParTrimestre(trimestre,typeDeclaration);
		System.out.println("Transferts filtrés : ");
		transferts.forEach(t -> System.out.println(t));

		System.out.println("le type est  " +typeDeclaration);
		System.out.println("Nombre de transferts trouvés: " + transferts.size());
		for (EtatDeclarationBCT etatDeclarationBCT : decalarations) {
			List<Transfert>transfertsetats=etatDeclarationBCT.getTransferts();
				String typeExistnat=getTypeFromFirstTransfert(transferts);
				System.out.println(transfertsetats);
			    String typeNouveau=getTypeFromFirstTransfert(transfertsetats);
			    System.out.println(typeExistnat+" "+typeNouveau);
				if (typeExistnat != null && typeNouveau != null && typeExistnat.equals(typeNouveau) && etatDeclarationBCT.getTrimestre().equals(etat.getTrimestre())) {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body("Il existe déjà un rapport pour ce type de transferts et ce trimestre.");
					/*
					 * return ResponseEntity.ok() .header(HttpHeaders.CONTENT_DISPOSITION,
					 * "attachment; filename=etat_investissement.pdf")
					 * .contentType(MediaType.APPLICATION_PDF)
					 * .body(etatDeclarationBCT.getContenuPdf());
					 */
			}
		}

		StringBuilder xml = genererContenuXml(trimestre,transferts);
	       if(xml!=null) {
	           
	           String contenuXml = xml.toString();

        org.w3c.dom.Document xmlDoc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new ByteArrayInputStream(contenuXml.getBytes()));

        // Générer PDF en mémoire
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        com.itextpdf.text.Document pdfDoc = new com.itextpdf.text.Document();
        PdfWriter.getInstance(pdfDoc, outputStream);
        pdfDoc.open();

        pdfDoc.add(new Paragraph("ETAT DE DECLARATION TRIMESTRIEL DES TRANSFERTS DE TYPE "+typeDeclaration));
        pdfDoc.add(Chunk.NEWLINE);
      

        PdfPTable table = new PdfPTable(9); // 3 + 1 + 1 + 2 + 4 = 11 colonnes
        table.setWidthPercentage(100);
        boolean Personnemorale = transferts.stream()
        	    .map(t -> t.getCompteBancaire_source().getParticipant())
        	    .allMatch(p -> p instanceof PersonneMorale);
        addHeaderRow(table,Personnemorale);

        for (int i = 0; i < xmlDoc.getElementsByTagName("ligne").getLength(); i++) {
            Node ligne = xmlDoc.getElementsByTagName("ligne").item(i);
            if (ligne.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) ligne;
                addDataRow(table, elem);
            }
        
        }
        pdfDoc.add(table);
        pdfDoc.close();
        etat.setContenuPdf(outputStream.toByteArray());
        etat.setTransferts(transferts);
        for (Transfert transfert : transferts) {
            transfert.setEtatDeclaration(etat);
        }
        etatDecRepo.save(etat);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=etat_investissement.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(outputStream.toByteArray());}
	       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("il n'ya pas de transferts a mettre dans le rapport");
	}
	
	@Override
	public StringBuilder genererContenuXml(String trimestre,List<Transfert> transferts) {
		if (!transferts.isEmpty()) {

			   
			    StringBuilder xml = new StringBuilder();
			    xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			    xml.append("<etatDeclaration>\n");

			    for (Transfert transfert : transferts) {
			        xml.append("<ligne>\n");

			        Participant emmeteur = transfert.getCompteBancaire_source().getParticipant();
			        if (emmeteur instanceof PersonneMorale) {
			            xml.append("    <colonne1>").append(((PersonneMorale) emmeteur).getCodeDouane()).append("</colonne1>\n");
			            xml.append("    <colonne2>").append(((PersonneMorale) emmeteur).getRaisonSociale()).append("</colonne2>\n");
			        } else if(emmeteur instanceof PersonnePhysique){
			            xml.append("    <colonne1>").append(((PersonnePhysique) emmeteur).getCin()).append("</colonne1>\n");
			            xml.append("    <colonne2>").append(((PersonnePhysique) emmeteur).getNom()).append("</colonne2>\n");
			        }

		            xml.append("    <colonne3>").append(emmeteur.getAdresse()).append("</colonne3>\n");

			        xml.append("    <colonne6>").append(transfert.getMontantFinal()).append("</colonne6>\n");
			        xml.append("    <colonne7>").append(transfert.getMontantTransfert()).append("</colonne7>\n");
			        
			        if (transfert instanceof TransfertPermanent tp) {
			            xml.append("    <colonne8>").append(tp.getNatureOperation()).append("</colonne8>\n");
			        } else {
			            xml.append("    <colonne8>Non applicable</colonne8>\n");
			        }


			        Participant beneficiaire = transfert.getCompteBancaire_cible().getParticipant();
			        if (beneficiaire instanceof PersonneMorale) {
			            xml.append("    <colonne9>").append(((PersonneMorale) beneficiaire).getRaisonSociale()).append("</colonne9>\n");
			        } else if(beneficiaire instanceof PersonnePhysique){
			            xml.append("    <colonne9>").append(((PersonnePhysique) beneficiaire).getNom()).append("</colonne9>\n");
			        }

		            xml.append("    <colonne10>").append(transfert.getCompteBancaire_cible().getBanque().getPays().getCodePays()).append("</colonne10>\n");
		            xml.append("    <colonne11>").append(beneficiaire.getAdresse()).append("</colonne11>\n");
			        xml.append("</ligne>\n");
			    }

			    xml.append("</etatDeclaration>");

			    return xml;}
			return null;
	}
	private List<Transfert> filtreTransfertsParTrimestre(String trimestre,String typeDeclaration) {
	    LocalDate startDate, endDate;
	    int currentYear = LocalDate.now().getYear();

	    switch (trimestre.toUpperCase()) {
	        case "T1":
	            startDate = LocalDate.of(currentYear, 1, 1);
	            endDate = LocalDate.of(currentYear, 3, 31);
	            break;
	        case "T2":
	            startDate = LocalDate.of(currentYear, 4, 1);
	            endDate = LocalDate.of(currentYear, 6, 30);
	            break;
	        case "T3":
	            startDate = LocalDate.of(currentYear, 7, 1);
	            endDate = LocalDate.of(currentYear, 9, 30);
	            break;
	        case "T4":
	            startDate = LocalDate.of(currentYear, 10, 1);
	            endDate = LocalDate.of(currentYear, 12, 31);
	            break;
	        default:
	            throw new IllegalArgumentException("Trimestre invalide: " + trimestre);
	    }

	    return transfertRepo.findAll().stream()
	        .filter(t -> {
	        	if(t.getDateEnvoie()!=null) {
	            LocalDate dateEnvoie = t.getDateEnvoie().toLocalDate();
	            return !dateEnvoie.isBefore(startDate) && !dateEnvoie.isAfter(endDate);} return false;
	        })
	        
	        .filter(t -> {
	            if (t instanceof TransfertPonctuel tp) {
	                return typeDeclaration.equals(tp.getTypeTransfert().toString());
	                
	               
	            } 
	            else if (t instanceof TransfertPermanent tp){
	            	DossierDelegue dossier = tp.getDossierDelegue();
	            	 switch (typeDeclaration) {
	                 case "COMMERCIAL":
	                     return dossier instanceof DossierContratCommercial;
	                 case "ECONOMIE_SUR_SALAIRE":
	                     return dossier instanceof DossierEconomieSurSalaire;
	                 case "EMPREINT":
	                     return dossier instanceof DossierEmpreint;
	                 case "FORMATION_PROFESSIONNELLE":
	                     return dossier instanceof DossierFormationProfessionnelle;
	                 case "IVESTISSMENT":
	                     return dossier instanceof DossierInvestissement;
	                 case "SCOLARITE":
	                     return dossier instanceof DossierScolarité;
	                 case "SOINS_MEDICAUX":
	                     return dossier instanceof DossierSoinMedical;
	                 
	                 default:
	                     return false;
	             }
	            }
				return false;
	        })
	        .collect(Collectors.toList());
	    }
	private void addHeaderRow(PdfPTable table,boolean isPersonnemorale) {
		Font smallFont = new Font(Font.FontFamily.HELVETICA, 10);
        // Ligne 1 (en-têtes générales avec fusion de colonnes)
        PdfPCell cell1 = new PdfPCell(new Phrase("Identification de l’émmeteur"));
        cell1.setColspan(3);
        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell1);

        PdfPCell cell4 = new PdfPCell(new Phrase("Montant transféré"));
        cell4.setColspan(2);
        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell4);

        PdfPCell cell5 = new PdfPCell(new Phrase("Renseignement concernant le transfert à l’étranger"));
        cell5.setColspan(4);
        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell5);
        //ligne 2
        PdfPCell sc11 = new PdfPCell(new Phrase(isPersonnemorale ? "Code douane" : "CIN",smallFont));
        sc11.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc11);
        PdfPCell sc12 = new PdfPCell(new Phrase(isPersonnemorale ? "Raison Sociale" : "Nom",smallFont));
        sc12.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc12);
        PdfPCell sc13 = new PdfPCell(new Phrase("Adresse",smallFont));
        sc13.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc13);

        PdfPCell sc41 = new PdfPCell(new Phrase("Montant en devises",smallFont));
        sc41.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc41);
        PdfPCell sc42 = new PdfPCell(new Phrase("Contre-valeur en dinar",smallFont));
        sc42.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc42);
        
        PdfPCell sc51 = new PdfPCell(new Phrase("Nature d'Operation",smallFont));
        sc51.setRowspan(2); 
        table.addCell(sc51);

        PdfPCell sc52 = new PdfPCell(new Phrase(isPersonnemorale ? "Raison Sociale" : "Nom",smallFont));
        sc52.setRowspan(2);
        table.addCell(sc52);
        PdfPCell sc53 = new PdfPCell(new Phrase("Lieu d’implantation"));
        sc53.setColspan(2); // va être divisé en 2 colonnes ligne 3
        table.addCell(sc53);
        // Ligne 3
        PdfPCell cc1 = new PdfPCell(new Phrase("Code Pays", smallFont));
        table.addCell(cc1);

        PdfPCell cc2 = new PdfPCell(new Phrase("Adresse", smallFont));
        table.addCell(cc2);
        }
	
	private void addDataRow(PdfPTable table, Element elem) {
	table.addCell(elem.getElementsByTagName("colonne1").item(0).getTextContent());
    table.addCell(elem.getElementsByTagName("colonne2").item(0).getTextContent());
    table.addCell(elem.getElementsByTagName("colonne3").item(0).getTextContent());
   
    
    table.addCell(elem.getElementsByTagName("colonne6").item(0).getTextContent());
    table.addCell(elem.getElementsByTagName("colonne7").item(0).getTextContent());
   

    table.addCell(elem.getElementsByTagName("colonne8").item(0).getTextContent());
    table.addCell(elem.getElementsByTagName("colonne9").item(0).getTextContent());
    table.addCell(elem.getElementsByTagName("colonne10").item(0).getTextContent());
    table.addCell(elem.getElementsByTagName("colonne11").item(0).getTextContent());
    }
	
	
	@Override
	public ResponseEntity<?> getEtatDeclarationParTypeEtTrimestre(String trimestre, String typeDeclaration) {
	    List<EtatDeclarationBCT> declarations = etatDecRepo.findAll();

	    for (EtatDeclarationBCT etat : declarations) {
	        List<Transfert> transferts = etat.getTransferts();
	        if (transferts == null || transferts.isEmpty()) continue;

	        String typeTransfertExistant = getTypeFromFirstTransfert(transferts);

	        if (typeTransfertExistant != null &&
	            typeTransfertExistant.equalsIgnoreCase(typeDeclaration) &&
	            etat.getTrimestre().equalsIgnoreCase(trimestre)) {

	 
	            return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=etat_declaration_" + trimestre + ".pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(etat.getContenuPdf());
	        }
	    }

	    return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("Aucun état de déclaration trouvé pour ce type et ce trimestre.");
	}


}
