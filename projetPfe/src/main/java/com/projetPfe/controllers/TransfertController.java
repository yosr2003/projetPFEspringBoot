package com.projetPfe.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetPfe.Iservices.TransfertServiceI;
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
	private TransfertServiceI transfertService;

	@Autowired
	private dossierDelegueRepository dossierDelegueRepository;

	 @GetMapping
	 public  List<Transfert> getAllTransferts() {
	        return transfertService.getAll();
	    }
	 
	 @PreAuthorize("hasRole('ChargéClientele')")
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getTransfertById(@PathVariable String id) {
	        return transfertService.consulterTransfert(id);
	    }
	 
	 
	 @PreAuthorize("hasRole('ChargéClientele')") 
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
	
	@PreAuthorize("hasRole('ChargéClientele')")
	@PostMapping
	public ResponseEntity<?> creerTransfert(@RequestBody Map<String, Object> body) {
	    try {
	        Double montant = Double.valueOf(body.get("montant").toString());
	        String numeroCompteSource = body.get("numeroCompteSource") != null ? body.get("numeroCompteSource").toString() : null;
	        String numeroCompteCible = body.get("numeroCompteCible") != null ? body.get("numeroCompteCible").toString() : null;
	        
	        FraisType typeFrais = FraisType.valueOf(body.get("typeFrais").toString().toUpperCase());

	        String natureOperation = body.get("natureOperation") != null ? body.get("natureOperation").toString() : null;
	        TransfertType type = body.get("type") != null ? TransfertType.valueOf(body.get("type").toString()) : null;

	        String idDossierDelegue = null;
	        if (body.get("idDossierDelegue") != null) {
	            idDossierDelegue = body.get("idDossierDelegue").toString();
	        }

	        Transfert transfert = transfertService.creerTransfert(
	                montant,
	                numeroCompteSource,
	                numeroCompteCible,
	                typeFrais,
	                idDossierDelegue,
	                natureOperation,
	                type
	        );

	        return ResponseEntity.ok(transfert);

	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : " + e.getMessage());
	    }
	}
	
	
	 @PreAuthorize("hasRole('BackOffice')")
	@GetMapping("/alerte")
	public List<Transfert> alerteTransfertsAttente() {
	    return transfertService.AlerteTransfertAttente();
	}


}
