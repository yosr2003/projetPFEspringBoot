package com.projetPfe.servicesImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservices.dossierScolariteIService;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.repositories.dossierDelegueRepository;
@Service
public class DossierScolariteServiceImp implements dossierScolariteIService {
	
	@Autowired
	dossierDelegueRepository dossierDelegueRepo;
	@Override
	public ResponseEntity<?> prolongerDossierScolarite(String id, DossierScolarité input) {
	    Optional<DossierDelegue> optional = dossierDelegueRepo.findById(id);
	    if (optional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dossier non trouvé");
	    }

	    DossierDelegue dossier = optional.get();

	    // Vérification que c'est bien un DossierScolarité
	    if (!(dossier instanceof DossierScolarité)) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Seuls les dossiers de scolarité peuvent être prolongés");
	    }

	    if (input.getDateExpiration() == null || input.getMotifProlong() == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Date de prolongation et motif requis");
	    }

	    if (input.getDateExpiration().isBefore(dossier.getDateExpiration())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("La nouvelle date doit être postérieure à la date actuelle : " +
	                        dossier.getDateExpiration());
	    }

	    if (!dossier.getEtatDossier().equals(EtatDoss.VALIDE)) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Le dossier doit être validé pour être prolongé");
	    }

	    dossier.setDateExpiration(input.getDateExpiration());
	    dossier.setMotifProlong(input.getMotifProlong());

	    dossierDelegueRepo.save(dossier);

	    return ResponseEntity.ok().body(dossier);
	}

}
