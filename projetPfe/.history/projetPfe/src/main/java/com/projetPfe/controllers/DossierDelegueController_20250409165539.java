package com.projetPfe.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.projetPfe.Iservice.IDossierDelegueService;
import com.projetPfe.entities.DossierDelegue;



@RestController
@RequestMapping("/dossiersDelegues")
public class DossierDelegueController {
	@Autowired
	private IDossierDelegueService dossDelService;
	
	@GetMapping
    public ResponseEntity<List<DossierDelegue>> getAllDossiers() {
        return ResponseEntity.ok(dossDelService.getAllDossierDelegues());
    }
	
	 @GetMapping("/{id}")
	 public ResponseEntity<DossierDelegue> getDossierById(@PathVariable String id) {
	        Optional<DossierDelegue> dossier = dossDelService.getDossierById(id);
	        return dossier.map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Map<String, Object>>  cloturerDossier(@RequestBody DossierDelegue d,@PathVariable("id") String id){
		 return dossDelService.cloturerDossier(d,id);
	 }
	 @PutMapping("/dupliquerDossier/{id}")
	 public ResponseEntity<Map<String, Object>> dupliquerDossier(@PathVariable("id") String id){
		 return dossDelService.dupliquerDossier(id);
	 }
	 
    
	

}
