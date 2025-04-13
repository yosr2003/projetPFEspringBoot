package com.projetPfe.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Swift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     int idSwift;
     String module="TRF";
     String typemsg;
     String seq97;
     LocalDateTime datgen;
     String message;
     String format;


    @OneToOne
    @JoinColumn(name = "ref_tranfert")
    private Transfert transfert;
}
