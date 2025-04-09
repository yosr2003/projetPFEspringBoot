package com.projetPfe.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class DossierDelegue {
	@Id
	private String idDossier;
	
	private String anneedoss;
	private EtatDoss etatDoss;
    
	private LocalDateTime  datclo;
	
	private String motifclo;
	private String MotifProlong;

	private LocalDateTime  dateCre;
	private LocalDate dateDebut;
	private LocalDate dateExpiration;
    private LocalDate DateFinProlong;
	private Double solde;
	private DossierDelegueType type;
    
	@OneToMany(mappedBy = "dossierDelegue")
	private List<Transfert> transferts;
	
    
    
    
	public LocalDate getDateFinProlong() {
		return DateFinProlong;
	}

	public void setDateFinProlong(LocalDate dateFinProlong) {
		DateFinProlong = dateFinProlong;
	}

	public List<Transfert> getTransferts() {
		return transferts;
	}

	public void setTransferts(List<Transfert> transferts) {
		this.transferts = transferts;
	}


	
	

	public DossierDelegue(String idDossier, String anneedoss, EtatDoss etatDoss, LocalDateTime datclo, String motifclo,
			String motifProlong, LocalDateTime dateCre, LocalDate dateDebut, LocalDate dateExpiration,
			LocalDate dateFinProlong, Double solde, DossierDelegueType type, List<Transfert> transferts) {
		super();
		this.idDossier = idDossier;
		this.anneedoss = anneedoss;
		this.etatDoss = etatDoss;
		this.datclo = datclo;
		this.motifclo = motifclo;
		MotifProlong = motifProlong;
		this.dateCre = dateCre;
		this.dateDebut = dateDebut;
		this.dateExpiration = dateExpiration;
		DateFinProlong = dateFinProlong;
		this.solde = solde;
		this.type = type;
		this.transferts = transferts;
	}

	public DossierDelegue() {
		super();
	}


	
/// getters and setters
	
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public DossierDelegueType getType() {
		return type;
	}

	public void setType(DossierDelegueType type) {
		this.type = type;
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

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}
	public String getIdDossier() {
		return idDossier;
	}

	public void setIdDossier(String idDossier) {
		this.idDossier = idDossier;
	}

	public String getAnneedoss() {
		return anneedoss;
	}

	public void setAnneedoss(String anneedoss) {
		this.anneedoss = anneedoss;
	}

	public LocalDateTime  getDateCre() {
		return dateCre;
	}

	public void setDateCre(LocalDateTime  dateCre) {
		this.dateCre = dateCre;
	}

	public EtatDoss getEtatDoss() {
		return etatDoss;
	}

	public void setEtatDoss(EtatDoss etatDoss) {
		this.etatDoss = etatDoss;
	}

	public LocalDateTime  getDatclo() {
		return datclo;
	}

	public void setDatclo(LocalDateTime  datclo) {
		this.datclo = datclo;
	}

	public String getMotifclo() {
		return motifclo;
	}

	public void setMotifclo(String motifclo) {
		this.motifclo = motifclo;
	}
	public String getMotifProlong() {
		return MotifProlong;
	}

	public void setMotifProlong(String motifProlong) {
		MotifProlong = motifProlong;
	}
	
	

}