package com.projetPfe.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MouvementDTO {
    LocalDate date;
    String natureOperation;
    Double montant;
    String compteDonneur;
    String nomDonneur;
    String beneficiaire;
}

