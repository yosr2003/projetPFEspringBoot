package com.projetPfe.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.projetPfe.dto.DecompteReliquatDTO;
import com.projetPfe.entities.MouvementDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class PDFGeneratorService {

    public byte[] generateDecompteReliquat(DecompteReliquatDTO dto) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font smallFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        document.add(new Paragraph("DECOMPTE DE RELIQUAT DES DROIT DE TRANSFERT", titleFont));
        document.add(new Paragraph("DOSSIER SCOLARITE", titleFont));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("N° Dossier scolarité : " + dto.getIdDossier(), normalFont));
        document.add(new Paragraph("Nom et prénom : " + dto.getNomComplet(), normalFont));
        document.add(new Paragraph("N° CIN : " + dto.getCin(), normalFont));
        document.add(new Paragraph("Pays : " + dto.getPays(), normalFont));
        document.add(new Paragraph("Spécialité : " + dto.getSpecialite(), normalFont));
        document.add(new Paragraph("Année universitaire : " + dto.getAnneeUniversitaire(), normalFont));
        document.add(new Paragraph("Période allant du : " + dto.getDateDebut() + " au " + dto.getDateFin(), normalFont));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.addCell("Date");
        table.addCell("Nature de l'opération");
        table.addCell("Montant DT");
        table.addCell("N° compte donneur d'ordre");
        table.addCell("Nom donneur d'ordre");
        table.addCell("Bénéficiaire");

        for (MouvementDTO mv : dto.getMouvements()) {
            table.addCell(mv.getDate().toString());
            table.addCell(mv.getNatureOperation());
            table.addCell(String.valueOf(mv.getMontant()));
            table.addCell(mv.getCompteDonneur());
            table.addCell(mv.getNomDonneur());
            table.addCell(mv.getBeneficiaire());
        }

        document.add(table);
        document.add(new Paragraph(" "));
        Paragraph mentions = new Paragraph();
        mentions.setFont(smallFont);

        mentions.add("Ce décompte a été établi conformément aux dispositions de la circulaire N° 93-10 (modifié N° 2015-08) du 08 Septembre 1993 ayant pour objet le transfert à titre de frais de scolarité au profit des étudiants à l'étranger. ");
        mentions.add("À ce titre, les étudiants à l'étranger ont le droit de réaliser :\n");
        mentions.add("- Des transferts à titre de frais d'installation sous forme d'une allocation annuelle d'un montant maximum de 4000 DT.\n");
        mentions.add("- Des transferts à titre de frais de séjour à l'étranger pour études durant l'année universitaire ou scolaire, dans la limite de 3000,000 DT par mois.\n");
        mentions.add("- Des transferts à titre de frais d'inscription et d'études, correspondant aux montants (hors frais de séjour) exigés par l'établissement étranger, selon tout document émis au nom de l'étudiant par ledit établissement, et réalisés selon les échéances fixées.\n\n");
        mentions.add("Ce décompte est délivré à la demande de l'intéressé pour servir et valoir ce que de droit.");

        document.add(mentions);

        document.add(new Paragraph("Ce document est généré conformément à la circulaire n° 93-10 modifiée par la n° 2015-08.", smallFont));
        document.add(new Paragraph("Edité par : " + dto.getAgent() + " le " + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), smallFont));

        document.close();
        return out.toByteArray();
    }
}