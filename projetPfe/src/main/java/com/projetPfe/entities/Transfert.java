package com.projetPfe.entities;

import java.io.Serializable;
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
public class Transfert implements Serializable{
	@Id
	@Column(name = "ref_transfert")
	private String refTransfert;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type_transfert")
	private TransfertType typeTransfert;

	@Column(name = "montant_transfert")
	private Double montantTransfert;

	@Column(name ="devise_source_id")
	private TauxChange deviseSource;

	@Column(name = "devise_cible_id")
	private TauxChange deviseCible;

	@Column(name = "datecre")
	private LocalDateTime  datecre;

	@Enumerated(EnumType.STRING)
	@Column(name = "etat")
	private EtatDoss etat;
	@Column(name = "date_echeance")
	private LocalDate dateEcheance;

	@Enumerated(EnumType.STRING)
	@Column(name = "nature_transfert")
	private TransfertNature natureTransfert;
	
	@Column(name = "frais")
	private Double frais;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_frais")
	private FraisType typeFrais;



	public Transfert() {
		super();
	}
	
	@ManyToOne
	@JoinColumn(name = "idDossier")
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


	public TauxChange getDeviseSource() {
		return this.deviseSource;
	}

	public TauxChange getDeviseCible() {
		return this.deviseCible;
	}


	public void setDeviseSource(TauxChange devise) {
		this.deviseSource = devise;
	}

	public void setDeviseCible(TauxChange devise) {
		this.deviseCible = devise;
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

	public FraisType getTypeFrais(){
		return this.typeFrais;
	}

	public void setTypeFrais(FraisType type){
		this.typeFrais = type;
	}


	public ResponseEntity<Transfert> orElseGet(Object object) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'orElseGet'");
	}
	
/// getters and setters 

}
