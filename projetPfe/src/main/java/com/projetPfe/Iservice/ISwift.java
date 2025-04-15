package com.projetPfe.Iservice;

import com.projetPfe.entities.Swift;

public interface ISwift {




	boolean existeDejaPourTransfert(String transfertId);

	Swift consulterSwift(String transfertId);



	Swift genererSwift(String transfertId, String format, String typeMessage);

	

}
