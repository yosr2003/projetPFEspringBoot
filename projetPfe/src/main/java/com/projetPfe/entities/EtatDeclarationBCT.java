package com.projetPfe.entities;

import java.util.Objects;

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
	private String TypeDeclaration;
	private int annee;

	public String getTypeDeclaration() {
		return TypeDeclaration;
	}
	public void setTypeDeclaration(String typeDeclaration) {
		TypeDeclaration = typeDeclaration;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public EtatDeclarationBCT(String trimestre, String typeDeclaration, int annee) {
		super();
		this.trimestre = trimestre;
		TypeDeclaration = typeDeclaration;
		this.annee = annee;
	}
	@Override
	public int hashCode() {
		return Objects.hash(TypeDeclaration, annee, trimestre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EtatDeclarationBCT other = (EtatDeclarationBCT) obj;
		return Objects.equals(TypeDeclaration, other.TypeDeclaration) && annee == other.annee
				&& Objects.equals(trimestre, other.trimestre);
	}

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
