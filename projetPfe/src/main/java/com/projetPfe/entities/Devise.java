package com.projetPfe.entities;

import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name="Devise")
public class Devise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false , unique = true)
    private monnaieType monnaie;

  @Column(nullable=false)
    private double valeur;

  @Column(nullable = false)
    private LocalDate date_maj;


  //getters&setters

  Long getId(){
    return this.id;
  }

  void setId(Long id){
    this.id = id;
  }

  monnaieType getMonnaie(){
    return this.monnaie;
  }

  double getValeur(){
    return this.valeur;
  }

  LocalDate getDateMaj(){
    return this.date_maj;
  }

  void setMonnaie(monnaieType type){
    this.monnaie = type;
  }

  void setValeur(double val){
    this.valeur = val;
  }

  void setDateMaj(LocalDate date){
    this.date_maj = date;
  }

  //getters&setters
  
}
