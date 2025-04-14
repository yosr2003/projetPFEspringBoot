package com.projetPfe.Iservice;

import com.projetPfe.entities.Swift;

public interface ISwift {






	Swift creerSwift(String transfertId, String format, String typeMessage);



	boolean existeDejaPourTransfert(String transfertId);

	Swift consulterSwift(String transfertId);

	

}
