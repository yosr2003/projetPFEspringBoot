package com.projetPfe.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private Header header;
    private T body;

    // Constructeur
    public Response(int code, String libelle, T body) {
        this.header = new Header(code, libelle);
        this.body = body;
    }

    // Getter et Setter pour Header
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    // Getter et Setter pour Body
    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    // Classe interne Header
    public static class Header {
        private int code;
        private String libelle;

        // Constructeur
        public Header(int code, String libelle) {
            this.code = code;
            this.libelle = libelle;
        }

        // Getters et Setters
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
    }
}
