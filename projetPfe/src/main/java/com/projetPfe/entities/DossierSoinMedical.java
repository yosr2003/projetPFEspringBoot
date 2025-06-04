package com.projetPfe.entities;

import java.time.LocalDateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import jakarta.persistence.Entity;

@Entity

public class DossierSoinMedical extends DossierDelegue {
private String typeTraitement;

public String getTypeTraitement() {
	return typeTraitement;
}

public void setTypeTraitement(String typeTraitement) {
	this.typeTraitement = typeTraitement;
}
@Override
public DossierSoinMedical dupliquerAvecNouveauId(String newId) {
	DossierSoinMedical copie = new DossierSoinMedical();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setTypeTraitement(this.typeTraitement);
    return copie;
}

@Override
public void ajouterInfosSpecifiquesAuRapport(Document doc) throws DocumentException {
	 doc.add(new Paragraph("Type de dossier : SOIN_MEDICAL"));
	    doc.add(new Paragraph("Type de traitement : " + this.getTypeTraitement()));
	
}


}
