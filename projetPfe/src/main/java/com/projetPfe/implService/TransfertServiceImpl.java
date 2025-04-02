package com.projetPfe.implService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.ITansfertService;
import com.projetPfe.dto.TransfertDTO;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Transfert;
import com.projetPfe.repositories.TransfertRepository;


@Service
public class TransfertServiceImpl implements ITansfertService {
	@Autowired
	private TransfertRepository transfertRepo;



	@Override
	public List<Transfert> getAllTransferts() {
		return transfertRepo.findAll();
	}

	   @Override
    public List<Transfert> AlerteTransfertAttente() {
        return transfertRepo.findByEtat(EtatDoss.TRAITEMENT);
    }



	@Override
	public Optional<TransfertDTO> getTransfertStatus(String refTransfert) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}



	@Override
	public Optional<Object> calculerFrais(Double montant, String deviseCible, String deviseSource, String typefrais,
			double montantFrais) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
