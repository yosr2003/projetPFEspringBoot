package com.projetPfe.implService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.IDossierDelegueService;
import com.projetPfe.dto.ResponseBodyDTO;
import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.EtatDoss;





import com.projetPfe.repositories.DossierDelegueRepository;


@Service
public class DossierDelegueServiceImpl implements IDossierDelegueService{
	@Autowired
	private DossierDelegueRepository dossierDelegueRepo;

	@Override
	public List<DossierDelegue> getAllDossierDelegues() {
		// ////TODO Auto-generated method stub
		return dossierDelegueRepo.findAll();}
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

	
	
	

}
