package com.projetPfe.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TransfertPermanent extends Transfert {
private String natureOperation;
@ManyToOne
@JoinColumn(name = "DossierDelegue_id")
private DossierDelegue dossierDelegue;

public String getNatureOperation() {
	return natureOperation;
}

public void setNatureOperation(String natureOperation) {
	this.natureOperation = natureOperation;
}

public TransfertPermanent() {
	super();
}

public DossierDelegue getDossierDelegue() {
	return dossierDelegue;
}

public void setDossierDelegue(DossierDelegue dossierDelegue) {
	this.dossierDelegue = dossierDelegue;
}

}
