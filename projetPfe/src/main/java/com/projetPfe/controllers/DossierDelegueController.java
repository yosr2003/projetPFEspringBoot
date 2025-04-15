package com.projetPfe.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	
	
	
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getDossierById(@PathVariable String id) {
	        return dossDelService.getDossierById(id);
	    }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<?>   cloturerDossier(@RequestBody DossierDelegue d,@PathVariable("id") String id){

		 return dossDelService.cloturerDossier(d,id);
	 }
	 
	 @PutMapping("/dupliquerDossier/{id}")
	 public ResponseEntity<Map<String, Object>> dupliquerDossier(@PathVariable("id") String id){
		 return dossDelService.dupliquerDossier(id);
	 }
	 
	 @GetMapping("/rapportMouvements/{id}")
		public ResponseEntity<?> genereRapportMouvement(@PathVariable("id") String id) {
		 try {
			return dossDelService.genererRapportMouvement(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Une erreur est survenue lors de la génération du dossier de mouvements");
			
		} 
		}
	 

	

}
