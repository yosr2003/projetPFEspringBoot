package com.projetPfe.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class EtatDeclarationBCT {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEtatDeclaration;
	@Lob
	private byte[] contenuPdf; 
	private String trimestre;
	@JsonIgnore
	@OneToMany(mappedBy = "etatDeclaration")
	private List<Transfert> transferts;




	public EtatDeclarationBCT(String trimestre) {
		super();
		this.trimestre = trimestre;

	}
	@Override
	public int hashCode() {
		return Objects.hash(trimestre);
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
		return Objects.equals(trimestre, other.trimestre);
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
	public void setTransferts(List<Transfert> transferts) {
		this.transferts = transferts;
	}


}
