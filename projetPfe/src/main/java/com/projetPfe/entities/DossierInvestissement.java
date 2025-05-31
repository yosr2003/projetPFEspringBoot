package com.projetPfe.entities;

import java.time.LocalDateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import jakarta.persistence.Entity;

@Entity

public class DossierInvestissement extends DossierDelegue {
private String SecteurActivité;

public String getSecteurActivité() {
	return SecteurActivité;
}

public void setSecteurActivité(String secteurActivité) {
	SecteurActivité = secteurActivité;
}
@Override
public DossierInvestissement dupliquerAvecNouveauId(String newId) {
	DossierInvestissement copie = new DossierInvestissement();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
//    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setSecteurActivité(this.SecteurActivité);
    return copie;
}

@Override
public void ajouterInfosSpecifiquesAuRapport(Document doc) throws DocumentException {
	 doc.add(new Paragraph("Type de dossier : INVESTISSEMENT"));
	    doc.add(new Paragraph("secteur d'activité : " + this.getSecteurActivité()));
	
}
}
