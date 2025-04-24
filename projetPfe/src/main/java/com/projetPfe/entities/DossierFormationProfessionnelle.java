package com.projetPfe.entities;

import java.time.LocalDateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import jakarta.persistence.Entity;

@Entity

public class DossierFormationProfessionnelle extends DossierDelegue {
private String nomFormation;

public String getNomFormation() {
	return nomFormation;
}

public void setNomFormation(String nomFormation) {
	this.nomFormation = nomFormation;
}
@Override
public DossierFormationProfessionnelle dupliquerAvecNouveauId(String newId) {
	DossierFormationProfessionnelle copie = new DossierFormationProfessionnelle();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setMotifProlong(this.getMotifProlong());
    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setNomFormation(this.nomFormation);
    return copie;
}

@Override
public void ajouterInfosSpecifiquesAuRapport(Document doc) throws DocumentException {
	 doc.add(new Paragraph("Type de dossier : FORMATION_PROFESSIONNELLE"));
	    doc.add(new Paragraph("Formation : " + this.getNomFormation()));
	
}
}
