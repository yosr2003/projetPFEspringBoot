package com.projetPfe.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Pays {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codePays;
	 @Column(nullable = false)
	private String pays;
	 @JsonIgnore
	@OneToMany(mappedBy = "pays")
	private List<Banque> banques;
	public Pays(Long codePays, String pays, List<Banque> banques) {
		super();
		this.codePays = codePays;
		this.pays = pays;
		this.banques = banques;
	}
	
	public Pays(String pays) {
		super();
		this.pays = pays;
	}
	


	public Pays() {
		super();
	}

	public Long getCodePays() {
		return codePays;
	}
	public void setCodePays(Long codePays) {
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
