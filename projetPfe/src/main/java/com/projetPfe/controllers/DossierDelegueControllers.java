package com.projetPfe.controllers;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservices.IserviceDossierDelegue;
import com.projetPfe.Iservices.dossierScolariteIService;

import com.projetPfe.entities.DossierScolarité;


@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/dossiersDelegues")
public class DossierDelegueControllers {
	@Autowired
	private IserviceDossierDelegue dossDelService;
	@Autowired
	private dossierScolariteIService dossierScolariteServiceImp;
	

	 @GetMapping
	 public ResponseEntity<?> getAllDossiers() {
	        return dossDelService.getAllDossiers();
	    }
	
	 @PreAuthorize("hasRole('ChargéClientele')")
	 @PostMapping("/dupliquerDossier/{id}")
	 public ResponseEntity<Map<String, Object>> dupliquerDossier(@PathVariable("id") String id){
		 return dossDelService.dupliquerDossier(id);
	 }
	 
	 
	 @PreAuthorize("hasRole('ChargéClientele')")
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getDossierById(@PathVariable String id) {
	        return dossDelService.getDossierById(id);
	    }
	 
	 @PreAuthorize("hasRole('ChargéClientele')")
	 @PutMapping("/cloturer/{id}")
	 public ResponseEntity<Map<String, Object>> cloturerDossier(
	         @PathVariable String id,
	         @RequestBody Map<String, Object> body) {

	     LocalDate dateCloture = LocalDate.parse((String) body.get("dateCloture"));
	     String motif = (String) body.get("motifcloture");

	     return dossDelService.cloturerDossier(id, dateCloture, motif);
	 }

	 @PreAuthorize("hasRole('ChargéClientele')")
	 @PutMapping("/scolarite/prolonger/{id}")
	 public ResponseEntity<?> prolongerDossierScolarite(
	         @PathVariable String id,@RequestBody Map<String, Object> body) {
	     LocalDate dateProlongation = LocalDate.parse((String) body.get("dateProlongation"));
	     String motifProlongation = (String) body.get("motifProlongation");

	     return dossierScolariteServiceImp.prolongerDossierScolarite(id, dateProlongation, motifProlongation);
	 }

	 

	 
	 
}
