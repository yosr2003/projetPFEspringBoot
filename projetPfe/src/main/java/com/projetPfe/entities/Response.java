package com.projetPfe.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private Header header;
    private T body;

    // Classe interne Header
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Header {
        private int code;
        private String libelle;
    }
}
