package com.projetPfe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projetPfe.Iservice.IDossierDelegueService;
import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierDelegueType;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.implService.DossierDelegueServiceImpl;
import com.projetPfe.repositories.DossierDelegueRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DossierDelegueTest {
	@InjectMocks
    private DossierDelegueServiceImpl dossierDelegueService;

    @Mock
    private DossierDelegueRepository dossierDelegueRepo;
    
    @Test
    void testCloturerDossier_NotFound() {
        //le dossier fourni est inexistant
        String id = "123";
        when(dossierDelegueRepo.findById(id)).thenReturn(Optional.empty());

        
        ResponseEntity<Map<String, Object>> response = dossierDelegueService.cloturerDossier
        		(new DossierDelegue(), id);

        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(dossierDelegueRepo, never()).save(any());
    }
    
    @Test
    void testCloturerDossier_NotValidated() {
      //Un dossier existant mais non validé
      String id = "123";
      DossierDelegue dossierExistant = new DossierDelegue();
      dossierExistant.setEtatDoss(EtatDoss.TRAITEMENT);

      when(dossierDelegueRepo.findById(id)).thenReturn(Optional.of(dossierExistant));

        
      ResponseEntity<Map<String, Object>> response = dossierDelegueService.cloturerDossier
      (new DossierDelegue(), id);

        
      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      verify(dossierDelegueRepo, never()).save(any());
    }
    
    
    

    @Test
    void testDupliquerDossier_Success() {
        String id = "DOS001";
        DossierDelegue dossier = new DossierDelegue();
        dossier.setIdDossier(id);
        dossier.setEtatDoss(EtatDoss.VALIDE);
        dossier.setDateDebut(LocalDate.now());
        dossier.setDateExpiration(LocalDate.now().plusMonths(1));
        dossier.setAnneedoss("2024");
        //dossier.setType(DossierDelegueType.SCOLARITE);
        dossier.setSolde(12.00);
        dossier.setDateFinProlong(LocalDate.now().plusMonths(2));
        dossier.setMotifProlong("Motif");

        when(dossierDelegueRepo.findById(id)).thenReturn(Optional.of(dossier));

        ResponseEntity<Map<String, Object>> response = dossierDelegueService.dupliquerDossier(id);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertTrue(body.containsKey("header"));
        assertTrue(body.containsKey("body"));
        ResponseHeaderDTO header = (ResponseHeaderDTO) body.get("header");
        assertEquals(200, header.getCode());
        assertEquals("dupliqué avec succès", header.getMessage());
    }
}
