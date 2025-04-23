package com.projetPfe.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.projetPfe.servicesImp.DossierDelegueService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/dossiersDelegues")
public class DossierDelegueControllers {
	@Autowired
	private DossierDelegueService dossDelService;
	

	 @GetMapping
	 public ResponseEntity<?> getAllDossiers() {
	        return dossDelService.getAllDossiers();
	    }
	
	 
	 @PutMapping("/dupliquerDossier/{id}")
	 public ResponseEntity<Map<String, Object>> dupliquerDossier(@PathVariable("id") String id){
		 return dossDelService.dupliquerDossier(id);
	 }
	 
}
