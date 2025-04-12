package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity
public class PersonneMorale extends Participant{
	private String codeDouane;
	private String raisonSociale;
	public String getCodeDouane() {
		return codeDouane;
	}
	public void setCodeDouane(String codeDouane) {
		this.codeDouane = codeDouane;
	}
	public String getRaisonSociale() {
		return raisonSociale;
	}
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

}
