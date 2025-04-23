package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity

public class DossierContratCommercial extends DossierDelegue {
private Double montantContrat;

public Double getMontantContrat() {
	return montantContrat;
}

public void setMontantContrat(Double montantContrat) {
	this.montantContrat = montantContrat;
}

}
