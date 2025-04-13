package com.projetPfe.services;
import com.projetPfe.entities.*;
import com.projetPfe.repositories.*;
import com.projetPfe.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TransfertService {
    @Autowired
    private TransfertRepository transfertRepo;

    @Autowired
    private TauxChangeRepository tauxchangeRepo;




    public List<Transfert> getAllTransferts() {
        return transfertRepo.findAll();
    }

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

    }
}
