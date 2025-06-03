

package com.projetPfe.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity

public class DossierScolarité extends DossierDelegue{
private String niveauEtude;
private LocalDate dateProlongation;

@OneToMany(mappedBy = "dossierScolarite", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)

private List<PieceJustificative> piecesJustificatives = new ArrayList<>();

public String getNiveauEtude() {
	return niveauEtude;
}

public void setNiveauEtude(String niveauEtude) {
	this.niveauEtude = niveauEtude;
}

public LocalDate getDateProlongation() {
	return dateProlongation;
}

public void setDateProlongation(LocalDate dateProlongation) {
	this.dateProlongation = dateProlongation;
}



@Override
public DossierScolarité dupliquerAvecNouveauId(String newId) {
	DossierScolarité copie = new DossierScolarité();
    copie.setIdDossier(newId);
    copie.setDateDebut(this.getDateDebut());
    copie.setDateExpiration(this.getDateExpiration());
    copie.setEtatDossier(this.getEtatDossier());
    copie.setDateCre(LocalDateTime.now());
    copie.setDateProlongation(this.getDateProlongation());
//    copie.setRapportMouvement(this.getRapportMouvement());
    copie.setNiveauEtude(this.niveauEtude);
    return copie;
}

public List<PieceJustificative> getPiecesJustificatives() {
	return piecesJustificatives;
}

public void setPiecesJustificatives(List<PieceJustificative> piecesJustificatives) {
	this.piecesJustificatives = piecesJustificatives;
}

@Override
public void ajouterInfosSpecifiquesAuRapport(Document doc) throws DocumentException {
    doc.add(new Paragraph("Type de dossier : SCOLARITÉ"));
    doc.add(new Paragraph("Niveau d'étude : " + this.getNiveauEtude()));
}

}







