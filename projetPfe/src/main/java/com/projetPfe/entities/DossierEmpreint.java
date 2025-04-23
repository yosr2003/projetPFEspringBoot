package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity
public class DossierEmpreint extends DossierDelegue {
	
private Double montantEmpreint;

public Double getMontantEmpreint() {
	return montantEmpreint;
}

public void setMontantEmpreint(Double montantEmpreint) {
	this.montantEmpreint = montantEmpreint;
}

}
