package com.projetPfe.entities;

public class Response<T> {
	private Header header;
    private T body;

    public Response(int code, String libelle, T body) {
        this.header = new Header(code, libelle);
        this.body = body;
    }


    //@Data
    public static class Header {
        private int code;
        private String libelle;

        public Header(int code, String libelle) {
            this.code = code;
            this.libelle = libelle;
        }
    }

}
