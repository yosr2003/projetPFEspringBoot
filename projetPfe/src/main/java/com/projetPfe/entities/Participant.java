package com.projetPfe.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToMany;

import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Participant {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matriculePartic;
	private String pays;
	private String adresse;
	private String email;
	private int numTel;
	
	@OneToMany(mappedBy = "participant")
	private List<CompteBancaire> compteBancaires;
	
	public Long getMatriculePartic() {
		return matriculePartic;
	}
	public void setMatriculePartic(Long matriculePartic) {
		this.matriculePartic = matriculePartic;
	}

	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumTel() {
		return numTel;
	}
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public Participant() {
		super();
	}
	

}
