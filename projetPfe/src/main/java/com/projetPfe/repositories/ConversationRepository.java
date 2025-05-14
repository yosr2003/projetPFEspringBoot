package com.projetPfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetPfe.entities.SessionConversationnelle;

public interface ConversationRepository extends JpaRepository<SessionConversationnelle, Long>{

}
