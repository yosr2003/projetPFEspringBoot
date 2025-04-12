package com.projetPfe.Iservice;



import com.projetPfe.entities.EtatDeclarationBCT;

public interface IEtatDeclarationService {
	public EtatDeclarationBCT genererContenuXml();
	public byte[] genererEtatDeclaration(String typeDeclaration, String trimestre,int id) throws Exception ;

	public byte[] test(String trimestre,String typeDeclaration) throws Exception ;

}
