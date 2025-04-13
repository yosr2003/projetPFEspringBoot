package com.projetPfe.controllers;
import com.projetPfe.dto.TransfertDTO;
import com.projetPfe.entities.Transfert;
import com.projetPfe.services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transferts")
public class TransfertController {
    @Autowired
    private TransfertService transfertService;

    @GetMapping
    public List<Transfert> getAllTransfert() {
        return transfertService.getAllTransferts();	}


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

        Optional<Object> result = transfertService.calculerFrais(montant, deviseCible, deviseSource, typefrais, montantFrais);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Unable to calculate frais, missing data or invalid parameters.");
        }
    }

}
