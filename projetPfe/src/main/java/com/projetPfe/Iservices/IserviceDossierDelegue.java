package com.projetPfe.Iservices;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.projetPfe.entities.DossierDelegue;

public interface IserviceDossierDelegue {
	public ResponseEntity<Map<String, Object>> dupliquerDossier(String id);

	public ResponseEntity<?> getAllDossiers();

	public ResponseEntity<?> getDossierById(String id);



	public ResponseEntity<Map<String, Object>> cloturerDossier(String id, LocalDate dateCloture, String motifcloture);

	
}
