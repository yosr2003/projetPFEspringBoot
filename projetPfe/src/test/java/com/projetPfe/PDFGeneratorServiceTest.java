package com.projetPfe;
import com.projetPfe.dto.DecompteReliquatDTO;
import com.projetPfe.entities.*;
import com.projetPfe.services.PDFGeneratorService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PDFGeneratorServiceTest {

    PDFGeneratorService service = new PDFGeneratorService();

    @Test
    void generateDecompteReliquat_shouldReturnNonEmptyPdf() throws Exception {
        DecompteReliquatDTO dto = new DecompteReliquatDTO();
        dto.setIdDossier("1234");
        dto.setNomComplet("Ali Ben Salah");
        dto.setCin("12345678");
        dto.setPays("Canada");
        dto.setSpecialite("Informatique");
        dto.setAnneeUniversitaire("2024/2025");
        dto.setDateDebut(LocalDate.of(2024, 9, 1));
        dto.setDateFin(LocalDate.of(2025, 6, 30));
        dto.setAgent("Test Agent");

        dto.setMouvements(List.of(
                new MouvementDTO(LocalDate.of(2024, 9, 10), "Retrait Espèce", 500.0, "001122", "BANQUE TEST", "Ali Ben Salah")
        ));

        byte[] pdf = service.generateDecompteReliquat(dto);
        assertNotNull(pdf);
        assertTrue(pdf.length > 0);
    }
}
