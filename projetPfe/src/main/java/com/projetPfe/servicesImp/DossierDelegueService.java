package com.projetPfe.servicesImp;

import java.util.HashMap;
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
}
