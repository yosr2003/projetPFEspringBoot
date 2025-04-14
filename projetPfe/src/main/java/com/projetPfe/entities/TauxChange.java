package com.projetPfe.entities;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class TauxChange {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String devise;
    private Double coursVente;
    private LocalDate dateMiseAJour;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getDevise() { return devise; }
    public void setDevise(String devise) { this.devise = devise; }

    public Double getCoursVente() { return coursVente; }
    public void setCoursVente(Double coursVente) { this.coursVente = coursVente; }

    public LocalDate getDateMiseAJour() { return dateMiseAJour; }
    public void setDateMiseAJour(LocalDate dateMiseAJour) { this.dateMiseAJour = dateMiseAJour; }
	public TauxChange(Long id, String devise, Double coursVente, LocalDate dateMiseAJour) {
		super();
		this.id = id;
		this.devise = devise;
		this.coursVente = coursVente;
		this.dateMiseAJour = dateMiseAJour;
	}
	public TauxChange() {
		super();
	}

   




}
