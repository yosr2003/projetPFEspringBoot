package com.projetPfe.dto;

import java.time.LocalDate;

import com.projetPfe.entities.AmortissementEch;

public class AmortissementEchDTO {
    private Long idAmortissementEch;
    private Double principalEch;
    private LocalDate dateEch;
    private String idDossier;
    private Boolean flgTrait;
    private Boolean flgValid;
    
    public AmortissementEchDTO() {
    }
    
    public AmortissementEchDTO(AmortissementEch amortissementEch) {
        this.idAmortissementEch = amortissementEch.getIdAmortissementEch();
        this.principalEch = amortissementEch.getPrincipalEch();
        this.dateEch = amortissementEch.getDateEch();
        this.idDossier = amortissementEch.getIdDossier();
        this.flgTrait = amortissementEch.getFlgTrait();
        this.flgValid = amortissementEch.getFlgValid();
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

    public LocalDate getDateEch() {
        return dateEch;
    }

    public void setDateEch(LocalDate dateEch) {
        this.dateEch = dateEch;
    }

    public String getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(String idDossier) {
        this.idDossier = idDossier;
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
}
