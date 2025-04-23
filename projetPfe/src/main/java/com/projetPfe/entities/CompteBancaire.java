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

	@ManyToOne
	@JoinColumn(name = "Participant_id")
	private Participant participant;
	@ManyToOne
	@JoinColumn(name = "Banque_id")
	private Banque banque;
	
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



	public Banque getBanque() {
		return banque;
	}



	public void setBanque(Banque banque) {
		this.banque = banque;
	}



	public List<Transfert> getTransfertsRecues() {
		return transfertsRecues;
	}



	public void setTransfertsRecues(List<Transfert> transfertsRecues) {
		this.transfertsRecues = transfertsRecues;
	}



	public List<Transfert> getTransfertsEnvoyes() {
		return transfertsEnvoyes;
	}



	public void setTransfertsEnvoyes(List<Transfert> transfertsEnvoyes) {
		this.transfertsEnvoyes = transfertsEnvoyes;
	}


	
	
}
