package com.projetPfe.Iservices;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.entities.EtatDeclarationBCT;
import com.projetPfe.entities.Transfert;


public interface EtatDeclarationIservice {

	ResponseEntity<?> genererEtatDeclaration(String trimestre, String typeDeclaration) throws Exception;

	StringBuilder  genererContenuXml(String trimestre,List<Transfert> transferts);

	

}
