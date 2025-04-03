package com.projetPfe.entities;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="taux_change")
public class TauxChange implements Serializable{
    public TauxChange() {
        super();
    }

    @Id
    @Column(name="id")
    private String id;
    @Column(name = "devise" , nullable = false)
    private String devise;
    @Column(name = "cours_vente",nullable = false)
    private Double coursVente;
    @Column(name = "date_miseajour")
    private LocalDate dateMiseAJour;

    
    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDevise() { return devise; }
    public void setDevise(String devise) { this.devise = devise; }

    public Double getCoursVente() { return coursVente; }
    public void setCoursVente(Double coursVente) { this.coursVente = coursVente; }

    public LocalDate getDateMiseAJour() { return dateMiseAJour; }
    public void setDateMiseAJour(LocalDate dateMiseAJour) { this.dateMiseAJour = dateMiseAJour; }

    


}
