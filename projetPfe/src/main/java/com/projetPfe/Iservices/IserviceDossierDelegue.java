package com.projetPfe.Iservices;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface IserviceDossierDelegue {
	   ResponseEntity<Map<String, Object>> dupliquerDossier(String id);
}
