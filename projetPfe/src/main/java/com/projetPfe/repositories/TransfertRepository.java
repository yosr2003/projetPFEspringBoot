package com.projetPfe.repositories;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransfertRepository extends JpaRepository<Transfert, String> {
    Optional<Transfert> findByrefTransfert(String refTransfert);

    List<Transfert> findByEtat(EtatDoss traitement);
}