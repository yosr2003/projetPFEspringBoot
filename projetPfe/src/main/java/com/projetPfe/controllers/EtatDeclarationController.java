package com.projetPfe.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	 public EtatDeclarationBCT genererContenu(){
		 return etaDecService.genererContenuXml();
	 }
	
	@GetMapping("/{id}")
	 public ResponseEntity<byte[]> genererEtatDeclaration(@PathVariable int id){
		 byte[] pdfBytes = etaDecService.genererEtatDeclaration(id);
		 return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=etat_investissement.pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(pdfBytes);
	 }
	 
	 @GetMapping("/test/{id}")
	 public ResponseEntity<byte[]> test(@PathVariable int id) throws Exception{
		 byte[] pdfBytes = etaDecService.test(id);
		 return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=etat_investissement.pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(pdfBytes);
	 }

}
