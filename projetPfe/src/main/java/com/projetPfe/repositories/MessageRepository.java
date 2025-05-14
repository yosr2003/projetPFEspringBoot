package com.projetPfe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetPfe.entities.Message;

public interface MessageRepository extends JpaRepository<Message,Long>{

}
