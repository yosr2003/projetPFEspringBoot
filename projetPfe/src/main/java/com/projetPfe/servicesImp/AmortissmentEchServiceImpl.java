package com.projetPfe.servicesImp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservices.IAmotissementService;
import com.projetPfe.dto.ApiResponse;
import com.projetPfe.dto.ResponseBodyDTO;
import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.AmortissementEcheance;
import com.projetPfe.repositories.AmortissementEchRepository;

@Service
public class AmortissmentEchServiceImpl implements IAmotissementService{
	@Autowired
	private AmortissementEchRepository amotissementRepo;

	@Override
	public ApiResponse alerteEcheance() {
		 try {
	            // Calculer la date cible (date actuelle + 10 jours)
	            LocalDate targetDate = LocalDate.now().plusDays(10);
	            
	            // Récupérer les échéances qui correspondent aux critères
	            List<AmortissementEcheance> upcomingDeadlines = amotissementRepo.findUpcomingDeadlines(targetDate);

	            ResponseHeaderDTO headerDTO = new ResponseHeaderDTO(200, "SERVICE_OK", "Liste des échéances retournée avec succès");
	            
	            // Créer le ResponseBodyDTO qui encapsule les données
	            ResponseBodyDTO<List<AmortissementEcheance>> body = new ResponseBodyDTO<>(upcomingDeadlines);
	            
	            // Convertir le ResponseHeaderDTO en Map pour l'ApiResponse
	            Map<String, Object> headerMap = new HashMap<>();
	            headerMap.put("code", headerDTO.getCode());
	            headerMap.put("libelle", headerDTO.getLibelle());
	            headerMap.put("message", headerDTO.getMessage());
	            
	            // Retourner l'ApiResponse avec le header et le body (qui est un ResponseBodyDTO)
	            return new ApiResponse(headerMap, body);
	            
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
