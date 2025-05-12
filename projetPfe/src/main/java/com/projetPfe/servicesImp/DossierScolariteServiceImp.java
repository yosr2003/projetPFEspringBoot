package com.projetPfe.servicesImp;

import java.time.LocalDate;
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
	public ResponseEntity<?> prolongerDossierScolarite(String id, LocalDate dateProlongation, String motif) {
	    Optional<DossierDelegue> optional = dossierDelegueRepo.findById(id);
	    if (optional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dossier non trouvé");
	    }

	    DossierDelegue dossier = optional.get();

	    // Vérifie que c'est un dossier de type Scolarité
	    if (!(dossier instanceof DossierScolarité)) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Seuls les dossiers de scolarité peuvent être prolongés");
	    }

	    // Vérifications des paramètres
	    if (dateProlongation == null || motif == null || motif.trim().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Date de prolongation et motif requis");
	    }

	    if (dateProlongation.isBefore(dossier.getDateExpiration())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("La nouvelle date de prolongation doit depasser la date d'expiration actuelle : " +dossier.getDateExpiration());
	    }

	    if (!dossier.getEtatDossier().equals(EtatDoss.VALIDE)) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Le dossier doit être validé pour être prolongé");
	    }

	    dossier.setDateExpiration(dateProlongation);
	    dossier.setMotifProlong(motif);

	    dossierDelegueRepo.save(dossier);

	    return ResponseEntity.ok(dossier);
	}


}
