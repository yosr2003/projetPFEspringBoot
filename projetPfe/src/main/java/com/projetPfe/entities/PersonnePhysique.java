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
	
    
    

}
