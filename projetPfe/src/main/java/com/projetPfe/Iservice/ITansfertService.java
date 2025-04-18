package com.projetPfe.Iservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;


import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertType;

public interface ITansfertService {
	public	List<Transfert> getAll();


	public ResponseEntity<?> consulterTransfert(String refTransfert);


  	public	List<Transfert> AlerteTransfertAttente();

	public Transfert creerTransfert(Double montant, CompteBancaire compteSource, CompteBancaire compteCible,
			FraisType typeFrais, DossierDelegue dossierDelegue,String natureOperation,TransfertType type) throws Exception;



	public Optional<Object> calculerFrais(Double montant, String deviseCible, String deviseSource, String typefrais);






	

}
