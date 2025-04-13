package com.projetPfe.dto;
import com.projetPfe.entities.EtatDoss;

public class TransfertDTO {
    private String refTransfert;
    private EtatDoss etat;

    public TransfertDTO(String refTransfert, EtatDoss etat) {
        this.refTransfert = refTransfert;
        this.etat = etat;
    }

    public String getRefTransfert() {
        return refTransfert;
    }

    public EtatDoss getEtat() {
        return etat;
    }
}
