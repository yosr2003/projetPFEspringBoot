package com.projetPfe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Transfert;

public interface TransfertRepository extends JpaRepository<Transfert, String> {

  Optional<Transfert> findByrefTransfert(String refTransfert);

List<Transfert> findByEtat(EtatDoss traitement);

Optional<Transfert> findById(String transfertId);



}
