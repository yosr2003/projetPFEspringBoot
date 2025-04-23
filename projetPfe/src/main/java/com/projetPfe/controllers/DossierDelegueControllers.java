package com.projetPfe.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.entities.DossierDelegue;
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
	
	 
	 @PostMapping("/dupliquerDossier/{id}")
	 public ResponseEntity<Map<String, Object>> dupliquerDossier(@PathVariable("id") String id){
		 return dossDelService.dupliquerDossier(id);
	 }
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getDossierById(@PathVariable String id) {
	        return dossDelService.getDossierById(id);
	    }
	 
	 @PutMapping("/cloturerDossier/{id}")
	 public ResponseEntity<?> cloturerDossier(@RequestBody DossierDelegue d,@PathVariable("id") String id){
		 return dossDelService.cloturerDossier(d,id);
	 }
	 @PutMapping("/ProlongerDossier/{id}")
	 public ResponseEntity<?> prolonger(@RequestBody DossierDelegue d,@PathVariable("id") String id){
		 return dossDelService.prolongerDossier(d, id);
	 }
	 
	 
}
