package com.projetPfe.Iservice;

import org.w3c.dom.Element;

import com.projetPfe.entities.EtatDeclarationBCT;

public interface IEtatDeclarationService {
	public EtatDeclarationBCT genererContenuXml(String id);


	public byte[] test(String typeDeclaration, String trimestre) throws Exception ;

}
