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
    
    @Override
    public Swift genererSwift(String transfertId, String format, String typeMessage) {
        // 1. Récupérer le transfert
        Transfert transfert = transfertRepository.findById(transfertId)
                .orElseThrow(() -> new RuntimeException("Transfert introuvable avec ID: " + transfertId));

        if (transfert.getEtat() != EtatDoss.VALIDE) {
            throw new RuntimeException("Le transfert avec ID " + transfertId + " n'est pas validé. SWIFT non généré.");
        }

        // 2. Récupérer le SWIFT lié à ce transfert
        Swift swift = swiftRepository.findByTransfert(transfert)
                .orElseThrow(() -> new RuntimeException("Aucun message SWIFT trouvé pour ce transfert."));

        // 3. Vérifier que le message texte existe
        if (swift.getTxtmsg() == null || swift.getTxtmsg().isEmpty()) {
            throw new RuntimeException("Le message texte SWIFT est vide. Impossible de générer le PDF.");
        }

        // 4. Générer le PDF
        byte[] pdfBytes = creerPdfswift(swift.getTxtmsg());
        swift.setPdfgen(pdfBytes);
        swift.setDatgen(LocalDateTime.now());

        try {
            enregistrerPdfSurDisque(pdfBytes, "swift_" + transfert.getRefTransfert() + ".pdf");
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du PDF sur disque", e);
        }

        // 5. Sauvegarder le swift mis à jour
        return swiftRepository.save(swift);
    }

    
    private byte[] creerPdfswift(String contenuTexte) {
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
    
    @Override
    public Swift consulterSwift(String transfertId) {
        Transfert transfert = transfertRepository.findById(transfertId)
                .orElseThrow(() -> new RuntimeException("Transfert introuvable avec ID: " + transfertId));

        Swift swift = swiftRepository.findByTransfert(transfert)
                .orElseThrow(() -> new RuntimeException("Swift introuvable pour le transfert ID: " + transfertId));

        return swift;
    }



    
    

	    
	}



