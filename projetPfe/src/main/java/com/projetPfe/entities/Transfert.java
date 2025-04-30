package com.projetPfe.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transfert")
public class Transfert {

    @Id
    @Column(name = "ref_transfert")
    private String refTransfert;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_transfert")
    private TransfertType typeTransfert;

    @Column(name = "montant_transfert")
    private Double montantTransfert;

    @ManyToOne
    @JoinColumn(name = "devise_source_id")
    private TauxChange deviseSource;

    @ManyToOne
    @JoinColumn(name = "devise_cible_id")
    private TauxChange deviseCible;

    @Column(name = "datecre")
    private LocalDateTime datecre;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private EtatDoss etat;

    @Column(name = "date_echeance")
    private LocalDate dateEcheance;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature_transfert")
    private TransfertNature natureTransfert;

    @Column(name = "frais")
    private Double frais;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_frais")
    private FraisType typeFrais;

    @Column(name = "montant_final")
    private Double montantFinal;

    @ManyToOne
    @JoinColumn(name = "idDossDelegue")
    private DossierDelegue dossierDelegue;

    @OneToOne(mappedBy = "transfert")
    private Swift swift;

    // Constructeur
    public Transfert() {
        super();
    }

    // Getters et Setters
    public String getRefTransfert() {
        return refTransfert;
    }

    public void setRefTransfert(String refTransfert) {
        this.refTransfert = refTransfert;
    }

    public TransfertType getTypeTransfert() {
        return typeTransfert;
    }

    public void setTypeTransfert(TransfertType typeTransfert) {
        this.typeTransfert = typeTransfert;
    }

    public Double getMontantTransfert() {
        return montantTransfert;
    }

    public void setMontantTransfert(Double montantTransfert) {
        this.montantTransfert = montantTransfert;
    }

    public TauxChange getDeviseSource() {
        return deviseSource;
    }

    public void setDeviseSource(TauxChange deviseSource) {
        this.deviseSource = deviseSource;
    }

    public TauxChange getDeviseCible() {
        return deviseCible;
    }

    public void setDeviseCible(TauxChange deviseCible) {
        this.deviseCible = deviseCible;
    }

    public LocalDateTime getDatecre() {
        return datecre;
    }

    public void setDatecre(LocalDateTime datecre) {
        this.datecre = datecre;
    }

    public EtatDoss getEtat() {
        return etat;
    }

    public void setEtat(EtatDoss etat) {
        this.etat = etat;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public TransfertNature getNatureTransfert() {
        return natureTransfert;
    }

    public void setNatureTransfert(TransfertNature natureTransfert) {
        this.natureTransfert = natureTransfert;
    }

    public Double getFrais() {
        return frais;
    }

    public void setFrais(Double frais) {
        this.frais = frais;
    }

    public FraisType getTypeFrais() {
        return typeFrais;
    }

    public void setTypeFrais(FraisType typeFrais) {
        this.typeFrais = typeFrais;
    }

    public Double getMontantFinal() {
        return montantFinal;
    }

    public void setMontantFinal(Double montantFinal) {
        this.montantFinal = montantFinal;
    }

    public DossierDelegue getDossierDelegue() {
        return dossierDelegue;
    }

    public void setDossierDelegue(DossierDelegue dossierDelegue) {
        this.dossierDelegue = dossierDelegue;
    }

    public Swift getSwift() {
        return swift;
    }

    public void setSwift(Swift swift) {
        this.swift = swift;
    }
}
