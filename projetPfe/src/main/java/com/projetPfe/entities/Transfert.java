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
	@Column(name = "ref_transfert")
	private String refTransfert;
	private String natureJuridique;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type_transfert")
	private TransfertType typeTransfert;

	@Column(name = "montant_transfert")
	private Double montantTransfert;
	
	@ManyToOne
	@JoinColumn(name = "CompteBancaire_source_id")
	private CompteBancaire CompteBancaire_source;

	@ManyToOne
	@JoinColumn(name = "CompteBancaire_cible_id")
	private CompteBancaire CompteBancaire_cible;

	@ManyToOne
	@JoinColumn(name = "devise_source_id")
	private TauxChange deviseSource;

	@ManyToOne
	@JoinColumn(name = "devise_cible_id")
	private TauxChange deviseCible;

	@Column(name = "datecre")
	private LocalDateTime  datecre;

	@Enumerated(EnumType.STRING)
	@Column(name = "etat")
	private EtatDoss etat;
	public TransfertType getTypeTransfert() {
		return typeTransfert;
	}

	public DossierDelegue getDossierDelegue() {
		return dossierDelegue;
	}

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

	@Column(name="montant_final")
	private double MontantFinal;


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
	
	public void setDossierDelegue(DossierDelegue dossierDelegue) {
		this.dossierDelegue = dossierDelegue;
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

	public String getNatureJuridique() {
		return natureJuridique;
	}

	public void setNatureJuridique(String natureJuridique) {
		this.natureJuridique = natureJuridique;
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
	
/// getters and setters 

}
