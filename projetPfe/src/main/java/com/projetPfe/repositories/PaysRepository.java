package com.projetPfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetPfe.entities.Pays;




@Repository
public interface PaysRepository extends JpaRepository <Pays,Long> {

}
