package com.projetPfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.Participant;


@Repository
public interface PersonnePhysiqueRepository extends JpaRepository<Participant, Long> {

}
