package com.projetPfe.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projetPfe.entities.EtatDoss;

public class TransfertDTO {
  @JsonProperty("ref_transfert")
  private String refTransfert;
  
  @JsonProperty("etat")
  private EtatDoss etat;

  public TransfertDTO(String refTransfert, EtatDoss etat) {
      this.refTransfert = refTransfert;
      this.etat = etat;
  }

  public String getRefTransfert() {
      return this.refTransfert;
  }

  public EtatDoss getEtat() {
      return this.etat;
  }
}

