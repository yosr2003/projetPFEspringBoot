package com.projetPfe.Iservices;

import com.projetPfe.entities.Swift;

public interface SwiftIservice {

	boolean existeDejaPourTransfert(String transfertId);

	Swift consulterSwift(String transfertId);

	Swift creerSwift(String transfertId);

}
