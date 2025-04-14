package com.projetPfe.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projetPfe.entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {


}
