package com.projetPfe.servicesImp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetPfe.Iservices.IserviceDossierDelegue;
import com.projetPfe.Iservices.TransfertServiceI;
import com.projetPfe.dto.DossierDTO;
import com.projetPfe.dto.ResponseBodyDTO;
import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierEmpreint;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.entities.DossierSoinMedical;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.Participant;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertPermanent;
import com.projetPfe.repositories.*;
@Service
public class DossierDelegueService implements IserviceDossierDelegue{
	@Autowired
	private TransfertServiceI transefrtService;
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
	            ResponseHeaderDTO header = new ResponseHeaderDTO(400, "BAD_REQUEST", "Échec : un dossier ne peut etre dupliqué que si il est valide et ce dossier n'est pas validé");
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
	        response.put("header", new ResponseHeaderDTO(404, "NOT_FOUND", "Dossier inexistant"));
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    DossierDelegue dossier = optionalDossier.get();

	    if (!dossier.getEtatDossier().equals(EtatDoss.VALIDE)) {
	        response.put("header", new ResponseHeaderDTO(400, "BAD_REQUEST", "Le dossier n'est pas validé"));
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    if (dateCloture != null && dateCloture.isAfter(dossier.getDateExpiration()) && dateCloture.isAfter(dossier.getDateDebut())) {
	        response.put("header", new ResponseHeaderDTO(400, "BAD_REQUEST", "La date de clôture  doit etre entre la date d'expiration :  "+ dossier.getDateExpiration()+" et la date debut du dossier " + dossier.getDateDebut()));
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
	    Optional<DossierDelegue> d = dossierDelegueRepo.findById(id);
	    if (!d.isPresent()) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce Dossier Délégué n'existe pas");
	    }

	    DossierDelegue dossier = d.get();

	    try {
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        Document pdfDoc = new Document();
	        PdfWriter.getInstance(pdfDoc, out);
	        pdfDoc.open();

	        // Titre centré
	        Paragraph titre = new Paragraph("Informations du dossier avec la réference : " + id);
	        titre.setAlignment(Paragraph.ALIGN_CENTER);
	        titre.getFont().setSize(16);
	        titre.setSpacingAfter(20f);
	        pdfDoc.add(titre);

	        // Infos en haut
	        pdfDoc.add(new Paragraph("Type de dossier : " + dossier.getClass().getSimpleName()));
	        pdfDoc.add(new Paragraph("Date Début : " + (dossier.getDateDebut() != null ? dossier.getDateDebut().toString() : "N/A")));
	        pdfDoc.add(new Paragraph("Date Expiration : " + (dossier.getDateExpiration() != null ? dossier.getDateExpiration().toString() : "N/A")));
	        pdfDoc.add(new Paragraph(" ")); // Espace après les paragraphes

	        // Tableau pour les attributs communs
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(100);
	        table.setSpacingBefore(10f);
	        table.setWidths(new float[]{2, 3});

	        Font fontAttribut = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	        Font fontValeur = new Font(Font.FontFamily.HELVETICA, 12);

	        // Attributs communs
	        ajouterLigne(table, "ID Dossier", dossier.getIdDossier(), fontAttribut, fontValeur);
	        ajouterLigne(table, "État", dossier.getEtatDossier().name(), fontAttribut, fontValeur);
	        ajouterLigne(table, "Date Création", dossier.getDateCre() != null ? dossier.getDateCre().toString() : "N/A", fontAttribut, fontValeur);
	        ajouterLigne(table, "Date Clôture", dossier.getDateCloture() != null ? dossier.getDateCloture().toString() : "N/A", fontAttribut, fontValeur);
	        ajouterLigne(table, "Motif de clôture", dossier.getMotifcloture() != null ? dossier.getMotifcloture() : "N/A", fontAttribut, fontValeur);

	        // Attributs spécifiques dynamiques
	        Class<?> classeDossier = dossier.getClass();
	        Class<?> classeParent = DossierDelegue.class;

	        for (var field : classeDossier.getDeclaredFields()) {
	            // Vérifie que c'est un attribut propre (pas hérité)
	            if (field.getDeclaringClass() != classeParent) {
	                field.setAccessible(true);

	                if (dossier instanceof DossierScolarité && field.getName().equals("piecesJustificatives")) {
	                    continue;
	                }

	                Object valeur = field.get(dossier);
	                ajouterLigne(table, field.getName(), valeur != null ? valeur.toString() : "N/A", fontAttribut, fontValeur);
	            }
	        }

	        // Ajouter une seule fois la ligne spéciale pour pièces justificatives
	        if (dossier instanceof DossierScolarité) {
	            ajouterLigne(table, "Pièces justificatives", "Fourni par le client", fontAttribut, fontValeur);
	        }



	        pdfDoc.add(table);
	        pdfDoc.close();

	        byte[] pdfBytes = out.toByteArray();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDispositionFormData("filename", "dossier_" + dossier.getIdDossier() + ".pdf");

	        return ResponseEntity.ok().headers(headers).body(pdfBytes);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la génération du PDF");
	    }
	}

	// Méthode utilitaire
	private void ajouterLigne(PdfPTable table, String attribut, String valeur, Font fontAttribut, Font fontValeur) {
	    table.addCell(new Paragraph(attribut, fontAttribut));
	    table.addCell(new Paragraph(valeur != null ? valeur : "N/A", fontValeur));
	}



	@Override
	public ResponseEntity<?> creeDossier(DossierDTO d) {
		if(d.getTransfert()==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la creation d'un dossier delegue ne se fait qu'avec la creation d'un transefert"); 
		}
		DossierDelegue dossier;
		TransfertPermanent transfert;
		switch(d.getTypeDossier().toLowerCase()) {
		 case "scolarite":
			  dossier=new DossierScolarité();
			  dossier.setIdDossier(genererIdentifiantUnique("DOSS_SC"));
			  ((DossierScolarité) dossier).setNiveauEtude(d.getNiveauEtude());
			  break;
		 case "soin_medical":
			  dossier =new DossierSoinMedical();
			  dossier.setIdDossier(genererIdentifiantUnique("DOSS_SM"));
			  ((DossierSoinMedical) dossier).setTypeTraitement(d.getTypeTraitement());
			  break;
		 case "empreintExtérieure ":
			   dossier=new DossierEmpreint();
			   dossier.setIdDossier(genererIdentifiantUnique("DOSS_EMP"));
			   ((DossierEmpreint) dossier).setMontantEmpreint(d.getMontantEmpreint());
		 
			   
	     default:
	    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ce type de dossier n'exitse pas"); 
            
		}
		
		dossier.setEtatDossier(EtatDoss.TRAITEMENT);
		dossier.setDateCre(LocalDateTime.now());
		dossier.setDateDebut(d.getDateDebut());
		dossier.setDateExpiration(d.getDateExpiration());
		dossierDelegueRepo.save(dossier);
		try {
			transfert=(TransfertPermanent) transefrtService.creerTransfert(d.getTransfert().getMontant(), d.getTransfert().getNumeroCompteSource(),  d.getTransfert().getNumeroCompteCible(), d.getTransfert().getTypeFrais(),dossier.getIdDossier(),d.getTransfert().getNatureOperation(), null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
			
		}
		
		return ResponseEntity.ok().body(dossier); 
	}






}
