package com.projetPfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetPfe.entities.RapportMouvementsFinanciers;

@Repository
public interface RapportMouvementFinanciersRepository extends JpaRepository<RapportMouvementsFinanciers, Integer> {

}
