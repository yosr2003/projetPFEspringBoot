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
import org.springframework.web.multipart.MultipartFile;

import com.projetPfe.Iservices.IserviceDossierDelegue;
import com.projetPfe.Iservices.dossierScolariteIService;
import com.projetPfe.dto.DossierDTO;
import com.projetPfe.entities.DossierDelegue;
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
	 @PostMapping()
	 public ResponseEntity<?> creeDossier(@RequestBody DossierDTO d) {
			 return dossDelService.creeDossier(d);  
	 }
	
	 @PreAuthorize("hasRole('ChargéClientele')")
	 @PostMapping("/dupliquerDossier/{id}")
	 public ResponseEntity<Map<String, Object>> dupliquerDossier(@PathVariable("id") String id){
		 return dossDelService.dupliquerDossier(id);
	 }
	 
	 
	 @PreAuthorize("hasRole('ChargéClientele')")
	 @GetMapping(value = "/{id}", produces = "application/pdf")
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
	 @PutMapping(value = "/scolarite/prolonger/{id}", consumes = "multipart/form-data")
	 public ResponseEntity<?> prolongerDossierScolarite(
	         @PathVariable String id,
	         @RequestParam("dateProlongation") String dateStr,
	         @RequestParam("fichier") MultipartFile fichier) {

	     LocalDate dateProlongation = LocalDate.parse(dateStr);
	     return dossierScolariteServiceImp.prolongerDossierScolarite(id, dateProlongation, fichier);



	 }
	 

	 
	 
}
