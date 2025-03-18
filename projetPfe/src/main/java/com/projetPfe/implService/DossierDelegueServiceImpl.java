package com.projetPfe.implService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.IDossierDelegueService;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.repositories.DossierDelegueRepository;


@Service
public class DossierDelegueServiceImpl implements IDossierDelegueService{
	@Autowired
	private DossierDelegueRepository dossierDelegueRepo;

	@Override
	public List<DossierDelegue> getAllDossierDelegues() {
		// TODO Auto-generated method stub
		return dossierDelegueRepo.findAll();}
	@Override
	public Optional<DossierDelegue> getDossierById(String id) {
        return dossierDelegueRepo.findById(id);
    }

}
