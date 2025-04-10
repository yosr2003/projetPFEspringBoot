package com.projetPfe.Iservice;

import com.projetPfe.entities.EtatDeclarationBCT;

public interface IEtatDeclarationService {
	public EtatDeclarationBCT genererContenuXml();
	public  String escapeXml(String input);
	public byte[] genererPdf(int etatId);

}
