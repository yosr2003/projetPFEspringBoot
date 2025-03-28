package com.projetPfe.Iservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.projetPfe.entities.DossierDelegue;

public interface IDossierDelegueService {
	public List<DossierDelegue> getAllDossierDelegues();
	public Optional<DossierDelegue> getDossierById(String id);
	public ResponseEntity<DossierDelegue> cloturerDossier(DossierDelegue d, String id);
//	public ResponseEntity<Response<DossierDelegue>> clotureDossier(DossierDelegue d, String id);
	
	ResponseEntity<Map<String, String>> dupliquerDossier(String id);
	

}