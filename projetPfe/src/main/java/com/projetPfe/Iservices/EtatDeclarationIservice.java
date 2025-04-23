package com.projetPfe.Iservices;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.entities.EtatDeclarationBCT;

@Service
public interface EtatDeclarationIservice {

	ResponseEntity<?> test(String trimestre, String typeDeclaration) throws Exception;

	EtatDeclarationBCT genererContenuXml();

	byte[] genererEtatDeclaration(String typeDeclaration, String trimestre, int id) throws Exception;

}
