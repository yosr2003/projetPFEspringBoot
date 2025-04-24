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
@Service
public interface TransfertServiceI {

	List<Transfert> getAll();

	Optional<Object> calculerFrais(Double montant, String deviseCible, String deviseSource, String typefrais);

	Optional<TauxChange> getTauxChangeByDevise(String devise);

	Transfert creerTransfert(Double montant, CompteBancaire compteSource, CompteBancaire compteCible,
			FraisType typeFrais, DossierDelegue dossierDelegue, String natureOperation, TransfertType type)
			throws Exception;

	List<Transfert> AlerteTransfertAttente();

	ResponseEntity<?> consulterTransfert(String refTransfert);

}
