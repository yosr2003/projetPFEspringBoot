package com.projetPfe.Iservice;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.entities.DossierDelegue;

@Service
public interface IDossierDelegueService {
    List<DossierDelegue> getAllDossierDelegues();
    Optional<DossierDelegue> getDossierById(String id);
    ResponseEntity<DossierDelegue> cloturerDossier(DossierDelegue d, String id);
    DossierDelegue updateDossier(DossierDelegue dossier);
}
