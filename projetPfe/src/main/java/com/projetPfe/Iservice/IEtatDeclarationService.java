package com.projetPfe.Iservice;



import org.springframework.http.ResponseEntity;

import com.projetPfe.entities.EtatDeclarationBCT;

public interface IEtatDeclarationService {
	public EtatDeclarationBCT genererContenuXml();
	public byte[] genererEtatDeclaration(String typeDeclaration, String trimestre,int id) throws Exception ;

	public ResponseEntity<?>  test(String trimestre,String typeDeclaration) throws Exception ;

}
