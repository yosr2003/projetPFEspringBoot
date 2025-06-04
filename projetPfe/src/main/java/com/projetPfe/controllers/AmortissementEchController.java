package com.projetPfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservices.IAmotissementService;
import com.projetPfe.dto.ApiResponse;


@CrossOrigin(origins = "*")
@RestController
public class AmortissementEchController {
	@Autowired
	private IAmotissementService amortissementService;
	
	
	 @PreAuthorize("hasRole('ChargéClientele')")
	@GetMapping("/alertsUpcomingDeadline")
    public ResponseEntity<ApiResponse> getUpcomingDeadlineAlerts() {
        ApiResponse response = amortissementService.alerteEcheance();
      
        int statusCode = (int) response.getHeader().get("code");
        
        return ResponseEntity.status(statusCode).body(response);
    }
	

}
