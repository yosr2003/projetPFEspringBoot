package com.projetPfe;

import com.projetPfe.entities.*;
import com.projetPfe.services.*;
import com.projetPfe.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DossierServiceTest {

    @Mock
    DossierDelegueRepository dossierRepository;

    @Mock
    PDFGeneratorService pdfGeneratorService;

    @InjectMocks
    DossierDelegueService dossierService;

    DossierScolarite dossier;
    Transfert transfert;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dossier = new DossierScolarite();
        dossier.setIdDossier("1234");
        dossier.setAnneedoss("2024/2025");
        dossier.setNomEtudiant("Ali");
        dossier.setPrenomEtudiant("Ben Salah");
        dossier.setCinEtudiant("12345678");
        dossier.setPaysEtudes("Canada");
        dossier.setSpecialite("Informatique");
        dossier.setDateDebut(LocalDate.of(2024, 9, 1));
        dossier.setDateExpiration(LocalDate.of(2025, 6, 30));

        transfert = new Transfert();
        transfert.setDatecre(LocalDateTime.of(2024, 9, 10, 10, 0));
        transfert.setMontantTransfert(500.0);
        transfert.setTypeTransfert(TransfertType.FINANCIER);
        transfert.setDossierDelegue(dossier);

        dossier.setTransferts(List.of(transfert));
    }

    @Test
    void generateDecompteReliquatPdf_shouldGeneratePdf() throws Exception {
        when(dossierRepository.findById("1234")).thenReturn(Optional.of(dossier));
        when(pdfGeneratorService.generateDecompteReliquat(any())).thenReturn(new byte[]{1, 2, 3});

        byte[] result = dossierService.generateDecompteReliquatPdf("1234", "Admin Agent");

        assertNotNull(result);
        assertEquals(3, result.length);

        verify(pdfGeneratorService, times(1)).generateDecompteReliquat(any());
    }
}
