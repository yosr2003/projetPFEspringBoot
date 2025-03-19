package com.projetPfe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
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
import com.projetPfe.entities.DossierDelegue;
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
    void testCloturerDossier_Success() {
        // GIVEN : Un dossier existant avec état "Validé"
        String id = "123";
        DossierDelegue dossierExistant = new DossierDelegue();
        dossierExistant.setEtatDoss(EtatDoss.Validé);

        DossierDelegue dossierInput = new DossierDelegue();
        dossierInput.setDatclo(LocalDateTime.now());
        dossierInput.setMotifclo("Motif test");

        when(dossierDelegueRepo.findById(id)).thenReturn(Optional.of(dossierExistant));
        when(dossierDelegueRepo.save(any(DossierDelegue.class))).thenReturn(dossierExistant);

        // WHEN : On appelle la méthode
        ResponseEntity<DossierDelegue> response = dossierDelegueService.cloturerDossier(dossierInput, id);

        // THEN : Vérification
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(EtatDoss.Clôturé, response.getBody().getEtatDoss());
        assertEquals("Motif test", response.getBody().getMotifclo());
        verify(dossierDelegueRepo).save(dossierExistant);
    }
    
    @Test
    void testCloturerDossier_NotValidated() {
        // GIVEN : Un dossier existant mais non validé
        String id = "123";
        DossierDelegue dossierExistant = new DossierDelegue();
        dossierExistant.setEtatDoss(EtatDoss.Traitement);

        when(dossierDelegueRepo.findById(id)).thenReturn(Optional.of(dossierExistant));

        // WHEN
        ResponseEntity<DossierDelegue> response = dossierDelegueService.cloturerDossier(new DossierDelegue(), id);

        // THEN
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(dossierDelegueRepo, never()).save(any());
    }
    
    @Test
    void testCloturerDossier_NotFound() {
        // GIVEN : Aucun dossier trouvé
        String id = "123";
        when(dossierDelegueRepo.findById(id)).thenReturn(Optional.empty());

        // WHEN
        ResponseEntity<DossierDelegue> response = dossierDelegueService.cloturerDossier(new DossierDelegue(), id);

        // THEN
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(dossierDelegueRepo, never()).save(any());
    }

}
