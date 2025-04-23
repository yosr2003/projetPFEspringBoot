package com.projetPfe.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DossierDelegue {
	@Id
	private String idDossier;
	
	//private String anneedoss;
	private EtatDoss etatDossier;
    
	private LocalDate  dateCloture;
	
	private String motifcloture;
	private String motifProlong;

	private LocalDateTime  dateCre;
	private LocalDate dateDebut;
	private LocalDate dateExpiration;

	@Lob
	private byte[] rapportMouvement; 

	
	@OneToMany(mappedBy = "dossierDelegue")
	private List<TransfertPermanent> transfertPermanent;


	public DossierDelegue() {
		super();
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


	public String getMotifProlong() {
		return motifProlong;
	}


	public void setMotifProlong(String motifProlong) {
		this.motifProlong = motifProlong;
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


	public byte[] getRapportMouvement() {
		return rapportMouvement;
	}


	public void setRapportMouvement(byte[] rapportMouvement) {
		this.rapportMouvement = rapportMouvement;
	}


	public List<TransfertPermanent> getTransfertPermanent() {
		return transfertPermanent;
	}


	public void setTransfertPermanent(List<TransfertPermanent> transfertPermanent) {
		this.transfertPermanent = transfertPermanent;
	}
	
    
    
    


	
}
