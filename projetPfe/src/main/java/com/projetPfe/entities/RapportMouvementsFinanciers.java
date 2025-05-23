package com.projetPfe.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class RapportMouvementsFinanciers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_rapport;
	
	@Lob
	private byte[] rapportMouvement; 
	
	@OneToOne(mappedBy = "rapportMouvementFinanciers")
    private DossierDelegue dossier;

	public RapportMouvementsFinanciers() {
		super();
	}

	public void setDossier(DossierDelegue dossier) {
		this.dossier = dossier;
	}

	public void setRapportMouvement(byte[] rapportMouvement) {
		this.rapportMouvement = rapportMouvement;
	}

	public byte[] getRapportMouvement() {
		return rapportMouvement;
	}
	
	
	

}
