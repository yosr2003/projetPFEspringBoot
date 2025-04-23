package com.projetPfe.entities;

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
}
