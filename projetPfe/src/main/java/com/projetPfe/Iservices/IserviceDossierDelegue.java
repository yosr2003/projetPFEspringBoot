package com.projetPfe.Iservices;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.projetPfe.entities.DossierDelegue;

public interface IserviceDossierDelegue {
	   ResponseEntity<Map<String, Object>> dupliquerDossier(String id);

	ResponseEntity<?> getAllDossiers();

	ResponseEntity<?> getDossierById(String id);



	ResponseEntity<Map<String, Object>> cloturerDossier(String id, LocalDate dateCloture, String motifcloture);
}
