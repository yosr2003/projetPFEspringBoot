package com.projetPfe.implService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetPfe.controllers.DossierDelegueController;
import com.projetPfe.controllers.TransfertController;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.Transfert;

import jakarta.annotation.PostConstruct;

@Service
public class DynamicApiService {
	private final Map<String, Supplier<CompletableFuture<Object>>> apiRegistry = new HashMap<>();
	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private TransfertController transfertController;

	@Autowired
	private DossierDelegueController dossierDelegueController;

	public DynamicApiService() {
	        // Ne pas enregistrer ici : les beans @Autowired ne sont pas encore disponibles
	    }
	
	@PostConstruct
	public void init() {
	        apiRegistry.put("getAllTransferts", () ->
	        transfertController.getAllTransfert()// Obtient le CompletableFuture<List<DossierDelegue>>
	            .thenApply(transferts -> transferts) // Renvoie la liste des dossiers
	    );

	        apiRegistry.put("getAllDossiers", () ->
	        dossierDelegueController.getAllDossiers() // Obtient le CompletableFuture<List<DossierDelegue>>
	            .thenApply(dossiers -> dossiers) // Renvoie la liste des dossiers
	    );
	    }

//    @Autowired
//    public DynamicApiService(TransfertController transfertController,
//                             DossierDelegueController dossierDelegueController) {
//        // Enregistrement des APIs
////    	apiRegistry.put("getAllDossiers", () -> CompletableFuture.supplyAsync(() -> {
////    	    CompletableFuture<List<DossierDelegue>> dossiers = dossierDelegueController.getAllDossiers().getBody(); // <- important
////    	    return objectMapper.writeValueAsString(dossiers); // serialize to JSON string
////    	}));
//
//    	apiRegistry.put("getAllTransferts", () -> CompletableFuture.supplyAsync(() -> {
//    	    List<Transfert> transferts = transfertController.getAllTransfert();
//    	    return objectMapper.writeValueAsString(transferts);
//    	})); 
//    	}

	public boolean exists(String name) {
        return apiRegistry.containsKey(name);
    }

    public Supplier<CompletableFuture<Object>> getApiByName(String name) {
        return apiRegistry.get(name);
    }

}
