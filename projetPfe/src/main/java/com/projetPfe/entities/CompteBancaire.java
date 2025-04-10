package com.projetPfe.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class CompteBancaire {
	
	@Id
	private String NumeroCompte;
	
	private Double montant;
	private String Devise ;
	private String Banque;
	private String BIC;
	private String pays;
	@ManyToOne
	@JoinColumn(name = "Participant_id")
	private Participant participant;
	public String getNumeroCompte() {
		return NumeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		NumeroCompte = numeroCompte;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public String getDevise() {
		return Devise;
	}
	public void setDevise(String devise) {
		Devise = devise;
	}
	public Participant getParticipant() {
		return participant;
	}
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	public String getBanque() {
		return Banque;
	}
	public void setBanque(String banque) {
		Banque = banque;
	}
	public String getBIC() {
		return BIC;
	}
	public void setBIC(String bIC) {
		BIC = bIC;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public CompteBancaire(String numeroCompte, Double montant, String devise, String banque, String bIC, String pays) {
		super();
		NumeroCompte = numeroCompte;
		this.montant = montant;
		Devise = devise;
		Banque = banque;
		BIC = bIC;
		this.pays = pays;
	}
	public CompteBancaire() {
		super();
	}
	public CompteBancaire(String numeroCompte, Double montant, String devise, String banque, String bIC, String pays,
			Participant participant) {
		super();
		NumeroCompte = numeroCompte;
		this.montant = montant;
		Devise = devise;
		Banque = banque;
		BIC = bIC;
		this.pays = pays;
		this.participant = participant;
	}
	
	
}
