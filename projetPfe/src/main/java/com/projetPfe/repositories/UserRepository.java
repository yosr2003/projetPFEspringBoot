package com.projetPfe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetPfe.entities.Employe;

@Repository
public interface UserRepository extends JpaRepository<Employe, Long> {

Boolean existsByEmail(String email);
Optional<Employe> findByEmail(String email);


}
