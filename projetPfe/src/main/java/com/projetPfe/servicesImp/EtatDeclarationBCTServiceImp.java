package com.projetPfe.servicesImp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetPfe.Iservices.EtatDeclarationIservice;
import com.projetPfe.entities.EtatDeclarationBCT;
import com.projetPfe.entities.Participant;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.Transfert;
import com.projetPfe.repositories.EtatDeclaraionBCTRepository;
import com.projetPfe.repositories.TransfertRepository;

@Service
public class EtatDeclarationBCTServiceImp implements EtatDeclarationIservice {
	@Autowired
	private EtatDeclaraionBCTRepository etatDecRepo;
	@Autowired
	private TransfertRepository transfertRepo;
	@Override
	public ResponseEntity<?> test(String trimestre, String typeDeclaration) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public EtatDeclarationBCT genererContenuXml() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public byte[] genererEtatDeclaration(String typeDeclaration, String trimestre, int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	  
}
