package com.projetPfe.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity

public class DossierFormationProfessionnelle extends DossierDelegue {
private String nomFormation;

public String getNomFormation() {
	return nomFormation;
}

public void setNomFormation(String nomFormation) {
	this.nomFormation = nomFormation;
}
@Override
public DossierFormationProfessionnelle dupliquerAvecNouveauId(String newId) {
	DossierFormationProfessionnelle copie = new DossierFormationProfessionnelle();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setMotifProlong(this.getMotifProlong());
    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setNomFormation(this.nomFormation);
    return copie;
}
}
