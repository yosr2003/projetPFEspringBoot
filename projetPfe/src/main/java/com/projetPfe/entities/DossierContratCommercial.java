package com.projetPfe.entities;

import java.time.LocalDateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import jakarta.persistence.Entity;

@Entity

public class DossierContratCommercial extends DossierDelegue {
private Double montantContrat;

public Double getMontantContrat() {
	return montantContrat;
}

public void setMontantContrat(Double montantContrat) {
	this.montantContrat = montantContrat;
}
@Override
public DossierContratCommercial dupliquerAvecNouveauId(String newId) {
	DossierContratCommercial copie = new DossierContratCommercial();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
//    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setMontantContrat(this.montantContrat);
    return copie;
}
@Override
public void ajouterInfosSpecifiquesAuRapport(Document doc) throws DocumentException {
    doc.add(new Paragraph("Type de dossier : CONTRAT_COMMERCIAL"));
    doc.add(new Paragraph("Montant total du contrat : " + this.getMontantContrat()));
}


}
