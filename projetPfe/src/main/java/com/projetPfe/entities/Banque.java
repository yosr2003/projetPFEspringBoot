package com.projetPfe.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Banque {
	@Id	
	private String BIC;
	private String Banque;
	@JsonIgnore
	@OneToMany(mappedBy = "banque")
	private List<CompteBancaire> CompteBancaires;
	@ManyToOne
	@JoinColumn(name = "pays_id")
	private Pays pays;
	
	
	public Banque(String bIC, String banque, List<CompteBancaire> compteBancaires, Pays pays) {
		super();
		BIC = bIC;
		Banque = banque;
		CompteBancaires = compteBancaires;
		this.pays = pays;
	}
	public Banque() {
		super();
	}

	public Banque(String bIC, String banque, Pays pays) {
		super();
		BIC = bIC;
		Banque = banque;
		this.pays = pays;
	}
	public String getBIC() {
		return BIC;
	}
	public void setBIC(String bIC) {
		BIC = bIC;
	}
	public String getBanque() {
		return Banque;
	}
	public void setBanque(String banque) {
		Banque = banque;
	}
	public List<CompteBancaire> getCompteBancaires() {
		return CompteBancaires;
	}
	public void setCompteBancaires(List<CompteBancaire> compteBancaires) {
		CompteBancaires = compteBancaires;
	}
	public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}
	
	
}
