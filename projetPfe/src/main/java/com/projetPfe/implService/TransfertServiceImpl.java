package com.projetPfe.implService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.ITansfertService;
import com.projetPfe.dto.TransfertDTO;
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

	public Optional<Object> getTransfertStatus(String refTransfert) {
		Map<String , Object> result = new HashMap<>();

		Transfert TransfertInfo = transfertRepo.findByrefTransfert(refTransfert).get();

		System.out.println(TransfertInfo.getRefTransfert());
		System.out.println(TransfertInfo.getEtat());
		result.put("Reference Transfert", refTransfert);
		result.put("Statut Transfert",TransfertInfo.getEtat());


		return Optional.of(result);

	}

	public Optional<TauxChange> getTauxChangeByDevise(String devise) {
		return tauxchangeRepo.findByDevise(devise);
	}

	public Optional<Object> calculerFrais(Double montant, String deviseCible,String deviseSource , String typefrais , double montantFrais){
		try{
		double taux_change=0.0;
		Map<String, Object> result = new HashMap<>();
		double montantFinal = 0.0;
		double montantConverti = montant;
		if ("TND".equals(deviseSource)) {
			Optional<TauxChange> deviseCIBLEinfo =getTauxChangeByDevise(deviseCible);
			
			if (deviseCIBLEinfo.isEmpty()){
				throw new Exception("Devise cible not found");
			}
			TauxChange tauxChangeInfo = deviseCIBLEinfo.get();
			double tauxChange = tauxChangeInfo.getCoursVente();
			montantConverti *= tauxChange;
		}

		montantFinal = "BEN".equals(typefrais)? montantConverti - montantFrais : montantConverti ; 
		
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