package com.projetPfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetPfe.Iservices.IRapportMvmntFinanciersService;

@Controller
@RequestMapping("/RapportMvmntsFinanciers")
public class RapportMvmntFinanciersController {
	@Autowired
	private IRapportMvmntFinanciersService rapportService;
	
	
	@PreAuthorize("hasRole('ChargéClientele')")
	@PostMapping("/{id}")
	public ResponseEntity<?> genereRapportMouvement(@PathVariable("id") String id) {
	 try {
		return rapportService.genererRapportMouvement(id);
	} catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Une erreur est survenue lors de la génération du dossier de mouvements");
		
	} 
	}
	
	@PreAuthorize("hasRole('ChargéClientele')")
	@GetMapping("/{id}")
	public ResponseEntity<?> consulterRapportMouvement(@PathVariable("id") String id) {
	    try {
	        return rapportService.consulterRapportMouvement(id);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("Une erreur est survenue lors de la consultation du rapport de mouvements");
	    }
	}


}
