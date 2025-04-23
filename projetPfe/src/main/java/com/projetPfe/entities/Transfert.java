package com.projetPfe.entities;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Transfert {
	@Id
	@Column(name = "ref_transfert")
	private String refTransfert;
	
	@Column(name = "montant_transfert")
	private Double montantTransfert;
	

	@Column(name = "datecre")
	private LocalDateTime datecre;
	private LocalDateTime dateEnvoie;

	public LocalDateTime getDateEnvoie() {
		return dateEnvoie;
	}


	public void setDateEnvoie(LocalDateTime dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}

	
	@Column(name = "montantFrais")
	private Double montantFrais;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_frais")
	private FraisType typeFrais;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "EtatTransfert")
	private EtatTransfert EtatTransfert;

	@Column(name="montant_final")
	private double MontantFinal;
	
	@ManyToOne
	@JoinColumn(name = "CompteBancaire_source_id")
	private CompteBancaire CompteBancaire_source;

	@ManyToOne
	@JoinColumn(name = "CompteBancaire_cible_id")
	private CompteBancaire CompteBancaire_cible;
	

	@ManyToOne
	@JoinColumn(name = "idEtatDeclaration")
	private EtatDeclarationBCT etatDeclaration;
	
	@JsonIgnore
	@OneToOne(mappedBy = "transfert")
	private Swift swift;
	
	


	public Transfert() {
		super();
	}
	

/// getters and setters 
	public String getRefTransfert() {
		return refTransfert;
	}
	
	public double getMontantFinal(){
		return this.MontantFinal;
	}
	
	public void setMontantFinal(double montantFinal){
		this.MontantFinal = montantFinal;
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





	public Double getFrais() {
		return montantFrais;
	}


	public void setFrais(Double montantFrais) {
		this.montantFrais = montantFrais;
	}


	public LocalDateTime  getDatecre() {
		return datecre;
	}


	public void setDatecre(LocalDateTime  datecre) {
		this.datecre = datecre;
	}




	public CompteBancaire getCompteBancaire_source() {
		return CompteBancaire_source;
	}

	public void setCompteBancaire_source(CompteBancaire compteBancaire_source) {
		CompteBancaire_source = compteBancaire_source;
	}

	public CompteBancaire getCompteBancaire_cible() {
		return CompteBancaire_cible;
	}

	public void setCompteBancaire_cible(CompteBancaire compteBancaire_cible) {
		CompteBancaire_cible = compteBancaire_cible;
	}


	public void setSwift(Swift swift) {
		this.swift = swift;
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

	


	public Double getMontantFrais() {
		return montantFrais;
	}


	public void setMontantFrais(Double montantFrais) {
		this.montantFrais = montantFrais;
	}


	public EtatTransfert getEtatTransfert() {
		return EtatTransfert;
	}


	public void setEtatTransfert(EtatTransfert etatTransfert) {
		EtatTransfert = etatTransfert;
	}


	public EtatDeclarationBCT getEtatDeclaration() {
		return etatDeclaration;
	}


	public void setEtatDeclaration(EtatDeclarationBCT etatDeclaration) {
		this.etatDeclaration = etatDeclaration;
	}


	public Swift getSwift() {
		return swift;
	}

	
	



}
