package com.projetPfe.implService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetPfe.Iservice.ISwift;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.EcodeOp;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Participant;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.Swift;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertNature;
import com.projetPfe.repositories.SwiftRepository;
import com.projetPfe.repositories.TransfertRepository;

import java.io.ByteArrayOutputStream;

@Service
public class SwiftServiceImpl implements ISwift {

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
    public Swift creerSwift(String transfertId, String format, String typeMessage) {
        Transfert transfert = transfertRepository.findById(transfertId)
                .orElseThrow(() -> new RuntimeException("Transfert introuvable avec ID: " + transfertId));
        if (transfert.getEtat() != EtatDoss.VALIDE) {
            throw new RuntimeException("Le transfert avec ID " + transfertId + " n'est pas validé. SWIFT non généré.");
        }

        Swift swift = new Swift();
        swift.setTransfert(transfert);
        swift.setFormat(format);
        swift.setTypemsg(typeMessage);
        swift.setDatgen(LocalDateTime.now());

        if ("xml".equalsIgnoreCase(format)) {
            String xmlMessage = creerMessageSwiftXML(transfert);
            swift.setTxtmsg(xmlMessage);
            byte[] pdfBytes = creerPdfxml(xmlMessage);
            swift.setPdfgen(pdfBytes);
            try {
                enregistrerPdfSurDisque(pdfBytes, "swift_" + transfert.getRefTransfert() + ".pdf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            CompteBancaire emetteur = transfert.getCompteBancaire_source();
            CompteBancaire beneficiaire = transfert.getCompteBancaire_cible();
            EcodeOp codeOp = (transfert.getNatureTransfert() == TransfertNature.PONCTUEL) ? EcodeOp.CRED : EcodeOp.SSTD;

            StringBuilder message = new StringBuilder();
            message.append("BIC Banque Émettrice: ").append(emetteur.getBIC()).append("\n")
                   .append("BIC Banque Bénéficiaire: ").append(beneficiaire.getBIC()).append("\n")
                   .append(":20:").append(transfert.getRefTransfert()).append("\n")
                   .append(":23B:").append(codeOp).append("\n")
                   .append(":32A:")
                   .append(transfert.getDatecre().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(" ")
                   .append(transfert.getCompteBancaire_source().getDevise().getDevise()).append(" ")
                   .append(String.format("%.2f", transfert.getMontantTransfert())).append("\n")
                   .append(":50K:/").append(emetteur.getNumeroCompte()).append("\n")
                   .append(getNomParticipant(emetteur.getParticipant())).append("\n")
                   .append(emetteur.getParticipant().getAdresse()).append("\n")
                   .append(":59:/").append(beneficiaire.getNumeroCompte()).append("\n")
                   .append(getNomParticipant(beneficiaire.getParticipant())).append("\n")
                   .append(beneficiaire.getParticipant().getAdresse()).append("\n")
                   .append(":71A:").append(transfert.getTypeFrais()).append("\n");

            swift.setCodeOperationBancaire(codeOp);
            swift.setTxtmsg(message.toString());

            byte[] pdfBytes = creerPdfswiftMT(swift);
            swift.setPdfgen(pdfBytes);
            try {
                enregistrerPdfSurDisque(pdfBytes, "swift_" + transfert.getRefTransfert() + ".pdf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return swiftRepository.save(swift);
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
            document.add(new Paragraph("Expéditeur : (BIC) " + emetteur.getBIC(), normalFont));
            document.add(new Paragraph(emetteur.getBanque(), normalFont));
            document.add(new Paragraph(emetteur.getPays(), normalFont));

            CompteBancaire beneficiaire = swift.getTransfert().getCompteBancaire_cible();
            Participant benef = beneficiaire.getParticipant();
            document.add(new Paragraph("Destinataire : (BIC) " + beneficiaire.getBIC(), normalFont));
            document.add(new Paragraph(beneficiaire.getBanque(), normalFont));
            document.add(new Paragraph(beneficiaire.getPays(), normalFont));

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

    private byte[] creerPdfxml(String contenuTexte) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph(contenuTexte));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    private void enregistrerPdfSurDisque(byte[] pdfBytes, String nomFichier) throws IOException {
        Path dossierPath = Paths.get(System.getProperty("user.home"), "Downloads", "pdf_swift");
        if (!Files.exists(dossierPath)) {
            Files.createDirectories(dossierPath);
        }
        Path cheminFichier = dossierPath.resolve(nomFichier);
        Files.write(cheminFichier, pdfBytes);
        System.out.println("✅ PDF enregistré avec succès à : " + cheminFichier.toString());
    }
    
    
	    private String creerMessageSwiftXML(Transfert transfert) {
	        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
	        String ref = transfert.getRefTransfert();
	        String dateOnly = transfert.getDatecre().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	        return """
	        <?xml version="1.0" encoding="UTF-8"?>
	        <RequestPayload>
	          <head:AppHdr xmlns:head="urn:iso:std:iso:20022:tech:xsd:head.001.001.04">
	            <head:Fr>
	              <head:FIId>
	                <head:FinInstnId>
	                  <head:BICFI>%s</head:BICFI>
	                </head:FinInstnId>
	              </head:FIId>
	            </head:Fr>
	            <head:To>
	              <head:FIId>
	                <head:FinInstnId>
	                  <head:BICFI>%s</head:BICFI>
	                </head:FinInstnId>
	              </head:FIId>
	            </head:To>
	            <head:BizMsgIdr>%s</head:BizMsgIdr>
	            <head:MsgDefIdr>pacs.008.001.12</head:MsgDefIdr>
	            <head:CreDt>%s</head:CreDt>
	          </head:AppHdr>
	          <pacs:Document xmlns:pacs="urn:iso:std:iso:20022:tech:xsd:pacs.008.001.12">
	            <pacs:FIToFICstmrCdtTrf>
	              <pacs:GrpHdr>
	                <pacs:MsgId>%s</pacs:MsgId>
	                <pacs:CreDtTm>%s</pacs:CreDtTm>
	                <pacs:NbOfTxs>1</pacs:NbOfTxs>
	                <pacs:SttlmInf>
	                  <pacs:SttlmMtd>INDA</pacs:SttlmMtd>
	                </pacs:SttlmInf>
	                <pacs:InstgAgt>
	                  <pacs:FinInstnId>
	                    <pacs:BICFI>%s</pacs:BICFI>
	                  </pacs:FinInstnId>
	                </pacs:InstgAgt>
	                <pacs:InstdAgt>
	                  <pacs:FinInstnId>
	                    <pacs:BICFI>%s</pacs:BICFI>
	                  </pacs:FinInstnId>
	                </pacs:InstdAgt>
	              </pacs:GrpHdr>
	              <pacs:CdtTrfTxInf>
	                <pacs:IntrBkSttlmAmt Ccy="%s">%.2f</pacs:IntrBkSttlmAmt>
	                <pacs:IntrBkSttlmDt>%s</pacs:IntrBkSttlmDt>
	                <pacs:ChrgBr>%s</pacs:ChrgBr>
	                <pacs:Dbtr>
	                  <pacs:Nm>%s</pacs:Nm>
	                  <pacs:PstlAdr>
	                    <pacs:StrtNm>%s</pacs:StrtNm>
	                    <pacs:Ctry>%s</pacs:Ctry>
	                  </pacs:PstlAdr>
	                </pacs:Dbtr>
	                <pacs:DbtrAgt>
	                  <pacs:FinInstnId>
	                    <pacs:BICFI>%s</pacs:BICFI>
	                  </pacs:FinInstnId>
	                </pacs:DbtrAgt>
	                <pacs:Cdtr>
	                  <pacs:Nm>%s</pacs:Nm>
	                  <pacs:PstlAdr>
	                    <pacs:StrtNm>%s</pacs:StrtNm>
	                    <pacs:Ctry>%s</pacs:Ctry>
	                  </pacs:PstlAdr>
	                </pacs:Cdtr>
	                <pacs:CdtrAgt>
	                  <pacs:FinInstnId>
	                    <pacs:BICFI>%s</pacs:BICFI>
	                  </pacs:FinInstnId>
	                </pacs:CdtrAgt>
	                <pacs:InstrForCdtrAgt>
	                  <pacs:InstrInf>%s</pacs:InstrInf>
	                </pacs:InstrForCdtrAgt>
	              </pacs:CdtTrfTxInf>
	            </pacs:FIToFICstmrCdtTrf>
	          </pacs:Document>
	        </RequestPayload>
	        """.formatted(
	            transfert.getCompteBancaire_source().getBIC(),
	            transfert.getCompteBancaire_cible().getBIC(),
	            ref,
	            nowDate,
	            ref,
	            nowDate,
	            transfert.getCompteBancaire_source().getBIC(),
	            transfert.getCompteBancaire_cible().getBIC(),
	            transfert.getCompteBancaire_source().getDevise().getDevise(),
	            transfert.getMontantTransfert(),
	            dateOnly,
	            transfert.getTypeFrais(),
	            getNomParticipant(transfert.getCompteBancaire_source().getParticipant()),
	            transfert.getCompteBancaire_source().getParticipant().getAdresse(),
	            transfert.getCompteBancaire_source().getPays(),
	            transfert.getCompteBancaire_source().getBIC(),
	            getNomParticipant(transfert.getCompteBancaire_cible().getParticipant()),
	            transfert.getCompteBancaire_cible().getParticipant().getAdresse(),
	            transfert.getCompteBancaire_cible().getPays(),
	            transfert.getCompteBancaire_cible().getBIC(),
	            "PAYMENT FOR INVOICE #US4567 - URGENT"
	        );
	    }


	    
	}


