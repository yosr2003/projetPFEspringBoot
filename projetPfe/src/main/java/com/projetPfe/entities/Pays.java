package com.projetPfe.entities;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Pays {
	@Id
	private int codePays;
	private String pays;
	@OneToMany(mappedBy = "pays")
	private List<Banque> banques;
	public Pays(int codePays, String pays, List<Banque> banques) {
		super();
		this.codePays = codePays;
		this.pays = pays;
		this.banques = banques;
	}
	public Pays() {
		super();
	}
	public int getCodePays() {
		return codePays;
	}
	public void setCodePays(int codePays) {
		this.codePays = codePays;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public List<Banque> getBanques() {
		return banques;
	}
	public void setBanques(List<Banque> banques) {
		this.banques = banques;
	}
	

}
