package com.projetPfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservices.IAmotissementService;
import com.projetPfe.dto.ApiResponse;



@RestController
public class AmortissementEchController {
	@Autowired
	private IAmotissementService amortissementService;
	
	@GetMapping("/alerts")
    public ResponseEntity<ApiResponse> getUpcomingDeadlineAlerts() {
        ApiResponse response = amortissementService.alerteEcheance();
      
        int statusCode = (int) response.getHeader().get("code");
        
        return ResponseEntity.status(statusCode).body(response);
    }
	

}
