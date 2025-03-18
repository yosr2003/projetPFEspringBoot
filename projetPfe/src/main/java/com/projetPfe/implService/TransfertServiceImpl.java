package com.projetPfe.implService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.ITansfertService;
import com.projetPfe.entities.Transfert;
import com.projetPfe.repositories.TransfertRepository;


@Service
public class TransfertServiceImpl implements ITansfertService {
	@Autowired
	private TransfertRepository transfertRepo;


	@Override
	public Transfert findTransfert(String id) {
		if(transfertRepo.findById(id).isPresent()) {
			return transfertRepo.findById(id).get();
		}else
		{
			return null;
		}
		
	}


	@Override
	public List<Transfert> getAllTransferts() {
		return transfertRepo.findAll();
	}

}
