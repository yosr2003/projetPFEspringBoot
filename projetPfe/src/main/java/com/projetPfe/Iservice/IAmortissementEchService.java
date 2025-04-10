package com.projetPfe.Iservice;

import com.projetPfe.dto.ApiResponse;

/**
 * Interface pour le service de gestion des échéances d'amortissement
 */
public interface IAmortissementEchService {
    

    ApiResponse getUpcomingDeadlines();
}
