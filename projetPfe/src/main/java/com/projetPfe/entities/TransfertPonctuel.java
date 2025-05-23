package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity
public class TransfertPonctuel extends Transfert {
	private TransfertType typeTransfert;
	public TransfertPonctuel() {
		super();
	}

	public TransfertType getTypeTransfert() {
		return typeTransfert;
	}

	

	public void setTypeTransfert(TransfertType typeTransfert) {
		this.typeTransfert = typeTransfert;
	}



	}

