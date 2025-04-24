package com.projetPfe.Iservices;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.entities.DossierScolarité;
@Service
public interface dossierScolariteIService {

	ResponseEntity<?> prolongerDossierScolarite(String id, LocalDate dateProlongation, String motif);


}
