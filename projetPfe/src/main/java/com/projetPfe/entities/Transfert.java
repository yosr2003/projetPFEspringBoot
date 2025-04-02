package com.projetPfe.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Transfert {
	@Id
	private String refTransfert;
	private Double montantTransfert;
	private String deviseTransfert;
	private LocalDateTime  datecre;
	private EtatDoss etat;
	private LocalDate dateEcheance;
	private String natureTransfert;
	private Double frais;

	public Transfert() {
		super();
	}
	
	@ManyToOne
	@JoinColumn(name = "idDossDelegue")
	private DossierDelegue dossierDelegue;
	
	
	@OneToOne(mappedBy = "transfert")
	private Swift swift;
	

/// getters and setters 
	public String getRefTransfert() {
		return refTransfert;
	}


	public void setRefTransfert(String refTransfert) {
		this.refTransfert = refTransfert;
	}


	public Double getMontantTransfert() {
		return montantTransfert;
	}


	public void setMontantTransfert(Double montantTransfert) {
		this.montantTransfert = montantTransfert;
	}


	public String getDeviseTransfert() {
		return deviseTransfert;
	}


	public void setDeviseTransfert(String deviseTransfert) {
		this.deviseTransfert = deviseTransfert;
	}


	public LocalDate getDateEcheance() {
		return dateEcheance;
	}


	public void setDateEcheance(LocalDate dateEcheance) {
		this.dateEcheance = dateEcheance;
	}


	public String getNatureTransfert() {
		return natureTransfert;
	}


	public void setNatureTransfert(String natureTransfert) {
		this.natureTransfert = natureTransfert;
	}


	public Double getFrais() {
		return frais;
	}


	public void setFrais(Double frais) {
		this.frais = frais;
	}


	public LocalDateTime  getDatecre() {
		return datecre;
	}


	public void setDatecre(LocalDateTime  datecre) {
		this.datecre = datecre;
	}


	public EtatDoss getEtat() {
		return etat;
	}


	public void setEtat(EtatDoss etat) {
		this.etat = etat;
	}
	
/// getters and setters 

}
