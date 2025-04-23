package com.projetPfe.Iservices;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.entities.DossierScolarité;
@Service
public interface dossierScolariteIService {

	ResponseEntity<?> prolongerDossierScolarite(String id, DossierScolarité input);

}
