package com.projetPfe.Iservice;

import java.util.List;
import java.util.Optional;

import com.projetPfe.entities.Transfert;

public interface ITansfertService {
	public List<Transfert> getAllTransferts() ;

	public Optional<Transfert> getTransfertById(String refTransfert);
	

}
