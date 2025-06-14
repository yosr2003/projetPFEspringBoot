package com.projetPfe.dto;

import java.time.LocalDate;

public class DossierDTO {
	    private String typeDossier; 
	    private LocalDate dateDebut;
	    private LocalDate dateExpiration;
	    private String niveauEtude;
	    private String typeTraitement;
	    private TransfertPermanantDTO transfert;
	    private Double montantEmpreint;
	    
		public String getTypeDossier() {
			return typeDossier;
		}
		public LocalDate getDateDebut() {
			return dateDebut;
		}
		public LocalDate getDateExpiration() {
			return dateExpiration;
		}
		public String getNiveauEtude() {
			return niveauEtude;
		}
		public String getTypeTraitement() {
			return typeTraitement;
		}
		public TransfertPermanantDTO getTransfert() {
			return transfert;
		}
		public Double getMontantEmpreint() {
			return montantEmpreint;
		}
	    
	    


}
