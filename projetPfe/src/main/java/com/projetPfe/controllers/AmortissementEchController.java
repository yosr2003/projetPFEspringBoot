package com.projetPfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.dto.ApiResponse;
import com.projetPfe.services.AmortissementEchService;

@RestController
@RequestMapping("/api/amortissements")
public class AmortissementEchController {
    
    @Autowired
    private AmortissementEchService amortissementEchService;
    
    /**
     * Endpoint pour récupérer les alertes d'échéances à venir (10 jours avant la date d'échéance)
     * @return ResponseEntity contenant la réponse API
     */
    @GetMapping("/alerts")
    public ResponseEntity<ApiResponse> getUpcomingDeadlineAlerts() {
        ApiResponse response = amortissementEchService.getUpcomingDeadlines();
        
        // Retourner la réponse avec le code HTTP correspondant au code dans le header
        return ResponseEntity.status(response.getHeader().getCode()).body(response);
    }
}
