package com.projetPfe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetPfe.entities.TauxChange;

@Repository
public interface TauxChangeRepository extends JpaRepository<TauxChange, Long> {

	Optional<TauxChange> findByDevise(String devise);

}
