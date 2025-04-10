package com.projetPfe.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservice.IEtatDeclarationService;
import com.projetPfe.entities.EtatDeclarationBCT;

@RestController
@RequestMapping("/etatDeclaration")
public class EtatDeclarationController {
	@Autowired
	private IEtatDeclarationService etaDecService;
	
	@PostMapping
	 public EtatDeclarationBCT GenererEtatDeclaration(){
		 return etaDecService.genererContenuXml();
	 }
	 

}
