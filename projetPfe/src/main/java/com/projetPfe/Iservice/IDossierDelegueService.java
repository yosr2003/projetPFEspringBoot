package com.projetPfe.Iservice;

import java.util.List;
import java.util.Optional;

import com.projetPfe.entities.DossierDelegue;

public interface IDossierDelegueService {
	public List<DossierDelegue> getAllDossierDelegues();
	public Optional<DossierDelegue> getDossierById(String id);

}