package com.projetPfe.entities;
public class ResponseHeaderDTO {
    private int code;
    private String libelle;
    private String message;

    // Constructeur
    public ResponseHeaderDTO(int code, String libelle, String message) {
        this.code = code;
        this.libelle = libelle;
        this.message = message;
    }

    // Getters et setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

