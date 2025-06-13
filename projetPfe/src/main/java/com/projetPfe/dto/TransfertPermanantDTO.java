package com.projetPfe.dto;

import com.projetPfe.entities.FraisType;

public class TransfertPermanantDTO {
	    private Double montant;
	    private String natureOperation;
	    private FraisType typeFrais;
	    private String numeroCompteSource;
	    private String numeroCompteCible;
		public Double getMontant() {
			return montant;
		}
		public String getNatureOperation() {
			return natureOperation;
		}
		public FraisType getTypeFrais() {
			return typeFrais;
		}
		public String getNumeroCompteSource() {
			return numeroCompteSource;
		}
		public String getNumeroCompteCible() {
			return numeroCompteCible;
		}
	    
	    
}
