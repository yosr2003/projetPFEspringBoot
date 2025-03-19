package com.projetPfe.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "transfert")
public class Transfert {
	@Id
	@Column(name = "refTransfert")
	private String refTransfert;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "typeTransfert")
	private TransfertType typeTransfert;

	@Column(name = "montantTransfert")
	private Double montantTransfert;

	@Column(name = "deviseTransfert")
	private String deviseTransfert;

	@Column(name = "datecre")
	private LocalDateTime  datecre;

	@Enumerated(EnumType.STRING)
	@Column(name = "etat")
	private EtatDoss etat;
	@Column(name = "dateEcheance")
	private LocalDate dateEcheance;

	@Enumerated(EnumType.STRING)
	@Column(name = "natureTransfert")
	private TransfertNature natureTransfert;
	
	@Column(name = "frais")
	private Double frais;

	public Transfert() {
		super();
	}
	
	/*@ManyToOne
	@JoinColumn(name = "idDossDelegue")
	private DossierDelegue dossierDelegue;
	
	
	@OneToOne(mappedBy = "transfert")
	private Swift swift;
	*/

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


	public TransfertNature getNatureTransfert() {
		return natureTransfert;
	}


	public void setNatureTransfert(TransfertNature natureTransfert) {
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


	public ResponseEntity<Transfert> orElseGet(Object object) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'orElseGet'");
	}
	
/// getters and setters 

}
