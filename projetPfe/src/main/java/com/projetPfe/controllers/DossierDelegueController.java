package com.projetPfe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservice.IDossierDelegueService;
import com.projetPfe.entities.DossierDelegue;



@RestController
public class DossierDelegueController {
	@Autowired
	private IDossierDelegueService dossDelRepo;
	
	@GetMapping("/dossiersDelegues")
	public List<DossierDelegue> getAllDossierDelegue() {
		return dossDelRepo.getAllDossierDelegues();	}

	

}
