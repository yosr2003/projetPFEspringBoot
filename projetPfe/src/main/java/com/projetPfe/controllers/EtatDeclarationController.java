package com.projetPfe.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservices.EtatDeclarationIservice;
@RestController
@RequestMapping("/etatDeclaration")
public class EtatDeclarationController {
	@Autowired
	private EtatDeclarationIservice etaDecService;
	     
	 @PreAuthorize("hasRole('ChargéClientele')")	
		@PostMapping
	    public ResponseEntity<?> genererEtatDeclaration(@RequestBody Map<String, String> requestBody) {
	    
	        String typeDeclaration = requestBody.get("typeDeclaration");
	        String trimestre = requestBody.get("trimestre");
		    try {
				return 	etaDecService.genererEtatDeclaration(trimestre, typeDeclaration);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Une erreur est survenue lors de la génération de l’état de déclaration");	
			}
	       }
	 

	 
	 @PostMapping("/consulter")
	 @PreAuthorize("hasRole('ChargéClientele')")
	 public ResponseEntity<?> consulterEtatDeclaration(@RequestBody Map<String, String> requestBody) {
	     String typeDeclaration = requestBody.get("typeDeclaration");
	     String trimestre = requestBody.get("trimestre");

	     try {
	         return etaDecService.getEtatDeclarationParTypeEtTrimestre(trimestre, typeDeclaration);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body("Erreur lors de la consultation de l’état de déclaration.");
	     }
	 }




}
