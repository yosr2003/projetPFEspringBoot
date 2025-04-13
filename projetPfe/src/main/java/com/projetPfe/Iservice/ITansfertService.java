package com.projetPfe.Iservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.projetPfe.dto.TransfertDTO;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertType;

public interface ITansfertService {
	public List<Transfert> getAllTransferts() ;


	public Optional<TransfertDTO> getTransfertStatus(String refTransfert);




  	public	List<Transfert> AlerteTransfertAttente();





	Transfert creerTransfert(Double montant, CompteBancaire compteSource, CompteBancaire compteCible,
			FraisType typeFrais, DossierDelegue dossierDelegue) throws Exception;



	Optional<Object> calculerFrais(Double montant, String deviseCible, String deviseSource, String typefrais);




	

}
