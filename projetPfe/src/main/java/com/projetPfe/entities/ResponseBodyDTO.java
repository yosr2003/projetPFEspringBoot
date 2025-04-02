package com.projetPfe.entities;

public class ResponseBodyDTO {
    private String idDossier;

    // Constructeur
    public ResponseBodyDTO(String idDossier) {
        this.idDossier = idDossier;
    }

    // Getters et setters
    public String getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(String idDossier) {
        this.idDossier = idDossier;
    }
}

