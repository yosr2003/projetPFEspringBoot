package com.projetPfe.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class TauxChange {
    
    @Id
    private String id;

    private String devise;
    private Double coursVente;
    private LocalDate dateMiseAJour;

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @OneToMany(mappedBy = "deviseSource")
    private List<Transfert> transfertsSource;

    @OneToMany(mappedBy = "deviseCible")
    private List<Transfert> transfertsCible;

    public String getDevise() { return devise; }
    public void setDevise(String devise) { this.devise = devise; }

    public Double getCoursVente() { return coursVente; }
    public void setCoursVente(Double coursVente) { this.coursVente = coursVente; }

    public LocalDate getDateMiseAJour() { return dateMiseAJour; }
    public void setDateMiseAJour(LocalDate dateMiseAJour) { this.dateMiseAJour = dateMiseAJour; }

    public List<Transfert> getTransfertsSource() {
        return transfertsSource;
    }
    
    public void setTransfertsSource(List<Transfert> transfertsSource) {
        this.transfertsSource = transfertsSource;
    }
    
    public List<Transfert> getTransfertsCible() {
        return transfertsCible;
    }
    
    public void setTransfertsCible(List<Transfert> transfertsCible) {
        this.transfertsCible = transfertsCible;
    }
    


}
