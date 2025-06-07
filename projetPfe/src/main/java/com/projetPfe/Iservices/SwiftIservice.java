package com.projetPfe.Iservices;

import org.springframework.http.ResponseEntity;

import com.projetPfe.entities.Swift;

public interface SwiftIservice {

	boolean existeDejaPourTransfert(String transfertId);

	Swift consulterSwift(String transfertId);

	ResponseEntity<?> creerSwift(String transfertId);

}
