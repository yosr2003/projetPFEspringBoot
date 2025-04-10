package com.projetPfe.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AmortissementEch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAmortissementEch;
    
    private Double principalEch;
    private Double interet;
    private LocalDate dateEch;
    private Double autreCharge;
    
    @ManyToOne
    @JoinColumn(name = "idDossier")
    private DossierDelegue dossierDelegue;
    
    private Boolean flgTrait;
    private Boolean flgValid;
    
    // Constructeurs
    public AmortissementEch() {
    }
    
    // Getters et Setters
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

    public DossierDelegue getDossierDelegue() {
        return dossierDelegue;
    }

    public void setDossierDelegue(DossierDelegue dossierDelegue) {
        this.dossierDelegue = dossierDelegue;
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
    
    public String getIdDossier() {
        return dossierDelegue != null ? dossierDelegue.getIdDossier() : null;
    }
}
