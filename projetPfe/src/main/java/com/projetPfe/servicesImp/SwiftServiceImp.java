package com.projetPfe.servicesImp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetPfe.Iservices.SwiftIservice;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.EtatTransfert;
import com.projetPfe.entities.Participant;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.Swift;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertPermanent;
import com.projetPfe.entities.TransfertPonctuel;
import com.projetPfe.repositories.SwiftRepository;
import com.projetPfe.repositories.TransfertRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class SwiftServiceImp implements SwiftIservice {
	 @Autowired
	    private TransfertRepository transfertRepository;

	    @Autowired
	    private SwiftRepository swiftRepository;
	    @Override
	    public boolean existeDejaPourTransfert(String transfertId) {
	        return swiftRepository.existsByTransfert_RefTransfert(transfertId);
	    }
	    
	    private String getNomParticipant(Participant participant) {
	        if (participant instanceof PersonnePhysique) {
	            PersonnePhysique pp = (PersonnePhysique) participant;
	            return pp.getNom() + " " + pp.getPrenom();
	        } else if (participant instanceof PersonneMorale) {
	            PersonneMorale pm = (PersonneMorale) participant;
	            return pm.getRaisonSociale();
	        } else {
	            return "Participant inconnu";
	        }
	    }


	    @Override
	    public ResponseEntity<byte[]>  creerSwift(String transfertId) {
	    	  try {
	    	   if (existeDejaPourTransfert(transfertId)) {
	    	        return ResponseEntity.status(HttpStatus.CONFLICT)
	    	                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
	    	                .body("Un message SWIFT existe déjà pour ce transfert.".getBytes());
	    	    }
	        Transfert transfert = transfertRepository.findById(transfertId)
	                .orElseThrow(() -> new RuntimeException("Transfert introuvable avec ID: " + transfertId));

	        if (transfert.getEtatTransfert() != EtatTransfert.VALIDE) {
	            throw new RuntimeException("le transfert avec ID " + transfertId + " n'est pas validé. le message SWIFT ne peut etre generé que pour un transfert en etat valide");
	        }

	        Swift swift = new Swift();
	        swift.setTransfert(transfert);
	        swift.setFormat("MT");
	        swift.setTypemsg("MT103");
	        swift.setDatgen(LocalDateTime.now());

	        CompteBancaire emetteur = transfert.getCompteBancaire_source();
	        CompteBancaire beneficiaire = transfert.getCompteBancaire_cible();

	        // Détermination du code opération selon le type de transfert
	        String codeOp;
	        if (transfert instanceof TransfertPonctuel) {
	            codeOp = "CRED";
	        } else if (transfert instanceof TransfertPermanent) {
	            codeOp = "SSTD";
	        } else {
	            throw new RuntimeException("Type de transfert inconnu pour déterminer le code opération.");
	        }

	        // Construction du message SWIFT
	        StringBuilder message = new StringBuilder();
	        message.append("BIC Banque Émettrice: ").append(emetteur.getBanque().getBIC()).append("\n")
	               .append("BIC Banque Bénéficiaire: ").append(beneficiaire.getBanque().getBIC()).append("\n")
	               .append(":20:").append(transfert.getRefTransfert()).append("\n")
	               .append(":23B:").append(codeOp).append("\n")
	               .append(":32A:").append(transfert.getDatecre().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(" ")
	               .append(emetteur.getDevise().getDevise()).append(" ")
	               .append(String.format("%.2f", transfert.getMontantTransfert())).append("\n")
	               .append(":50K:/").append(emetteur.getNumeroCompte()).append("\n")
	               .append(getNomParticipant(emetteur.getParticipant())).append("\n")
	               .append(emetteur.getParticipant().getAdresse()).append("\n")
	               .append(":59:/").append(beneficiaire.getNumeroCompte()).append("\n")
	               .append(getNomParticipant(beneficiaire.getParticipant())).append("\n")
	               .append(beneficiaire.getParticipant().getAdresse()).append("\n")
	               .append(":71A:").append(transfert.getTypeFrais()).append("\n");

	        swift.setTxtmsg(message.toString());

	        // Génération du PDF
	        byte[] pdfBytes = creerPdfswiftMT(swift);
	        swift.setPdfgen(pdfBytes);
	        swiftRepository.save(swift);

	        String fileName = "swift_" + transfert.getRefTransfert() + ".pdf";

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(pdfBytes);

	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
	                .body(("Erreur lors de la création du message SWIFT : " + e.getMessage()).getBytes());
	    }
	    }

	
	    

	    @Override
	    public Swift consulterSwift(String transfertId) {
	        Transfert transfert = transfertRepository.findById(transfertId)
	                .orElseThrow(() -> new RuntimeException("Transfert introuvable avec ID: " + transfertId));

	        Swift swift = swiftRepository.findByTransfert(transfert)
	                .orElseThrow(() -> new RuntimeException("Swift introuvable pour le transfert ID: " + transfertId));

	        return swift;
	    }



	    
	    
	    private byte[] creerPdfswiftMT(Swift swift) {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        Document document = new Document();
	        try {
	            PdfWriter.getInstance(document, baos);
	            document.open();
	            Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
	            Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
	            Font normalFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL);

	            document.add(new Paragraph("message swift MT103 (Paiements interbancaires clients)", titleFont));
	            document.add(new Paragraph("Sortie Swift : Virement individuel client", subtitleFont));
	            document.add(new Paragraph(" "));
	            CompteBancaire emetteur = swift.getTransfert().getCompteBancaire_source();
	            Participant donneur = emetteur.getParticipant();
	            document.add(new Paragraph("Expéditeur : (BIC) " + emetteur.getBanque().getBIC(), normalFont));
	            document.add(new Paragraph(emetteur.getBanque().getBanque(), normalFont));
	            document.add(new Paragraph(emetteur.getBanque().getPays().getPays(), normalFont));

	            CompteBancaire beneficiaire = swift.getTransfert().getCompteBancaire_cible();
	            Participant benef = beneficiaire.getParticipant();
	            document.add(new Paragraph("Destinataire : (BIC) " + beneficiaire.getBanque().getBIC(), normalFont));
	            document.add(new Paragraph(beneficiaire.getBanque().getBanque(), normalFont));
	            document.add(new Paragraph(beneficiaire.getBanque().getPays().getPays(), normalFont));

	            document.add(new Paragraph("Service de copie FIN : BSP", normalFont));
	            document.add(new Paragraph("----------------------------------------------------Texte du message-----------------------------------------------"));
	            document.add(new Paragraph(swift.getTxtmsg(), normalFont));
	            document.add(new Paragraph("------------------------------------------------------Bande finale du message----------------------------------------", normalFont));

	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return baos.toByteArray();
	    }

}
