package com.projetPfe.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DossierDelegue {
	@Id
	private String idDossier;
	private String anneedoss;
	private LocalDateTime  dateCre;
	private EtatDoss etatDoss;
	private LocalDateTime  datclo;
	private String motifclo;
	private LocalDate dateDebut;
	private LocalDate dateExpiration;
	private Double solde;
	private DossierDelegueType type;
	
	

	public DossierDelegue() {
		super();
	}
	
	@OneToMany(mappedBy = "dossierDelegue")
	private List<Transfert> transferts;
	
	
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
/// getters and setters	
	
	
	

}