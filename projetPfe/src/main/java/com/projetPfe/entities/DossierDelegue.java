

package com.projetPfe.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DossierDelegue {
	@Id
	private String idDossier;
	
	//private String anneedoss;
	private EtatDoss etatDossier;
    
	private LocalDate  dateCloture;
	
	private String motifcloture;


	private LocalDateTime  dateCre;
	private LocalDate dateDebut;
	private LocalDate dateExpiration;

	

	@JsonIgnore
	@OneToMany(mappedBy = "dossierDelegue")
	private List<TransfertPermanent> transfertPermanent;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rapport_id")
    private RapportMouvementsFinanciers rapportMouvementFinanciers;

	

	public DossierDelegue() {
		super();
	}

	

	public RapportMouvementsFinanciers getRapportMouvementFinanciers() {
		return rapportMouvementFinanciers;
	}



	public void setRapportMouvementFinanciers(RapportMouvementsFinanciers rapportMouvementFinanciers) {
		this.rapportMouvementFinanciers = rapportMouvementFinanciers;
	}



	public String getIdDossier() {
		return idDossier;
	}


	public void setIdDossier(String idDossier) {
		this.idDossier = idDossier;
	}


	public EtatDoss getEtatDossier() {
		return etatDossier;
	}


	public void setEtatDossier(EtatDoss etatDossier) {
		this.etatDossier = etatDossier;
	}


	public LocalDate getDateCloture() {
		return dateCloture;
	}


	public void setDateCloture(LocalDate dateCloture) {
		this.dateCloture = dateCloture;
	}


	public String getMotifcloture() {
		return motifcloture;
	}


	public void setMotifcloture(String motifcloture) {
		this.motifcloture = motifcloture;
	}





	public LocalDateTime getDateCre() {
		return dateCre;
	}


	public void setDateCre(LocalDateTime dateCre) {
		this.dateCre = dateCre;
	}


	public LocalDate getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}


	public LocalDate getDateExpiration() {
		return dateExpiration;
	}


	public void setDateExpiration(LocalDate dateExpiration) {
		this.dateExpiration = dateExpiration;
	}





	public List<TransfertPermanent> getTransfertPermanent() {
		return transfertPermanent;
	}


	public void setTransfertPermanent(List<TransfertPermanent> transfertPermanent) {
		this.transfertPermanent = transfertPermanent;
	}
	
    
    
	public abstract DossierDelegue dupliquerAvecNouveauId(String newId);
	public abstract void ajouterInfosSpecifiquesAuRapport(Document doc) throws DocumentException;


	
}
