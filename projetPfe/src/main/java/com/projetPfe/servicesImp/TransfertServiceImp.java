package com.projetPfe.servicesImp;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import com.projetPfe.repositories.TransfertPermanentRepository;
import com.projetPfe.repositories.TransfertPonctuelRepository;
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
	TransfertPermanentRepository TransfertPermanentRepo;
	@Autowired 
	TransfertPonctuelRepository TransfertPonctueltRepo;
	@Autowired 
	CompteBancaireRepository CompteBancaireRepository;
	@Autowired 
	dossierDelegueRepository DossierDelegueRepository;
	
	@Override
	public List<Transfert> getAll() {
		return TransfertRepository.findAll();
	}
	
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
	public Transfert creerTransfert(Double montant,String numeroCompteSource,String numeroCompteCible,FraisType typeFrais,String idDossierDelegue,String natureOperation,
	                             TransfertType type)   
	 
	                                
	                                
	                                
	                                 throws Exception {

	    if (numeroCompteSource == null || numeroCompteSource.isEmpty()) {
	        throw new Exception("Numéro de compte source manquant");
	    }

	    if (numeroCompteCible == null || numeroCompteCible.isEmpty()) {
	        throw new Exception("Numéro de compte cible manquant");
	    }

	    CompteBancaire compteSource = CompteBancaireRepository.findById(numeroCompteSource)
	            .orElseThrow(() -> new Exception("Compte source introuvable"));
	    CompteBancaire compteCible = CompteBancaireRepository.findById(numeroCompteCible)
	            .orElseThrow(() -> new Exception("Compte cible introuvable"));

	    DossierDelegue dossierDelegue = null;

	    if (idDossierDelegue != null && !idDossierDelegue.isEmpty()) {
	        dossierDelegue = DossierDelegueRepository.findById(idDossierDelegue)
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
	    transfert.setRefTransfert(refTransfert);
	    if (transfert instanceof TransfertPermanent tp) {
	        tp.setNatureOperation(natureOperation);
	        tp.setDossierDelegue(dossierDelegue);
	        TransfertPermanentRepo.save(tp);
	    } else if (transfert instanceof TransfertPonctuel tp) {
	        tp.setTypeTransfert(type);
	        TransfertPonctueltRepo.save(tp);
	    }

	    
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
	       Optional<Transfert> optionalTransfert = TransfertRepository.findByrefTransfert(refTransfert);
	       if (optionalTransfert.isEmpty()) {
	           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transfert introuvable");
	       }

	       Transfert transfert = optionalTransfert.get();

	       try {
	           ByteArrayOutputStream out = new ByteArrayOutputStream();
	           Document pdfDoc = new Document();
	           PdfWriter.getInstance(pdfDoc, out);
	           pdfDoc.open();

	           // Titre principal
	           Paragraph titre = new Paragraph("Détails du Transfert : " + refTransfert, new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
	           titre.setAlignment(Paragraph.ALIGN_CENTER);
	           titre.setSpacingAfter(20f);
	           pdfDoc.add(titre);

	           // Partie haute : résumé
	           String typeTransfert = transfert instanceof TransfertPonctuel ? "Ponctuel" :
	                                  transfert instanceof TransfertPermanent ? "Permanent" : "Inconnu";

	           String dateCreation = transfert.getDatecre() != null ? transfert.getDatecre().toString() : "N/A";
	           String dateEnvoi = transfert.getDateEnvoie() != null ? transfert.getDateEnvoie().toString() : "Pas encore envoyé";
	           String typeFrais = transfert.getTypeFrais() != null ? transfert.getTypeFrais().name() : "N/A";

	           Font fontLabel = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	           Font fontNormal = new Font(Font.FontFamily.HELVETICA, 12);

	           pdfDoc.add(new Paragraph("Type de Transfert : " + typeTransfert, fontLabel));
	           pdfDoc.add(new Paragraph("Date de Création : " + dateCreation, fontLabel));
	           pdfDoc.add(new Paragraph("Date d’Envoi : " + dateEnvoi, fontLabel));
	           pdfDoc.add(new Paragraph("Type de Frais : " + typeFrais, fontLabel));

	           pdfDoc.add(new Paragraph("\n"));

	           // Tableau des détails
	           PdfPTable table = new PdfPTable(2);
	           table.setWidthPercentage(100);
	           table.setSpacingBefore(10f);
	           table.setWidths(new float[]{2, 3});

	           Font fontAttribut = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	           Font fontValeur = new Font(Font.FontFamily.HELVETICA, 12);

	           ajouterLigne(table, "Réf Transfert", transfert.getRefTransfert(), fontAttribut, fontValeur);
	           ajouterLigne(table, "Montant", String.valueOf(transfert.getMontantTransfert()), fontAttribut, fontValeur);
	           ajouterLigne(table, "Montant frais", String.valueOf(transfert.getMontantFrais()), fontAttribut, fontValeur);
	           ajouterLigne(table, "Montant final", String.valueOf(transfert.getMontantFinal()), fontAttribut, fontValeur);
	           ajouterLigne(table, "État", transfert.getEtatTransfert() != null ? transfert.getEtatTransfert().name() : "N/A", fontAttribut, fontValeur);

	           if (transfert.getCompteBancaire_source() != null) {
	               ajouterLigne(table, "Compte source", transfert.getCompteBancaire_source().getNumeroCompte(), fontAttribut, fontValeur);
	           }

	           if (transfert.getCompteBancaire_cible() != null) {
	               ajouterLigne(table, "Compte cible", transfert.getCompteBancaire_cible().getNumeroCompte(), fontAttribut, fontValeur);
	           }

	           if (transfert instanceof TransfertPonctuel) {
	               TransfertPonctuel tp = (TransfertPonctuel) transfert;
	               ajouterLigne(table, "Type Transfert Ponctuel", tp.getTypeTransfert() != null ? tp.getTypeTransfert().name() : "N/A", fontAttribut, fontValeur);
	           }

	           if (transfert instanceof TransfertPermanent) {
	               TransfertPermanent tp = (TransfertPermanent) transfert;
	               ajouterLigne(table, "Nature de l'opération", tp.getNatureOperation(), fontAttribut, fontValeur);
	               if (tp.getDossierDelegue() != null) {
	                   ajouterLigne(table, "Dossier délégué lié", tp.getDossierDelegue().getIdDossier(), fontAttribut, fontValeur);
	               }
	           }

	           pdfDoc.add(table);
	           pdfDoc.close();

	           byte[] pdfBytes = out.toByteArray();
	           HttpHeaders headers = new HttpHeaders();
	           headers.setContentType(MediaType.APPLICATION_PDF);
	           headers.setContentDispositionFormData("filename", "transfert_" + refTransfert + ".pdf");

	           return ResponseEntity.ok().headers(headers).body(pdfBytes);

	       } catch (Exception e) {
	           e.printStackTrace();
	           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la génération du PDF");
	       }
	   }

	   // Utilitaire pour ajouter une ligne au tableau
	   private void ajouterLigne(PdfPTable table, String attribut, String valeur, Font fontAttribut, Font fontValeur) {
	       table.addCell(new Paragraph(attribut, fontAttribut));
	       table.addCell(new Paragraph(valeur != null ? valeur : "N/A", fontValeur));
	   }


}
