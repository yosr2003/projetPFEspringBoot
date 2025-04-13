package com.projetPfe.repositories;

import com.projetPfe.dto.TauxChangeDTO;
import com.projetPfe.entities.TauxChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TauxChangeRepository extends JpaRepository<TauxChange , Long>{
	@Query(value="SELECT * FROM taux_change WHERE devise = :devise LIMIT 1", nativeQuery = true)
	Optional<TauxChange> findByDevise(@Param("devise") String devise);

}
