package com.projetPfe.implService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.IAmortissementEchService;
import com.projetPfe.dto.AmortissementEchDTO;
import com.projetPfe.dto.ApiResponse;
import com.projetPfe.dto.ResponseBodyDTO;
import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.AmortissementEch;
import com.projetPfe.repositories.AmortissementEchRepository;

@Service
public class AmortissementEchServiceImpl implements IAmortissementEchService {
    
    @Autowired
    private AmortissementEchRepository amortissementEchRepository;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ApiResponse getUpcomingDeadlines() {
        try {
            // Calculer la date cible (date actuelle + 10 jours)
            LocalDate targetDate = LocalDate.now().plusDays(10);
            
            // Récupérer les échéances qui correspondent aux critères
            List<AmortissementEch> upcomingDeadlines = amortissementEchRepository.findUpcomingDeadlines(targetDate);
            
            // Convertir les entités en DTOs
            List<AmortissementEchDTO> dtos = upcomingDeadlines.stream()
                    .map(AmortissementEchDTO::new)
                    .collect(Collectors.toList());
            
            // Créer le ResponseHeaderDTO
            ResponseHeaderDTO headerDTO = new ResponseHeaderDTO(200, "SERVICE_OK", "Liste des échéances retournée avec succès");
            
            // Créer le ResponseBodyDTO qui encapsule les données
            ResponseBodyDTO<List<AmortissementEchDTO>> bodyDTO = new ResponseBodyDTO<>(dtos);
            
            // Convertir le ResponseHeaderDTO en Map pour l'ApiResponse
            Map<String, Object> headerMap = new HashMap<>();
            headerMap.put("code", headerDTO.getCode());
            headerMap.put("libelle", headerDTO.getLibelle());
            headerMap.put("message", headerDTO.getMessage());
            
            // Retourner l'ApiResponse avec le header et le body (qui est un ResponseBodyDTO)
            return new ApiResponse(headerMap, bodyDTO);
            
        } catch (Exception e) {
            // En cas d'erreur, retourner un statut d'erreur
            ResponseHeaderDTO headerDTO = new ResponseHeaderDTO(500, "INTERNAL_SERVER_ERROR", e.getMessage());
            
            // Convertir le ResponseHeaderDTO en Map pour l'ApiResponse
            Map<String, Object> headerMap = new HashMap<>();
            headerMap.put("code", headerDTO.getCode());
            headerMap.put("libelle", headerDTO.getLibelle());
            headerMap.put("message", headerDTO.getMessage());
            
            // Retourner l'ApiResponse avec le header et un body null
            return new ApiResponse(headerMap, null);
        }
    }
}
