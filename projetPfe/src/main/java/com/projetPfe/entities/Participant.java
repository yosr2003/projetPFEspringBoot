package com.projetPfe.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Participant {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matriculePartic;
	private String intitulé;
	private String pays;
	private String adresse;
	private String email;
	private int numTel;
	public Long getMatriculePartic() {
		return matriculePartic;
	}
	public void setMatriculePartic(Long matriculePartic) {
		this.matriculePartic = matriculePartic;
	}
	public String getIntitulé() {
		return intitulé;
	}
	public void setIntitulé(String intitulé) {
		this.intitulé = intitulé;
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
	public Participant(Long matriculePartic, String intitulé, String pays, String adresse, String email, int numTel) {
		super();
		this.matriculePartic = matriculePartic;
		this.intitulé = intitulé;
		this.pays = pays;
		this.adresse = adresse;
		this.email = email;
		this.numTel = numTel;
	}
	public Participant() {
		super();
	}
	

}
