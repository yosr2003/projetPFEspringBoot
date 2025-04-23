package com.projetPfe.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity

public class DossierEconomieSurSalaire extends DossierDelegue  {
private Double montantMensuel;

public Double getMontantMensuel() {
	return montantMensuel;
}

public void setMontantMensuel(Double montantMensuel) {
	this.montantMensuel = montantMensuel;
}

@Override
public DossierEconomieSurSalaire dupliquerAvecNouveauId(String newId) {
	DossierEconomieSurSalaire copie = new DossierEconomieSurSalaire();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setMotifProlong(this.getMotifProlong());
    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setMontantMensuel(this.montantMensuel);
    return copie;
}
}
