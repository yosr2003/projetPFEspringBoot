package com.projetPfe.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetPfe.entities.AmortissementEcheance;


public interface AmortissementEchRepository extends JpaRepository<AmortissementEcheance, Long>{
	@Query("SELECT a FROM AmortissementEcheance a " +
		       "WHERE a.dateEch = :targetDate " +
		       "AND a.flgTrait = false AND a.flgValid = false")
		List<AmortissementEcheance> findEcheancesExactlyIn10Days(@Param("targetDate") LocalDate targetDate);

}

