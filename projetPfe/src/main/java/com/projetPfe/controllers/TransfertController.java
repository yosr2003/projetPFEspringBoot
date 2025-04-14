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
import com.projetPfe.dto.TransfertDTO;
import com.projetPfe.entities.Transfert;


@RestController
@RequestMapping("/transferts")
public class TransfertController {
	@Autowired
	private ITansfertService transfertService;
	
	@GetMapping
	public CompletableFuture<List<Transfert>> getAllTransfert() {
		return transfertService.getAllTransferts();	}
	
	/*@GetMapping("/alerteTransfertAttente")
	public List<Transfert> AlerteTransfertAttente() {
		return transfertService.AlerteTransfertAttente();	}*/

	@GetMapping("/alerteTransfertAttente")
	public List<Transfert> AlerteTransfertAttente() {
		return transfertService.AlerteTransfertAttente();	}


    @GetMapping("/{refTransfert}/status")
    public ResponseEntity<TransfertDTO> getTransfertStatus(@PathVariable String refTransfert) {
        Optional<TransfertDTO> dto = transfertService.getTransfertStatus(refTransfert);
        return dto.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }


   @GetMapping("/calculerFrais")
    public ResponseEntity<Object> calculerFrais(
    @RequestParam Double montant,
    @RequestParam String deviseCible,
    @RequestParam String deviseSource,
    @RequestParam String typefrais,
    @RequestParam double montantFrais) {

    Optional<Object> result = transfertService.calculerFrais(montant, deviseCible, deviseSource, typefrais);

    if (result.isPresent()) {
        return ResponseEntity.ok(result.get());
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error: Unable to calculate frais, missing data or invalid parameters.");
    }
}

   @PostMapping("/creer")
   public ResponseEntity<?> creerTransfert(@RequestBody Transfert transfert) {
       try {
           transfertService.creerTransfert(
                   transfert.getMontantTransfert(),
                   transfert.getCompteBancaire_source(),
                   transfert.getCompteBancaire_cible(),
                   transfert.getTypeFrais(),
                   transfert.getDossierDelegue()
           );

           return ResponseEntity.ok("✅ Transfert créé avec succès.");
           
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("❌ Erreur lors de la création du transfert : " + e.getMessage());
       }
   }


}
	
	
