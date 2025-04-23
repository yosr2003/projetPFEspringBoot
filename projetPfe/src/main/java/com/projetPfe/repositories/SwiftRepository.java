package com.projetPfe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.projetPfe.entities.Swift;
import com.projetPfe.entities.Transfert;

public interface SwiftRepository extends JpaRepository<Swift, Integer> {

	boolean existsByTransfert_RefTransfert(String transfertId);

	Optional<Swift> findByTransfert(Transfert transfert);

}
