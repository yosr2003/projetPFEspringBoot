package com.projetPfe.entities;

import java.util.List;


import jakarta.persistence.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class CompteBancaire {
	@Id
	private String NumeroCompte;
	
	private Double montant;
	//private String Devise ;
	private String Banque;
	private String BIC;
	private String pays;
	private int codePays;
	
	
	
///relations
	@ManyToOne
	@JoinColumn(name = "Participant_id")
	private Participant participant;
	
	@OneToMany(mappedBy = "CompteBancaire_cible")
	private List<Transfert> transfertsRecues;
	
	@OneToMany(mappedBy = "CompteBancaire_source")
	private List<Transfert> transfertsEnvoyes;
	
	@ManyToOne
	@JoinColumn(name = "devise_id")
	private TauxChange devise;
///relations
	
	///constructors
	public CompteBancaire() {
		super();
	}

	public CompteBancaire(String numeroCompte, Double montant, String banque, String bIC, String pays,
			Participant participant) {
		super();
		NumeroCompte = numeroCompte;
		this.montant = montant;

		Banque = banque;
		BIC = bIC;
		this.pays = pays;
		this.participant = participant;
	}
	///constructors

///getters and setters
	public String getNumeroCompte() {
		return NumeroCompte;
	}
	public TauxChange getDevise() {
		return devise;
	}
	public void setDevise(TauxChange devise) {
		this.devise = devise;
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
	public int getCodePays() {
		return codePays;
	}

	
	
}
