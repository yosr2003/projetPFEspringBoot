package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity
public class PersonnePhysique extends Participant{

    private String nom;
    private String prenom;
    private String cin;
	public PersonnePhysique() {
		super();
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	
    
    

}
