package com.projetPfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservice.IAmortissementEchService;
import com.projetPfe.dto.ApiResponse;

@RestController
@RequestMapping("/api/amortissements")
public class AmortissementEchController {
    
    @Autowired
    private IAmortissementEchService amortissementEchService;
    
    /**
     * Endpoint pour récupérer les alertes d'échéances à venir (10 jours avant la date d'échéance)
     * @return ResponseEntity contenant la réponse API
     */
    @GetMapping("/alerts")
    public ResponseEntity<ApiResponse> getUpcomingDeadlineAlerts() {
        ApiResponse response = amortissementEchService.getUpcomingDeadlines();
        
        // Récupérer le code HTTP à partir du header
        int statusCode = (int) response.getHeader().get("code");
        
        // Retourner la réponse avec le code HTTP correspondant
        return ResponseEntity.status(statusCode).body(response);
    }
}
