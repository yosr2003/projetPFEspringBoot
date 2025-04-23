package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity
public class TransfertPermanent extends Transfert {
private String natureOperation;

public String getNatureOperation() {
	return natureOperation;
}

public void setNatureOperation(String natureOperation) {
	this.natureOperation = natureOperation;
}

}
