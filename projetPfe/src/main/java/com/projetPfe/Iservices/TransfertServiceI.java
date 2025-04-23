package com.projetPfe.Iservices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetPfe.entities.TauxChange;
import com.projetPfe.entities.Transfert;
@Service
public interface TransfertServiceI {

	List<Transfert> getAll();

	Optional<Object> calculerFrais(Double montant, String deviseCible, String deviseSource, String typefrais);

	Optional<TauxChange> getTauxChangeByDevise(String devise);

}
