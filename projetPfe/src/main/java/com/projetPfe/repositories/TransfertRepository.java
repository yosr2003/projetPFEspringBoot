package com.projetPfe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetPfe.entities.EtatTransfert;
import com.projetPfe.entities.TauxChange;
import com.projetPfe.entities.Transfert;

public interface TransfertRepository extends JpaRepository<Transfert, String>{

	List<Transfert> findByEtat(EtatTransfert traitement);

	Optional<Transfert> findByrefTransfert(String refTransfert);

}
