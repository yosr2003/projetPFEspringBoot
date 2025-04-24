package com.projetPfe.entities;

import java.time.LocalDateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import jakarta.persistence.Entity;

@Entity

public class DossierScolarité extends DossierDelegue{
private String niveauEtude;

public String getNiveauEtude() {
	return niveauEtude;
}

public void setNiveauEtude(String niveauEtude) {
	this.niveauEtude = niveauEtude;
}
@Override
public DossierScolarité dupliquerAvecNouveauId(String newId) {
	DossierScolarité copie = new DossierScolarité();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setMotifProlong(this.getMotifProlong());
    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setNiveauEtude(this.niveauEtude);
    return copie;
}
@Override
public void ajouterInfosSpecifiquesAuRapport(Document doc) throws DocumentException {
    doc.add(new Paragraph("Type de dossier : SCOLARITÉ"));
    doc.add(new Paragraph("Niveau d'étude : " + this.getNiveauEtude()));
}

}
