package com.projetPfe.controllers;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.servicesImp.DossierDelegueService;
import com.projetPfe.servicesImp.DossierScolariteServiceImp;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/dossiersDelegues")
public class DossierDelegueControllers {
	@Autowired
	private DossierDelegueService dossDelService;
	@Autowired
	private DossierScolariteServiceImp dossierScolariteServiceImp;
	

	 @GetMapping
	 public ResponseEntity<?> getAllDossiers() {
	        return dossDelService.getAllDossiers();
	    }
	
	 
	 @PostMapping("/dupliquerDossier/{id}")
	 public ResponseEntity<Map<String, Object>> dupliquerDossier(@PathVariable("id") String id){
		 return dossDelService.dupliquerDossier(id);
	 }
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getDossierById(@PathVariable String id) {
	        return dossDelService.getDossierById(id);
	    }
	 
	 @PutMapping("/cloturer/{id}")
	 public ResponseEntity<Map<String, Object>> cloturerDossier(
	         @PathVariable String id,
	         @RequestBody Map<String, Object> body) {

	     LocalDate dateCloture = LocalDate.parse((String) body.get("dateCloture"));
	     String motif = (String) body.get("motifcloture");

	     return dossDelService.cloturerDossier(id, dateCloture, motif);
	 }

	 @PutMapping("/scolarite/prolonger/{id}")
	 public ResponseEntity<?> prolongerDossierScolarite(
	         @PathVariable String id,
	         @RequestBody DossierScolarité input) {
	     return dossierScolariteServiceImp.prolongerDossierScolarite(id, input);
	 }

	 
	 
}
