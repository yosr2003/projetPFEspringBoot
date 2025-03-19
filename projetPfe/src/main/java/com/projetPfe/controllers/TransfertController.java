package com.projetPfe.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservice.ITansfertService;
import com.projetPfe.dto.ApiResponse;
import com.projetPfe.entities.ResponseStatus;
import com.projetPfe.entities.Transfert;


@RestController
@RequestMapping("/transferts")
public class TransfertController {
	@Autowired
	private ITansfertService transfertService;
	
	@GetMapping
	public List<Transfert> getAllTransfert() {
		return transfertService.getAllTransferts();	}


	@GetMapping("/{refTransfert}")
        public ResponseEntity<ApiResponse> getTransfertById(@PathVariable String refTransfert) {
        return transfertService.getTransfertById(refTransfert)
            .map(transfert -> {
                // Construction du header
                Map<String, Object> header = new HashMap<>();
                header.put("code", ResponseStatus.SUCCESS.getCode());
                header.put("libelle", ResponseStatus.SUCCESS.getLibelle());

                // Construction du body
                Map<String, Object> body = new HashMap<>();
                body.put("refTransfert", transfert.getRefTransfert());
                body.put("etatDoss", transfert.getEtat());
                body.put("montantTransfert", transfert.getMontantTransfert());
                body.put("deviseTransfert", transfert.getDeviseTransfert());
                body.put("datecre", transfert.getDatecre());
								body.put("dateEchance",transfert.getDateEcheance());
								body.put("NatureTransfert",transfert.getNatureTransfert());
								body.put("Frais",transfert.getFrais());

                return ResponseEntity.ok(new ApiResponse(header, body));
            })
            .orElseGet(() -> {
                Map<String, Object> header = new HashMap<>();
                header.put("code", ResponseStatus.NOT_FOUND.getCode());
                header.put("libelle", ResponseStatus.NOT_FOUND.getLibelle());

                return ResponseEntity.status(404).body(new ApiResponse(header, null));
            });
    }
}
	
	
