package com.projetPfe.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class CompteBancaire {
	@Id
	private String NumeroCompte;
	
	private Double montant;
	private String Devise ;
	private String Banque;
	private String BIC;
	private String pays;
	private int codePays;
	
	
	public int getCodePays() {
		return codePays;
	}

	@ManyToOne
	@JoinColumn(name = "Participant_id")
	private Participant participant;
	
	@OneToMany(mappedBy = "CompteBancaire_cible")
	private List<Transfert> transfertsRecues;
	
	@OneToMany(mappedBy = "CompteBancaire_source")
	private List<Transfert> transfertsEnvoyes;
	
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

	public CompteBancaire() {
		super();
	}
	

}
