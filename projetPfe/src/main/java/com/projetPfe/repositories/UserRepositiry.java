package com.projetPfe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetPfe.entities.Utilisateur;

@Repository
public interface UserRepositiry extends JpaRepository<Utilisateur, Long> {

Boolean existsByEmail(String email);
Optional<Utilisateur> findByEmail(String email);


}
