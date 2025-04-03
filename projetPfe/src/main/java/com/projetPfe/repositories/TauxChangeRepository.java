package com.projetPfe.repositories;

import com.projetPfe.entities.TauxChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TauxChangeRepository extends JpaRepository<TauxChange , String>{
  @Query(value=("SELECT * FROM taux_change WHERE devise = :devise ") , nativeQuery = true)
  Optional<TauxChange> findByDevise(String devise);
}
