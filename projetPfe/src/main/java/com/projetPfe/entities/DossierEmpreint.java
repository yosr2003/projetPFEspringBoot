package com.projetPfe.entities;

import java.time.LocalDateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import jakarta.persistence.Entity;

@Entity
public class DossierEmpreint extends DossierDelegue {
	
private Double montantEmpreint;

public Double getMontantEmpreint() {
	return montantEmpreint;
}

public void setMontantEmpreint(Double montantEmpreint) {
	this.montantEmpreint = montantEmpreint;
}
@Override
public DossierEmpreint dupliquerAvecNouveauId(String newId) {
	DossierEmpreint copie = new DossierEmpreint();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setMotifProlong(this.getMotifProlong());
//    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setMontantEmpreint(this.montantEmpreint);
    return copie;
}
@Override
public void ajouterInfosSpecifiquesAuRapport(Document doc) throws DocumentException {
    doc.add(new Paragraph("Type de dossier : EMPRUNT"));
    doc.add(new Paragraph("Montant emprunté : " + this.getMontantEmpreint()));
}

}
