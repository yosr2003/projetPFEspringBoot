package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity
public class PersonnePhysique extends Participant{
    public String nom;
    public String prenom;
    public String cin;
    
	public PersonnePhysique() {
		super();
		
	}

////getters and setters
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
