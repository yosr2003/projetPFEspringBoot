package com.projetPfe.servicesImp;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservices.TransfertServiceI;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierContratCommercial;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierEconomieSurSalaire;
import com.projetPfe.entities.DossierEmpreint;
import com.projetPfe.entities.DossierFormationProfessionnelle;
import com.projetPfe.entities.DossierInvestissement;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.entities.DossierSoinMedical;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.EtatTransfert;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.TauxChange;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertPermanent;
import com.projetPfe.entities.TransfertPonctuel;
import com.projetPfe.entities.TransfertType;
import com.projetPfe.repositories.TauxChangeRepository;
import com.projetPfe.repositories.TransfertRepository;
import com.projetPfe.repositories.dossierDelegueRepository;
import com.projetPfe.repositories.CompteBancaireRepository;
@Service
public class TransfertServiceImp implements TransfertServiceI {

	@Autowired 
	TauxChangeRepository tauxchangeRepo;
	@Autowired 
	TransfertRepository TransfertRepository;
	@Autowired 
	CompteBancaireRepository CompteBancaireRepository;
	@Autowired 
	dossierDelegueRepository DossierDelegueRepository;
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
	
	@Override
	public Transfert creerTransfert(Double montant,CompteBancaire compteSource,CompteBancaire compteCible,
	                                FraisType typeFrais,
	                                DossierDelegue dossierDelegue,
	                                String natureOperation,
	                                TransfertType type) throws Exception 
	                                {
	                                

	
	    if (compteSource == null || compteSource.getNumeroCompte() == null) {
	        throw new Exception("Numéro de compte source manquant");
	    }

	    if (compteCible == null || compteCible.getNumeroCompte() == null) {
	        throw new Exception("Numéro de compte cible manquant");
	    }

	
	    compteSource = CompteBancaireRepository.findById(compteSource.getNumeroCompte())
	            .orElseThrow(() -> new Exception("Compte source introuvable"));
	    compteCible = CompteBancaireRepository.findById(compteCible.getNumeroCompte())
	            .orElseThrow(() -> new Exception("Compte cible introuvable"));

	    if (dossierDelegue != null && dossierDelegue.getIdDossier() != null) {
	        dossierDelegue = DossierDelegueRepository.findById(dossierDelegue.getIdDossier())
	                .orElseThrow(() -> new Exception("Dossier Délégué introuvable"));
	    }


	    String prefix = "TR";

	    if (dossierDelegue != null) {
	        if (dossierDelegue instanceof DossierScolarité) {
	            prefix = "TSC";
	        } else if (dossierDelegue instanceof DossierInvestissement) {
	            prefix = "TINV";
	        } else if (dossierDelegue instanceof DossierSoinMedical) {
	            prefix = "TSM";
	        } else if (dossierDelegue instanceof DossierEconomieSurSalaire) {
	            prefix = "TES";
	        } else if (dossierDelegue instanceof DossierContratCommercial) {
	            prefix = "TCC";
	        } else if (dossierDelegue instanceof DossierFormationProfessionnelle) {
	            prefix = "TFP";
	        } else if (dossierDelegue instanceof DossierEmpreint) {
	            prefix = "TEE";
	        }
	    }


	    String refTransfert = prefix + String.valueOf((int) (Math.random() * 1000000));


	    Transfert transfert = (dossierDelegue != null)
	            ? new TransfertPermanent()
	            : new TransfertPonctuel();

	    if (transfert instanceof TransfertPermanent tp) {
	        tp.setNatureOperation(natureOperation);
	        tp.setDossierDelegue(dossierDelegue);
	    } else if (transfert instanceof TransfertPonctuel tp) {
	        tp.setTypeTransfert(type);
	    }

	    transfert.setRefTransfert(refTransfert);
	    transfert.setDatecre(LocalDateTime.now());
	    transfert.setDateEnvoie(LocalDateTime.now());
	    transfert.setMontantTransfert(montant);
	    transfert.setTypeFrais(typeFrais);
	    transfert.setCompteBancaire_source(compteSource);
	    transfert.setCompteBancaire_cible(compteCible);
	    transfert.setEtatTransfert(EtatTransfert.TRAITEMENT);


	    TauxChange deviseSource = compteSource.getDevise();
	    TauxChange deviseCible = compteCible.getDevise();

	    if (deviseSource == null || deviseCible == null) {
	        throw new Exception("Devise source ou cible non définie.");
	    }

	    Optional<Object> result = calculerFrais(montant, deviseCible.getDevise(), deviseSource.getDevise(), typeFrais.name());

	    if (result.isPresent()) {
	        Map<String, Object> data = (Map<String, Object>) result.get();
	        transfert.setFrais((Double) data.get("montantFrais"));
	        transfert.setMontantFinal((Double) data.get("montantFinal"));
	    } else {
	        throw new Exception("Erreur lors du calcul des frais.");
	    }

	    return TransfertRepository.save(transfert);
	}


	   @Override
 public List<Transfert> AlerteTransfertAttente() {
     return TransfertRepository.findByEtatTransfert(EtatTransfert.TRAITEMENT);
 }



	@Override
	public ResponseEntity<?> consulterTransfert(String refTransfert) {
		Optional<Transfert> t=TransfertRepository.findByrefTransfert(refTransfert);
		if(t.isPresent()) {
			return ResponseEntity.ok()
	                .body(t);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transfert introuvable");
	}
	
}
