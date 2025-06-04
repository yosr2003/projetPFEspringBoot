package com.projetPfe.servicesImp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservices.IAmotissementService;
import com.projetPfe.dto.ApiResponse;
import com.projetPfe.dto.ResponseBodyDTO;
import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.AmortissementEcheance;
import com.projetPfe.repositories.AmortissementEchRepository;

@Service
public class AmortissmentEchServiceImpl implements IAmotissementService {

    @Autowired
    private AmortissementEchRepository amotissementRepo;

    @Override
    public ApiResponse alerteEcheance() {
        try {
            // Calcule la date exactement dans 10 jours
            LocalDate targetDate = LocalDate.now().plusDays(10);

            // Appelle le repo pour récupérer uniquement les échéances à cette date précise
            List<AmortissementEcheance> exactDeadlines = amotissementRepo.findEcheancesExactlyIn10Days(targetDate);

            ResponseHeaderDTO headerDTO = new ResponseHeaderDTO(200, "SERVICE_OK", "Liste des échéances retournée avec succès");
            ResponseBodyDTO<List<AmortissementEcheance>> body = new ResponseBodyDTO<>(exactDeadlines);

            Map<String, Object> headerMap = new HashMap<>();
            headerMap.put("code", headerDTO.getCode());
            headerMap.put("libelle", headerDTO.getLibelle());
            headerMap.put("message", headerDTO.getMessage());

            return new ApiResponse(headerMap, body);

        } catch (Exception e) {
            ResponseHeaderDTO headerDTO = new ResponseHeaderDTO(500, "INTERNAL_SERVER_ERROR", e.getMessage());

            Map<String, Object> headerMap = new HashMap<>();
            headerMap.put("code", headerDTO.getCode());
            headerMap.put("libelle", headerDTO.getLibelle());
            headerMap.put("message", headerDTO.getMessage());

            return new ApiResponse(headerMap, null);
        }
    }

}
