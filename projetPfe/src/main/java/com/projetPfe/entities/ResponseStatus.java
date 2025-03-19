package com.projetPfe.entities;

public enum ResponseStatus {
    SUCCESS(200, "SERVICE_OK"),
    NOT_FOUND(404, "TRANSFERT_NOT_FOUND"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    INTERNAL_ERROR(500, "INTERNAL_SERVER_ERROR");

    private final int code;
    private final String libelle;

    ResponseStatus(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }
}
