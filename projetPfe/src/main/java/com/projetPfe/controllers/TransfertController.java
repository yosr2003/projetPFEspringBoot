package com.projetPfe.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.servicesImp.DossierDelegueService;
import com.projetPfe.servicesImp.TransfertServiceImp;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/dossiersDelegues")
public class TransfertController {
	@Autowired
	private TransfertServiceImp transfertService;

	
	
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

}
