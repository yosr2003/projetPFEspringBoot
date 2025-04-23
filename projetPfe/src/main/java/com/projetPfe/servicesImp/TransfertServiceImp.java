package com.projetPfe.servicesImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservices.TransfertServiceI;
import com.projetPfe.entities.TauxChange;
import com.projetPfe.entities.Transfert;
import com.projetPfe.repositories.TauxChangeRepository;
import com.projetPfe.repositories.TransfertRepository;
@Service
public class TransfertServiceImp implements TransfertServiceI {

	@Autowired 
	TauxChangeRepository tauxchangeRepo;
	@Autowired 
	TransfertRepository TransfertRepository;
	
	@Override
	public Optional<TauxChange> getTauxChangeByDevise(String devise) {
		return tauxchangeRepo.findByDevise(devise);
	}

	@Override
	public Optional<Object> calculerFrais(Double montant, String deviseCible, String deviseSource, String typefrais) {
		 try {
		        double taux_change = 0.0;
		        Map<String, Object> result = new HashMap<>();
		        double montantFinal;
		        double montantConverti;

		        if (deviseSource.equals("TND")) {
		            Optional<TauxChange> deviseCIBLEinfo = Optional.of(
		                getTauxChangeByDevise(deviseCible)
		                    .orElseThrow(() -> new Exception("Devise cible not found"))
		            );

		            montantConverti = montant * deviseCIBLEinfo.map(TauxChange::getCoursVente).orElse(0.0);
		            taux_change = deviseCIBLEinfo.map(TauxChange::getCoursVente).get();
		        } else {
		            montantConverti = montant;
		        }

		        double montantFrais = typefrais.equals("BEN") ? 50.0 : 0.0;
		        montantFinal = montantConverti - montantFrais;

		        result.put("montantInitial", montant);
		        result.put("deviseCible", deviseCible);
		        result.put("deviseSource", deviseSource);
		        result.put("tauxChange", taux_change);
		        result.put("montantConverti", montantConverti);
		        result.put("typeFrais", typefrais);
		        result.put("montantFrais", montantFrais);
		        result.put("montantFinal", montantFinal);

		        return Optional.of(result);
		    } catch (Exception e) {
		        System.err.println("Erreur lors du calcul des frais : " + e.getMessage());
		        return Optional.empty();
		    }
	}

	@Override
	public List<Transfert> getAll() {
		return TransfertRepository.findAll();
	}
	
}
