package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity

public class DossierSoinMedical extends DossierDelegue {
private String typeTraitement;

public String getTypeTraitement() {
	return typeTraitement;
}

public void setTypeTraitement(String typeTraitement) {
	this.typeTraitement = typeTraitement;
}
}
