package com.projetPfe.entities;

import java.time.LocalDateTime;

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
@Override
public DossierInvestissement dupliquerAvecNouveauId(String newId) {
	DossierInvestissement copie = new DossierInvestissement();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setMotifProlong(this.getMotifProlong());
    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setSecteurActivité(this.SecteurActivité);
    return copie;
}
}
