package com.projetPfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Transfert;

public interface TransfertRepository extends JpaRepository<Transfert, String> {
	List<Transfert> findByEtat(EtatDoss etat);
}
