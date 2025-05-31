package com.projetPfe.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class PieceJustificative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomFichier;

    @Lob
    private byte[] contenu;

    @ManyToOne
    private DossierScolarité dossierSC; 

    private LocalDateTime dateDepot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public byte[] getContenu() {
		return contenu;
	}

	public void setContenu(byte[] contenu) {
		this.contenu = contenu;
	}

	public DossierScolarité getDossierSC() {
		return dossierSC;
	}

	public void setDossierSC(DossierScolarité dossierSC) {
		this.dossierSC = dossierSC;
	}

	public LocalDateTime getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(LocalDateTime dateDepot) {
		this.dateDepot = dateDepot;
	}

      
}
