package com.projetPfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetPfe.entities.DossierDelegue;


@Repository
public interface dossierScolariteRepository extends JpaRepository<DossierDelegue, String> {

}
