package com.projetPfe.entities;

import java.time.LocalDateTime;

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
@Override
public DossierContratCommercial dupliquerAvecNouveauId(String newId) {
	DossierContratCommercial copie = new DossierContratCommercial();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setMotifProlong(this.getMotifProlong());
    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setMontantContrat(this.montantContrat);
    return copie;
}

}
