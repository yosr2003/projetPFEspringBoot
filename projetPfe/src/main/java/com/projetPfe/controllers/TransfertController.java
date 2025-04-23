package com.projetPfe.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertType;
import com.projetPfe.repositories.dossierDelegueRepository;
import com.projetPfe.servicesImp.TransfertServiceImp;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/transferts")
public class TransfertController {
	@Autowired
	private TransfertServiceImp transfertService;

	@Autowired
	private dossierDelegueRepository dossierDelegueRepository;

	
	@GetMapping("/calculerFrais")
    public ResponseEntity<Object> calculerFrais(
    @RequestParam Double montant,
    @RequestParam String deviseCible,
    @RequestParam String deviseSource,
    @RequestParam String typefrais
    ) {

    Optional<Object> result = transfertService.calculerFrais(montant, deviseCible, deviseSource, typefrais);

    if (result.isPresent()) {
        return ResponseEntity.ok(result.get());
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("impossible de calculer les frais de transfert");
    }
    }
	
	
	@PostMapping("/creer")
	public ResponseEntity<?> creerTransfert(@RequestBody Map<String, Object> body) {
	    try {
	        ObjectMapper mapper = new ObjectMapper();

	        Double montant = Double.valueOf(body.get("montant").toString());

	        // Compte source et cible
	        CompteBancaire compteSource = mapper.convertValue(body.get("compteSource"), CompteBancaire.class);
	        CompteBancaire compteCible = mapper.convertValue(body.get("compteCible"), CompteBancaire.class);

	        // Type de frais
	        FraisType typeFrais = FraisType.valueOf(body.get("typeFrais").toString());

	        // Nature opération pour TransfertPermanent
	        String natureOperation = body.get("natureOperation") != null ? body.get("natureOperation").toString() : null;

	        // Type pour TransfertPonctuel
	        TransfertType type = body.get("type") != null ? TransfertType.valueOf(body.get("type").toString()) : null;

	    
	        DossierDelegue dossierDelegue = null;
	        if (body.get("dossierDelegue") != null) {
	            Map<String, Object> dossierMap = (Map<String, Object>) body.get("dossierDelegue");
	            String idDossier = dossierMap.get("idDossier").toString(); // on garde comme String
	            // Appel du service ou repository pour récupérer le vrai objet
	            Optional<DossierDelegue> optionalDossier = java.util.Optional.empty();
				try {
					optionalDossier = dossierDelegueRepository.findById(idDossier);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if (optionalDossier.isPresent()) {
	                dossierDelegue = optionalDossier.get();
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dossier délégué non trouvé avec ID : " + idDossier);
	            } // méthode qui retourne un objet concret

	        }

	        // Appel au service
	        Transfert transfert = transfertService.creerTransfert(
	                montant,
	                compteSource,
	                compteCible,
	                typeFrais,
	                dossierDelegue,
	                natureOperation,
	                type
	        );

	        return ResponseEntity.ok(transfert);

	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : " + e.getMessage());
	    }
	}



}
