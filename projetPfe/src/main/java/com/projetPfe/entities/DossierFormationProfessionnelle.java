package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity

public class DossierFormationProfessionnelle extends DossierDelegue {
private String nomFormation;

public String getNomFormation() {
	return nomFormation;
}

public void setNomFormation(String nomFormation) {
	this.nomFormation = nomFormation;
}
}
