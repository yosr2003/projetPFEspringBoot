package com.projetPfe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.projetPfe.entities.TransfertPermanent;


@Repository
public interface TransfertPermanentRepository extends JpaRepository<TransfertPermanent, String> {
	List<TransfertPermanent> findByDossierDelegue_IdDossier(String idDossier);

}
