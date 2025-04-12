package com.projetPfe.implService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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


    @Async
	@Override
	public CompletableFuture<List<Transfert>> getAllTransferts() {
		//return (CompletableFuture<List<Transfert>>) transfertRepo.findAll();
		 return CompletableFuture.supplyAsync(() -> transfertRepo.findAll());
	}

   /* @Override
    public List<Transfert> AlerteTransfertAttente() {
        return transfertRepo.findByEtat(EtatDoss.Traitement);
    }*/

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
