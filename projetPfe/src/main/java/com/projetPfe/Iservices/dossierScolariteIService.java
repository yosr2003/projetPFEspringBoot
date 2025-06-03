package com.projetPfe.Iservices;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projetPfe.entities.DossierScolarité;

public interface dossierScolariteIService {



	ResponseEntity<?> prolongerDossierScolarite(String id, LocalDate dateProlongation, MultipartFile fichier);


}
