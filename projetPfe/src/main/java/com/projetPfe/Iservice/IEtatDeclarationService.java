package com.projetPfe.Iservice;

import org.w3c.dom.Element;

import com.projetPfe.entities.EtatDeclarationBCT;

public interface IEtatDeclarationService {
	public EtatDeclarationBCT genererContenuXml();
	public byte[] genererEtatDeclaration(int etatId);
	public byte[] genererPdfDepuisXml(int etatId) ;
	public byte[] test(int etatId) throws Exception ;

}
