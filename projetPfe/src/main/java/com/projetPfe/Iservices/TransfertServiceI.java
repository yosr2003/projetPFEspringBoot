package com.projetPfe.Iservices;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.TauxChange;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertType;

public interface TransfertServiceI {

	public List<Transfert> getAll();

	public Optional<Object> calculerFrais(Double montant, String deviseCible, String deviseSource, String typefrais);

	public Optional<TauxChange> getTauxChangeByDevise(String devise);

	public List<Transfert> AlerteTransfertAttente();

	public ResponseEntity<?> consulterTransfert(String refTransfert);



	public Transfert creerTransfert(Double montant, String numeroCompteSource, String numeroCompteCible, FraisType typeFrais,
			String idDossierDelegue, String natureOperation, TransfertType type) throws Exception;



	

}
