
package com.projetPfe.servicesImp;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projetPfe.Iservices.dossierScolariteIService;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.PieceJustificative;
import com.projetPfe.repositories.dossierDelegueRepository;
@Service
public class DossierScolariteServiceImp implements dossierScolariteIService {
	
	@Autowired
	dossierDelegueRepository dossierDelegueRepo;
	
	@Override
	public ResponseEntity<?> prolongerDossierScolarite(String id, LocalDate dateProlongation, MultipartFile fichier){

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
	    if (dateProlongation == null ) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Date de prolongation et motif requis");
	    }

	    if (dateProlongation.isBefore(dossier.getDateExpiration())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("La nouvelle date de prolongation doit depasser la date d'expiration actuelle : " +dossier.getDateExpiration());
	    }

	    if (!dossier.getEtatDossier().equals(EtatDoss.VALIDE)||!dossier.getEtatDossier().equals(EtatDoss.OUVERT) ) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Le dossier doit être validé ou ouvert pour être prolongé");
	    }
	    if (fichier == null || fichier.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Une pièce justificative est requise pour la prolongation.");
	    }

	 
	
		PieceJustificative piece = new PieceJustificative();
			        piece.setNomFichier(fichier.getOriginalFilename());
			        piece.setDateDepot(LocalDateTime.now());
			        try {
			            piece.setContenu(fichier.getBytes());
			        } catch (IOException e) {
			            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			                    .body("Impossible de lire le contenu du fichier justificatif");
			        }

       DossierScolarité dossierScolarite = (DossierScolarité) dossier;
	    
	    dossierScolarite.setDateProlongation(dateProlongation);
	    piece.setDossierSC(dossierScolarite);

	    
	    
	    dossierScolarite.getPiecesJustificatives().add(piece);
	    dossierDelegueRepo.save(dossierScolarite);

	    return ResponseEntity.ok().body(dossierScolarite);
	}



}
