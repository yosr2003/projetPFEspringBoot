package com.projetPfe.Iservice;

import java.util.List;
import java.util.Optional;

import com.projetPfe.dto.TransfertDTO;
import com.projetPfe.entities.Transfert;

public interface ITansfertService {
	public List<Transfert> getAllTransferts() ;


	public Optional<TransfertDTO> getTransfertStatus(String refTransfert);

  public Optional<Object>  calculerFrais(Double montant, String deviseCible,String deviseSource , String typefrais, double montantFrais);

	/*List<Transfert> AlerteTransfertAttente();*/

	

}
