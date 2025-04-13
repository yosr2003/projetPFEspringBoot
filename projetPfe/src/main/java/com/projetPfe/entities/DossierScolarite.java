package com.projetPfe.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@DiscriminatorValue("SCOLARITE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DossierScolarite extends DossierDelegue {

    String nomEtudiant;
    String prenomEtudiant;
    String cinEtudiant;
    String paysEtudes;
    String specialite;
}
