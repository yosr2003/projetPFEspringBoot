package com.projetPfe.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class AmortissementEcheance {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idAmortissementEch;
	    
	    private Double principalEch;
	    private Double interet;
	    private LocalDate dateEch;
	    private Double autreCharge;
	    private Boolean flgTrait;
	    private Boolean flgValid;
	    
	    @ManyToOne
	    @JoinColumn(name = "idDossierEmpreint")
	    private DossierEmpreint dossierEmpreint;

	    public AmortissementEcheance() {
	    }
	    
	    public Long getIdAmortissementEch() {
	        return idAmortissementEch;
	    }

	    public void setIdAmortissementEch(Long idAmortissementEch) {
	        this.idAmortissementEch = idAmortissementEch;
	    }

	    public Double getPrincipalEch() {
	        return principalEch;
	    }

	    public void setPrincipalEch(Double principalEch) {
	        this.principalEch = principalEch;
	    }

	    public Double getInteret() {
	        return interet;
	    }

	    public void setInteret(Double interet) {
	        this.interet = interet;
	    }

	    public LocalDate getDateEch() {
	        return dateEch;
	    }

	    public void setDateEch(LocalDate dateEch) {
	        this.dateEch = dateEch;
	    }

	    public Double getAutreCharge() {
	        return autreCharge;
	    }

	    public void setAutreCharge(Double autreCharge) {
	        this.autreCharge = autreCharge;
	    }


	    public Boolean getFlgTrait() {
	        return flgTrait;
	    }

	    public void setFlgTrait(Boolean flgTrait) {
	        this.flgTrait = flgTrait;
	    }

	    public Boolean getFlgValid() {
	        return flgValid;
	    }

	    public void setFlgValid(Boolean flgValid) {
	        this.flgValid = flgValid;
	    }

		public DossierEmpreint getDossierEmpreint() {
			return dossierEmpreint;
		}

		public void setDossierEmpreint(DossierEmpreint dossierEmpreint) {
			this.dossierEmpreint = dossierEmpreint;
		}
	    
	

}
