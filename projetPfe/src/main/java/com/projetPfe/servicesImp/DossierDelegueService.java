package com.projetPfe.servicesImp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservices.IserviceDossierDelegue;
import com.projetPfe.dto.ResponseBodyDTO;
import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.repositories.dossierDelegueRepository;
@Service
public class DossierDelegueService implements IserviceDossierDelegue{
	  @Autowired
	    private dossierDelegueRepository dossierDelegueRepo;
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



	public ResponseEntity<?> prolongerDossier(DossierDelegue d, String id) {
		// TODO Auto-generated method stub
		return null;
	}



}
