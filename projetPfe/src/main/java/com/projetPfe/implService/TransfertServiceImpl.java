package com.projetPfe.implService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.ITansfertService;

import com.projetPfe.dto.TauxChangeDTO;
import com.projetPfe.dto.TransfertDTO;

import com.projetPfe.entities.EtatDoss;

import com.projetPfe.entities.Transfert;
import com.projetPfe.repositories.TauxChangeRepository;
import com.projetPfe.repositories.TransfertRepository;
import com.projetPfe.entities.TauxChange;


@Service
public class TransfertServiceImpl implements ITansfertService {
	@Autowired
	private TransfertRepository transfertRepo;

	@Autowired
	private TauxChangeRepository tauxchangeRepo;



	@Override
	public List<Transfert> getAllTransferts() {
		return transfertRepo.findAll();
	}
	   @Override
    public List<Transfert> AlerteTransfertAttente() {
        return transfertRepo.findByEtat(EtatDoss.Traitement);
    }

	public Optional<TransfertDTO> getTransfertStatus(String refTransfert) {
		return transfertRepo.findByrefTransfert(refTransfert)
						.map(transfert -> new TransfertDTO(transfert.getRefTransfert(), transfert.getEtat()));
	}
	public Optional<TauxChange> getTauxChangeByDevise(String devise) {
		return tauxchangeRepo.findByDevise(devise);
	}

	public Optional<Object> calculerFrais(Double montant, String deviseCible,String deviseSource , String typefrais , double montantFrais){
		try{
		double taux_change=0.0;
		Map<String, Object> result = new HashMap<>();
		double montantFinal = 0.0;
		double montantConverti;
		if (deviseSource.equals("TND")) {
			Optional<TauxChange> deviseCIBLEinfo =Optional.of(getTauxChangeByDevise(deviseCible).orElseThrow(() -> new Exception("Devise cible not found")));
			
			montantConverti = (montant*deviseCIBLEinfo.map(TauxChange::getCoursVente).orElse(0.0));
			taux_change = deviseCIBLEinfo.map(TauxChange::getCoursVente).get();
		}else{
		montantConverti = montant;
		}

		if (typefrais.equals("BEN")){
			montantFinal = montantConverti - montantFrais;
		}else{
			montantFinal = montantConverti;
		}
		
		result.put("montantInitial",montant);
		result.put("DeviseCible",deviseCible);
		result.put("deviseSource",deviseSource);
		result.put("TauxChange",taux_change);
		result.put("montantConverti",montantConverti);
		result.put("typeFrais",typefrais);
		result.put("montantFrais",montantFrais);
		result.put("montantFinal",montantFinal);

		return Optional.of(result);
	}
		catch(Exception e){
			System.err.println(e.getMessage());
			return Optional.empty();
		}

	}}