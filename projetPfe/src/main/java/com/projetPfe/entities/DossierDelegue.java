package com.projetPfe.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dossier_type", discriminatorType = DiscriminatorType.STRING)
public class DossierDelegue {
    @Id
    String idDossier;

    String anneedoss;
    @Enumerated(EnumType.STRING)
    EtatDoss etatDoss;

    LocalDateTime  datclo;

    String motifclo;
    String MotifProlong;

    LocalDateTime  dateCre;
    LocalDate dateDebut;
    LocalDate dateExpiration;
    LocalDate DateFinProlong;
    Double solde;
    DossierDelegueType type;

    @OneToMany(mappedBy = "dossierDelegue")
    private List<Transfert> transferts;
}
