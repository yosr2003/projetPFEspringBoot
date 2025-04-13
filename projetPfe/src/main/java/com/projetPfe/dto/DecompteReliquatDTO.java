package com.projetPfe.dto;
import com.projetPfe.entities.MouvementDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecompteReliquatDTO {
    String idDossier;
    String nomComplet;
    String cin;
    String pays;
    String specialite;
    String anneeUniversitaire;
    LocalDate dateDebut;
    LocalDate dateFin;

    List<MouvementDTO> mouvements;

    String agent;
}
