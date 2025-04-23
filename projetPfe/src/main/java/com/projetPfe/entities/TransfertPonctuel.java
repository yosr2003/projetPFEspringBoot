package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity
public class TransfertPonctuel extends Transfert {
	private TransfertType typeTransfert;

	public TransfertType getTypeTransfert() {
		return typeTransfert;
	}

	public TransfertPonctuel() {
		super();
	}

	public void setTypeTransfert(TransfertType typeTransfert) {
		this.typeTransfert = typeTransfert;
	}



	}

