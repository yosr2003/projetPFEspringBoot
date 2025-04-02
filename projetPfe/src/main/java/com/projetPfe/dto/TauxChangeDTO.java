package com.projetPfe.dto;

public class TauxChangeDTO {
  private String id;
  private String devise;
  private double coursVente;

  public TauxChangeDTO(String id , String devise , double coursVente){
    this.id = id;
    this.devise = devise;
    this.coursVente = coursVente;
  }

  public String getDevise(){
    return this.devise;
  }

  public String getId(){
    return this.id;
  } 

  public double getCoursVente(){
    return this.coursVente;
  }
}
