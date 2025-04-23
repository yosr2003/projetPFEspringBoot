package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity

public class DossierScolarité extends DossierDelegue{
private String niveauEtude;

public String getNiveauEtude() {
	return niveauEtude;
}

public void setNiveauEtude(String niveauEtude) {
	this.niveauEtude = niveauEtude;
}


}
