package com.projetPfe.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projetPfe.entities.AmortissementEch;

@Repository
public interface AmortissementEchRepository extends JpaRepository<AmortissementEch, Long> {
    
    @Query("SELECT a FROM AmortissementEch a WHERE a.dateEch = :targetDate AND a.flgTrait = false AND a.flgValid = false")
    List<AmortissementEch> findUpcomingDeadlines(@Param("targetDate") LocalDate targetDate);
}
