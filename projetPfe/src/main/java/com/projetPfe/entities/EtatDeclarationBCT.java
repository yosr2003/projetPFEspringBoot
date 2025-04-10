package com.projetPfe.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class EtatDeclarationBCT {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEtatDeclaration;
	
	@Lob
	private String contenuTexte;

	public EtatDeclarationBCT() {
		super();
	}

	public void setContenuTexte(String contenuTexte) {
		this.contenuTexte = contenuTexte;
	}

	public String getContenuTexte() {
		return contenuTexte;
	}


}
