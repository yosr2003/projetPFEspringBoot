package com.projetPfe.implService;

import java.time.LocalDateTime;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.ITansfertService;
import com.projetPfe.dto.TransfertDTO;

import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.TauxChange;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertNature;
import com.projetPfe.entities.TransfertType;
import com.projetPfe.repositories.CompteBancaireRepository;
import com.projetPfe.repositories.TauxChangeRepository;
import com.projetPfe.repositories.TransfertRepository;

import com.projetPfe.repositories.DossierDelegueRepository;
@Service
public class TransfertServiceImpl implements ITansfertService {
	@Autowired
	private TransfertRepository transfertRepo;

	@Autowired
	private TauxChangeRepository tauxchangeRepo;

	 @Autowired
	 private TransfertRepository transfertRepository;

	 @Autowired
	 private CompteBancaireRepository compteBancaireRepository;
	 @Autowired
	 private DossierDelegueRepository DossierDelegueRepository;
	    
	 @Override
	 public Transfert creerTransfert(Double montant, CompteBancaire compteSource, CompteBancaire compteCible,
	                                    FraisType typeFrais, DossierDelegue dossierDelegue) throws Exception {

	        // ⚠️ Validation des IDs
	        if (compteSource == null || compteSource.getNumeroCompte() == null) {
	            throw new Exception("Numéro de compte source manquant");
	        }

	        if (compteCible == null || compteCible.getNumeroCompte() == null) {
	            throw new Exception("Numéro de compte cible manquant");
	        }

	        // 🔄 Rechargement des comptes complets
	        compteSource = compteBancaireRepository.findById(compteSource.getNumeroCompte())
	                .orElseThrow(() -> new Exception("Compte source introuvable"));

	        compteCible = compteBancaireRepository.findById(compteCible.getNumeroCompte())
	                .orElseThrow(() -> new Exception("Compte cible introuvable"));

	        // 🔄 Recharger le dossier délégué s'il est présent
	        if (dossierDelegue != null && dossierDelegue.getIdDossier() != null) {
	            dossierDelegue = DossierDelegueRepository.findById(dossierDelegue.getIdDossier())
	                    .orElseThrow(() -> new Exception("Dossier Délégué introuvable"));
	        }

	        // 📄 Création du transfert
	        Transfert transfert = new Transfert();

	        // Génération référence avec préfixe selon le type de dossier
	        String prefix = "TR";
	        if (dossierDelegue != null && dossierDelegue.getType() != null) {
	            switch (dossierDelegue.getType()) {
	                case SCOLARITE: prefix = "TSC"; break;
	                case INVESTISSEMENT: prefix = "TINV"; break;
	                case SOIN_MEDICAL: prefix = "TSM"; break;
	                case ECONOMIE_SUR_SALAIRE: prefix = "TES"; break;
	                case CONTRAT_COMMERCIALE: prefix = "TCC"; break;
	                case FORMATION_PROFESSIONEL: prefix = "TFP"; break;
	                case EMPREINT_EXTERIEUR: prefix = "TEE"; break;
	                default: break;
	            }
	        }

	        String refTransfert = prefix + String.valueOf((int) (Math.random() * 1000000));
	        transfert.setRefTransfert(refTransfert);

	        // Nature du transfert
	        transfert.setNatureTransfert(dossierDelegue != null ? TransfertNature.PERMANENT : TransfertNature.COURANT);

	        // Infos de base
	        transfert.setDatecre(LocalDateTime.now());
	        transfert.setEtat(EtatDoss.TRAITEMENT);
	        transfert.setMontantTransfert(montant);
	        transfert.setTypeFrais(typeFrais);
	        transfert.setTypeTransfert(TransfertType.FINANCIER);

	        // Dévises
	        TauxChange deviseSource = compteSource.getDevise();
	        TauxChange deviseCible = compteCible.getDevise();

	        if (deviseSource == null || deviseCible == null) {
	            throw new Exception("Devise source ou cible non définie.");
	        }

	        // Calcul des frais
	        Optional<Object> result = calculerFrais(montant, deviseCible.getDevise(), deviseSource.getDevise(), typeFrais.name());

	        if (result.isPresent()) {
	            Map<String, Object> data = (Map<String, Object>) result.get();
	            transfert.setFrais((Double) data.get("montantFrais"));
	            transfert.setMontantFinal((Double) data.get("montantFinal"));
	        } else {
	            throw new Exception("Erreur lors du calcul des frais.");
	        }

	        // Liaison des entités
	        transfert.setCompteBancaire_source(compteSource);
	        transfert.setCompteBancaire_cible(compteCible);
	        transfert.setDossierDelegue(dossierDelegue); // Peut être null

	        // 💾 Sauvegarde
	        return transfertRepository.save(transfert);
	    }

	   @Override
    public List<Transfert> AlerteTransfertAttente() {
        return transfertRepo.findByEtat(EtatDoss.TRAITEMENT);
    }



	@Override
	public Optional<TransfertDTO> getTransfertStatus(String refTransfert) {
		return transfertRepo.findByrefTransfert(refTransfert)
						.map(transfert -> new TransfertDTO(transfert.getRefTransfert(), transfert.getEtat()));
	}
	
	
	public Optional<TauxChange> getTauxChangeByDevise(String devise) {
		return tauxchangeRepo.findByDevise(devise);
	}

	@Override
	public Optional<Object> calculerFrais(Double montant, String deviseCible, String deviseSource, String typefrais) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	


	



	
	
	
	
	
	
	
	
	
	}
