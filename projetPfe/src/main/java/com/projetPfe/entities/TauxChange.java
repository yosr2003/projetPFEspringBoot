package com.projetPfe.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TauxChange {

    @Id
    private String id;

    private String devise;
    private Double coursVente;
    private LocalDate dateMiseAJour;


    @OneToMany(mappedBy = "deviseSource")
    private List<Transfert> transfertsSource;

    @OneToMany(mappedBy = "deviseCible")
    private List<Transfert> transfertsCible;




}
