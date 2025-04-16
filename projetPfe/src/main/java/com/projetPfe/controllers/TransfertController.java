package com.projetPfe.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservice.ITansfertService;

import com.projetPfe.entities.Transfert;


@RestController
@RequestMapping("/transferts")
public class TransfertController {
	@Autowired
	private ITansfertService transfertService;
	
	@PostMapping
	public ResponseEntity<?> creerTransfert(@RequestBody Transfert transfert) {
	       try {
	           transfertService.creerTransfert(
	                   transfert.getMontantTransfert(),
	                   transfert.getCompteBancaire_source(),
	                   transfert.getCompteBancaire_cible(),
	                   transfert.getTypeFrais(),
	                   transfert.getDossierDelegue(),
	                   transfert.getNatureOperation(),
	                   transfert.getTypeTransfert()
	           );

	           return ResponseEntity.ok("✅ Transfert créé avec succès.");
	           
	       } catch (Exception e) {
	           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                   .body("❌ Erreur lors de la création du transfert : " + e.getMessage());
	       }
	   }
	

	@GetMapping("/alerteTransfertAttente")
	public List<Transfert> AlerteTransfertAttente() {
		return transfertService.AlerteTransfertAttente();	}
	@GetMapping
	public List<Transfert> getAll() {
		return transfertService.getAll();	}


    @GetMapping("/etat/{refTransfert}")
    public ResponseEntity<?> getTransfertEtats(@PathVariable String refTransfert) {
        //Optional<TransfertDTO> dto = transfertService.getTransfertEtats(refTransfert);
//        return dto.map(ResponseEntity::ok)
//                  .orElseGet(() -> ResponseEntity.notFound().build());
    	return transfertService.getTransfertEtats(refTransfert);
    }


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
                .body("Error: Unable to calculate frais, missing data or invalid parameters.");
    }
    }




}
	
	
