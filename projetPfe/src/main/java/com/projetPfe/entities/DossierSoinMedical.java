package com.projetPfe.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity

public class DossierSoinMedical extends DossierDelegue {
private String typeTraitement;

public String getTypeTraitement() {
	return typeTraitement;
}

public void setTypeTraitement(String typeTraitement) {
	this.typeTraitement = typeTraitement;
}
@Override
public DossierSoinMedical dupliquerAvecNouveauId(String newId) {
	DossierSoinMedical copie = new DossierSoinMedical();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setMotifProlong(this.getMotifProlong());
    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setTypeTraitement(this.typeTraitement);
    return copie;
}
}
