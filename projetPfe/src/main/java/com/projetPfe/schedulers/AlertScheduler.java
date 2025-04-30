package com.projetPfe.schedulers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.projetPfe.Iservice.IAmortissementEchService;
import com.projetPfe.dto.AmortissementEchDTO;
import com.projetPfe.dto.ApiResponse;
import com.projetPfe.dto.ResponseBodyDTO;

@Component
public class AlertScheduler {
    
    private static final Logger logger = LoggerFactory.getLogger(AlertScheduler.class);
    
    @Autowired
    private IAmortissementEchService amortissementEchService;
    
    /**
     * Tâche planifiée qui s'exécute tous les jours à minuit pour vérifier les échéances à venir
     * et envoyer des alertes si nécessaire
     */
    @Scheduled(cron = "0 0 9 * * ?") // Tous les jours à 09.00
    public void checkUpcomingDeadlines() {
        logger.info("Vérification des échéances à venir...");
        
        ApiResponse response = amortissementEchService.getUpcomingDeadlines();
        
        if (response.getBody() != null) {
            @SuppressWarnings("unchecked")
            ResponseBodyDTO<List<AmortissementEchDTO>> bodyDTO = (ResponseBodyDTO<List<AmortissementEchDTO>>) response.getBody();
            List<AmortissementEchDTO> echDTOs = bodyDTO.getData();
            
            if (echDTOs != null && !echDTOs.isEmpty()) {
                logger.info("Alertes envoyées pour {} échéances à venir", echDTOs.size());
                
                // Ici, vous pourriez implémenter l'envoi d'emails, de notifications push, etc.
                // pour alerter les utilisateurs concernés
                
                echDTOs.forEach(dto -> {
                    logger.info("Alerte pour l'échéance ID: {}, Dossier: {}, Date: {}", 
                            dto.getIdAmortissementEch(), dto.getIdDossier(), dto.getDateEch());
                });
            } else {
                logger.info("Aucune échéance à venir nécessitant une alerte");
            }
        } else {
            logger.info("Aucune échéance à venir nécessitant une alerte");
        }
    }
}
