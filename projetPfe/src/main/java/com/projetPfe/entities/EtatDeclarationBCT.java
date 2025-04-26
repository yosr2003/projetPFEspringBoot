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
	private Long idEtatDeclaration;
	@Lob
	private byte[] contenuPdf; 
	private String trimestre;
	@JsonIgnore
	@OneToMany(mappedBy = "etatDeclaration")
	private List<Transfert> transferts;
	
	

	

	

	public EtatDeclarationBCT() {
		super();
	}
	
	

	public EtatDeclarationBCT(String trimestre) {
		super();
		this.trimestre = trimestre;
	}



	@Override
	public int hashCode() {
	    return Objects.hash(
	        trimestre,
	        getTypeFromFirstTransfert(transferts)
	    );
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

	    if (!Objects.equals(trimestre, other.trimestre))
	        return false;

	    if ((transferts == null || transferts.isEmpty()) || (other.transferts == null || other.transferts.isEmpty()))
	        return false;

	    String typeThis = getTypeFromFirstTransfert(this.transferts);
	    String typeOther = getTypeFromFirstTransfert(other.transferts);

	    return Objects.equals(typeThis, typeOther);
	}
	private String getTypeFromFirstTransfert(List<Transfert> transferts) {
	    if (transferts == null || transferts.isEmpty())
	        return null;

	    Transfert premier = transferts.get(0);
	    if (premier instanceof TransfertPonctuel) {
	        return ((TransfertPonctuel) premier).getTypeTransfert().toString();
	    } else if (premier instanceof TransfertPermanent) {
	        DossierDelegue dossier = ((TransfertPermanent) premier).getDossierDelegue();
	        if (dossier != null) {
	            return dossier.getClass().getSimpleName(); // ici tu utilises le nom du dossier
	        }
	    }
	    return null;
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
	
	public void setTransferts(List<Transfert> transferts) {
		this.transferts = transferts;
	}



	public Long getIdEtatDeclaration() {
		return idEtatDeclaration;
	}



	public void setIdEtatDeclaration(Long idEtatDeclaration) {
		this.idEtatDeclaration = idEtatDeclaration;
	}



	public List<Transfert> getTransferts() {
		return transferts;
	}


	
	


}
