package com.projetPfe.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
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

    @Column(name="montant_final")
    private double MontantFinal;


    @ManyToOne
    @JoinColumn(name = "idDossDelegue")
    private DossierDelegue dossierDelegue;


    @OneToOne(mappedBy = "transfert")
    private Swift swift;



}
