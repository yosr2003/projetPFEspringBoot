package com.projetPfe.Iservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;

import com.projetPfe.entities.DossierDelegue;

public interface IDossierDelegueService {
	public CompletableFuture<List<DossierDelegue>> getAllDossierDelegues();
	public Optional<DossierDelegue> getDossierById(String id);

	public ResponseEntity<Map<String, Object>> cloturerDossier(DossierDelegue d, String id);

//	public ResponseEntity<Response<DossierDelegue>> clotureDossier(DossierDelegue d, String id);
	
	public ResponseEntity<Map<String, Object>> dupliquerDossier(String id);
	public ResponseEntity<?> genererRapportMouvement(String idDossier)throws Exception ;
	

}