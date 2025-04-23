package com.projetPfe.entities;

import jakarta.persistence.Entity;

@Entity

public class DossierInvestissement extends DossierDelegue {
private String SecteurActivité;

public String getSecteurActivité() {
	return SecteurActivité;
}

public void setSecteurActivité(String secteurActivité) {
	SecteurActivité = secteurActivité;
}
}
