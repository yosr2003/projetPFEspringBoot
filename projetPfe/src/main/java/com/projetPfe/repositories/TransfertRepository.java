package com.projetPfe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetPfe.entities.Transfert;

public interface TransfertRepository extends JpaRepository<Transfert, String> {
  Optional<Transfert> findByrefTransfert(String refTransfert);

}
