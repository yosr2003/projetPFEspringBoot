package com.projetPfe.controllers;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.entities.Swift;
import com.projetPfe.servicesImp.DossierDelegueService;
import com.projetPfe.servicesImp.DossierScolariteServiceImp;
import com.projetPfe.servicesImp.SwiftServiceImp;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/Swift")
public class SwiftController {
	@Autowired
	private SwiftServiceImp swiftService;

	 @PreAuthorize("hasRole('BackOffice')")
	  @PostMapping("/{transfertId}")
	 public ResponseEntity<byte[]> creerSwift(@PathVariable String transfertId) {
		    return swiftService.creerSwift(transfertId); // Retourne le PDF
		}

	    @PreAuthorize("hasRole('BackOffice')")
	    @GetMapping("/{transfertId}")
	    public ResponseEntity<?> consulterSwift(@PathVariable String transfertId) {
	        try {
	            Swift swift = swiftService.consulterSwift(transfertId);

	            if (swift == null || swift.getPdfgen() == null) {
	                String message = "Le transfert " + transfertId + " n'a pas de message SWIFT";
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	            }

	            byte[] pdfBytes = swift.getPdfgen();

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_PDF);
	            headers.setContentDisposition(ContentDisposition.inline().filename("swift_" + transfertId + ".pdf").build());

	            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la consultation du SWIFT.");
	        }
	    }
	
	 
}
