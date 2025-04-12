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
import org.springframework.web.bind.annotation.RequestBody;
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
	

	 
	 @GetMapping("/test")
	 public ResponseEntity<byte[]> test(@RequestBody Map<String, String> requestBody) throws Exception{
		 String typeDeclaration = requestBody.get("typeDeclaration");
		 String trimestre = requestBody.get("trimestre");
		 byte[] pdfBytes = etaDecService.test(trimestre,typeDeclaration);
		 return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=etat_investissement.pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(pdfBytes);
	 }
	 
	 @GetMapping("/test/{id}")
	 public ResponseEntity<byte[]> genererEtatDeclaration(@RequestBody Map<String, String> requestBody,@PathVariable int id) throws Exception{
		 String typeDeclaration = requestBody.get("typeDeclaration");
		 String trimestre = requestBody.get("trimestre");
		 byte[] pdfBytes = etaDecService.genererEtatDeclaration(typeDeclaration,trimestre,id);
		 return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=etat_investissement.pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(pdfBytes);
	 }

}
