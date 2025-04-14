package com.projetPfe.implService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetPfe.Iservice.IDossierDelegueService;
import com.projetPfe.dto.ResponseBodyDTO;
import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.DossierDelegue;
//import com.projetPfe.entities.EtatDeclarationBCT;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Participant;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.Transfert;
import com.projetPfe.repositories.DossierDelegueRepository;
import com.projetPfe.repositories.TransfertRepository;


@Service
public class DossierDelegueServiceImpl implements IDossierDelegueService{
    ///////////////////////////////////////////////test
	@Autowired
	private DossierDelegueRepository dossierDelegueRepo;
	@Autowired
	private TransfertRepository transfertRepo;
	
   
	
	@Override
	public Optional<DossierDelegue> getDossierById(String id) {
        return dossierDelegueRepo.findById(id);
    }
	@Override
	public ResponseEntity<Map<String, Object>> cloturerDossier(DossierDelegue d,String id) {
		// Création de la réponse avec header et body
        Map<String, Object> response = new HashMap<>();
        ResponseHeaderDTO header = new ResponseHeaderDTO(404, "NOT_FOUND", "Dossier non trouvé");
        response.put("header", header);
		if(dossierDelegueRepo.findById(id).isPresent()) {
			DossierDelegue dossier=dossierDelegueRepo.findById(id).get();
			if(d.getDatclo().toLocalDate().isAfter(dossier.getDateExpiration())) {
				header = new ResponseHeaderDTO(400, "BAD_REQUEST", "cette date de clôture dépasse la date d'expiration du dossier");
	            response.put("header", header);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			if (dossier.getEtatDoss().equals(EtatDoss.VALIDE)) {
				dossier.setDatclo(d.getDatclo());
				dossier.setMotifclo(d.getMotifclo());
				dossier.setEtatDoss(EtatDoss.CLOTURE);
				
				dossierDelegueRepo.save(dossier);
				header = new ResponseHeaderDTO(200, "SERVICE_OK", "clôturé avec succès");
	            response.put("header", header);
	            ResponseBodyDTO body = new ResponseBodyDTO(dossier);
	            response.put("body", body);
	            return new ResponseEntity<>(response,HttpStatus.OK);
			}else {
				header = new ResponseHeaderDTO(400, "BAD_REQUEST", "dossier non validé");
	            response.put("header", header);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
		}
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
//	@Override
//	public ResponseEntity<Response<DossierDelegue>> clotureDossier(DossierDelegue d, String id) {
//		if(dossierDelegueRepo.findById(id).isPresent()) {
//			DossierDelegue dossier=dossierDelegueRepo.findById(id).get();
//			if (dossier.getEtatDoss().equals(EtatDoss.Validé)) {
//				dossier.setDatclo(d.getDatclo());
//				dossier.setMotifclo(d.getMotifclo());
//				dossier.setEtatDoss(EtatDoss.Clôturé);
//				HttpHeaders headers = new HttpHeaders();
//				headers.add("code",String.valueOf(HttpStatus.OK.value()));
//				headers.add("libelle","SERVICE_OK");
//				return ResponseEntity.ok(new Response<>(200, "SERVICE_OK", dossier));
//				
//			}else { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response<>(401, "UNAUTHORIZED", null));}
//		}
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(500, "INTERNAL_SERVER_ERROR", null));
//	}
	
	@Override
	public ResponseEntity<Map<String, Object>> dupliquerDossier(String id) {
	    Optional<DossierDelegue> optionalDossier = dossierDelegueRepo.findById(id);

	    if (optionalDossier.isPresent()) {
	        DossierDelegue dossier = optionalDossier.get();

	        if (dossier.getEtatDoss().equals(EtatDoss.VALIDE)) {
	            String newId = genererIdentifiantUnique("DOS");
	            DossierDelegue copie = new DossierDelegue();
	            copie.setIdDossier(newId);
	            copie.setDateDebut(dossier.getDateDebut());
	            copie.setEtatDoss(dossier.getEtatDoss()); 
	            copie.setDateExpiration(dossier.getDateExpiration());
	            copie.setAnneedoss(dossier.getAnneedoss());
	            copie.setType(dossier.getType());
	            copie.setSolde(dossier.getSolde());
	            copie.setDateCre(LocalDateTime.now()); 
	            copie.setDateFinProlong(dossier.getDateFinProlong());
	            copie.setMotifProlong(dossier.getMotifProlong());

	            dossierDelegueRepo.save(copie);

	            // Création de la réponse avec header et body
	            Map<String, Object> response = new HashMap<>();

	            // Créer le header
	            ResponseHeaderDTO header = new ResponseHeaderDTO(200, "SERVICE_OK", "dupliqué avec succès");
	            response.put("header", header);

	            // Créer le body
	            ResponseBodyDTO body = new ResponseBodyDTO(newId);
	            response.put("body", body);

	            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        } else {
	            // Dossier non validé
	            ResponseHeaderDTO header = new ResponseHeaderDTO(400, "BAD_REQUEST", "Échec : le dossier n'est pas validé");
	            Map<String, Object> response = new HashMap<>();
	            response.put("header", header);
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }
	    }

	    // Dossier non trouvé
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
	public ResponseEntity<?> genererRapportMouvement(String idDossier) throws Exception {
		Optional<DossierDelegue> d=dossierDelegueRepo.findById(idDossier);
		if(d.isPresent()) {
			if(d.get().getRapportMouvement()!=null) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ce Dossier Délégué a déja un rapport de mouvement financiers");}
			if ((d.get().getDatclo()!=null && LocalDate.now().isBefore(d.get().getDatclo().toLocalDate())) || (d.get().getDatclo()==null && LocalDate.now().isBefore(d.get().getDateExpiration()))) {
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			        .body("Ce dossier n'est pas encore clôturer");
			}
			DossierDelegue dossier=d.get();
			List<Transfert> transferts = transfertRepo.findAll();
			transferts = transferts.stream()
			        .filter(t -> t.getDossierDelegue() != null)
			        .filter(t -> idDossier.equals(t.getDossierDelegue().getIdDossier()))
			        .collect(Collectors.toList());
			StringBuilder xml = genererContenuXml(transferts);
	        String contenuXml = xml.toString();

        org.w3c.dom.Document xmlDoc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new ByteArrayInputStream(contenuXml.getBytes()));
        
        boolean Personnemorale = transferts.stream()
        	    .map(t -> t.getCompteBancaire_source().getParticipant())
        	    .allMatch(p -> p instanceof PersonneMorale);
        // Générer PDF en mémoire
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        com.itextpdf.text.Document pdfDoc = new com.itextpdf.text.Document();
        PdfWriter.getInstance(pdfDoc, outputStream);
        pdfDoc.open();

        pdfDoc.add(new Paragraph("RAPPORT DES MOUVEMENTS FINANCIERS D'UN DOSSIER DELEGUE DE  "+d.get().getType().toString()));
        pdfDoc.add(Chunk.NEWLINE);
     // Informations du dossier - format libre, aligné à gauche
        pdfDoc.add(new Paragraph("Période allant du "+dossier.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+" au: "+dossier.getDateExpiration().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))); // Adapter dynamiquement si besoin
        pdfDoc.add(new Paragraph("N° Dossier : " + dossier.getIdDossier()));
        pdfDoc.add(new Paragraph("Pays du bénéficiaire: " + transferts.get(0).getCompteBancaire_cible().getPays()));
        pdfDoc.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        
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
		dossier.setRapportMouvement(outputStream.toByteArray());
		dossierDelegueRepo.save(dossier);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=etat_investissement.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(outputStream.toByteArray());}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce Dossier Délégué n'existe pas");
    }
	
    private StringBuilder genererContenuXml(List<Transfert> transferts) {
	    StringBuilder xml = new StringBuilder();
	    xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	    xml.append("<etatDeclaration>\n");

	    for (Transfert transfert : transferts) {
	        xml.append("<ligne>\n");
	        xml.append("    <colonne1>").append(transfert.getDatecre().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("</colonne1>\n");
	        xml.append("    <colonne2>").append(transfert.getNatureJuridique()).append("</colonne2>\n");
	        xml.append("    <colonne3>").append(transfert.getMontantTransfert()).append("</colonne3>\n");
	        xml.append("    <colonne4>").append(transfert.getCompteBancaire_source().getNumeroCompte()).append("</colonne4>\n");
	        Participant emmeteur = transfert.getCompteBancaire_source().getParticipant();
	        if (emmeteur instanceof PersonneMorale) {
	            xml.append("    <colonne5>").append(((PersonneMorale) emmeteur).getRaisonSociale()).append("</colonne5>\n");
	        } else if(emmeteur instanceof PersonnePhysique){
	            xml.append("    <colonne5>").append(((PersonnePhysique) emmeteur).getNom()+" "+((PersonnePhysique) emmeteur).getPrenom()).append("</colonne5>\n");
	           
	        }
	        
	        Participant beneficiaire = transfert.getCompteBancaire_cible().getParticipant();
	        if (beneficiaire instanceof PersonneMorale) {
	            xml.append("    <colonne6>").append(((PersonneMorale) beneficiaire).getRaisonSociale()).append("</colonne6>\n");
	        } else if(beneficiaire instanceof PersonnePhysique){
	            xml.append("    <colonne6>").append(((PersonnePhysique) beneficiaire).getNom()+" "+((PersonnePhysique) beneficiaire).getPrenom()).append("</colonne6>\n");
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
