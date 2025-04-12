package com.projetPfe.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class EtatDeclarationBCT {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEtatDeclaration;
	@Lob
	private byte[] contenuPdf; 
	private String trimestre;
	

	@Lob
	private String contenuTexte;

	public EtatDeclarationBCT() {
		super();
	}
	public byte[] getContenuPdf() {
		return contenuPdf;
	}

	public void setContenuPdf(byte[] contenuPdf) {
		this.contenuPdf = contenuPdf;
	}

	public String getTrimestre() {
		return trimestre;
	}
	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}
	public void setContenuTexte(String contenuTexte) {
		this.contenuTexte = contenuTexte;
	}

	public String getContenuTexte() {
		return contenuTexte;
	}


}
