package com.projetPfe.Iservices;

import org.springframework.http.ResponseEntity;

public interface IRapportMvmntFinanciersService {
	public ResponseEntity<?> genererRapportMouvement(String idDossier) throws Exception;

}
