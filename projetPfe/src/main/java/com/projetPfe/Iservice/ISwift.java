package com.projetPfe.Iservice;

import com.projetPfe.entities.Swift;

public interface ISwift {






	public Swift creerSwift(String transfertId, String format, String typeMessage);



	public boolean existeDejaPourTransfert(String transfertId);

	public Swift consulterSwift(String transfertId);

	

}
