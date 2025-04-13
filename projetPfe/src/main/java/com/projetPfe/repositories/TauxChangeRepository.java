package com.projetPfe.repositories;
import com.projetPfe.entities.TauxChange;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TauxChangeRepository extends JpaRepository<TauxChange, String> {
    @Query(value=("SELECT * FROM taux_change WHERE devise = :devise ") , nativeQuery = true)
    Optional<TauxChange> findByDevise(@Param("devise") String devise);
}