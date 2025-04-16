package com.projetPfe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.projetPfe.dto.ResponseHeaderDTO;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierDelegueType;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.Transfert;
import com.projetPfe.implService.DossierDelegueServiceImpl;
import com.projetPfe.repositories.DossierDelegueRepository;
import com.projetPfe.repositories.TransfertRepository;
@ExtendWith(MockitoExtension.class)
public class DossierDelegueTest {
	@InjectMocks
    private DossierDelegueServiceImpl dossierDelegueService;

    @Mock
    private DossierDelegueRepository dossierDelegueRepo;
    @Mock
    private TransfertRepository transfertRepo;
    private DossierDelegue dossier;
    private Transfert transfert;
    
//    @Test
//    void testCloturerDossier_NotFound() {
        //le dossier fourni est inexistant
//        String id = "123";
//        when(dossierDelegueRepo.findById(id)).thenReturn(Optional.empty());
//
//        
//        ResponseEntity<Map<String, Object>> response = dossierDelegueService.cloturerDossier
//        		(new DossierDelegue(), id);
//
//        
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(dossierDelegueRepo, never()).save(any());
    //}
    
//    @Test
//    void testCloturerDossier_NotValidated() {
//      //Un dossier existant mais non validé
//      String id = "123";
//      DossierDelegue dossierExistant = new DossierDelegue();
//      dossierExistant.setEtatDoss(EtatDoss.TRAITEMENT);
//
//      when(dossierDelegueRepo.findById(id)).thenReturn(Optional.of(dossierExistant));
//
//        
//      ResponseEntity<Map<String, Object>> response = dossierDelegueService.cloturerDossier
//      (new DossierDelegue(), id);
//
//        
//      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//      verify(dossierDelegueRepo, never()).save(any());
  //  }
    
    
    

//    @Test
//    void testDupliquerDossier_Success() {
//        String id = "DOS001";
//        DossierDelegue dossier = new DossierDelegue();
//        dossier.setIdDossier(id);
//        dossier.setEtatDoss(EtatDoss.VALIDE);
//        dossier.setDateDebut(LocalDate.now());
//        dossier.setDateExpiration(LocalDate.now().plusMonths(1));
//        dossier.setAnneedoss("2024");
//        //dossier.setType(DossierDelegueType.SCOLARITE);
//        dossier.setSolde(12.00);
//        dossier.setDateFinProlong(LocalDate.now().plusMonths(2));
//        dossier.setMotifProlong("Motif");
//
//        when(dossierDelegueRepo.findById(id)).thenReturn(Optional.of(dossier));
//
//        ResponseEntity<Map<String, Object>> response = dossierDelegueService.dupliquerDossier(id);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        Map<String, Object> body = response.getBody();
//        assertNotNull(body);
//        assertTrue(body.containsKey("header"));
//        assertTrue(body.containsKey("body"));
//        ResponseHeaderDTO header = (ResponseHeaderDTO) body.get("header");
//        assertEquals(200, header.getCode());
//        assertEquals("dupliqué avec succès", header.getMessage());
 //   }
    @BeforeEach
    void setUp() {
        dossier = new DossierDelegue();
        dossier.setIdDossier("123");
        dossier.setDateDebut(LocalDate.of(2023, 1, 1));
        dossier.setDateExpiration(LocalDate.of(2023, 12, 31));
        dossier.setType(DossierDelegueType.INVESTISSEMENT); 

        CompteBancaire compteSource = new CompteBancaire();
        compteSource.setNumeroCompte("12345");
        PersonneMorale emetteur = new PersonneMorale();
        emetteur.setRaisonSociale("Société X");
        compteSource.setParticipant(emetteur);

        CompteBancaire compteCible = new CompteBancaire();
        compteCible.setNumeroCompte("54321");
        PersonneMorale beneficiaire = new PersonneMorale();
        beneficiaire.setRaisonSociale("Société Y");
        compteCible.setParticipant(beneficiaire);

        transfert = new Transfert();
        transfert.setDatecre(LocalDateTime.of(2023, 5, 10, 12, 0));
        transfert.setNatureOperation("Virement");
        transfert.setMontantTransfert(new Double("1000.00"));
        transfert.setCompteBancaire_source(compteSource);
        transfert.setCompteBancaire_cible(compteCible);
        transfert.setDossierDelegue(dossier);
    }

    @Test
    void testGenererRapportMouvement_Success() throws Exception {
        when(dossierDelegueRepo.findById("123")).thenReturn(Optional.of(dossier));
        when(transfertRepo.findAll()).thenReturn(List.of(transfert));

        ResponseEntity<?> response = dossierDelegueService.genererRapportMouvement("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getHeaders().getContentType().includes(MediaType.APPLICATION_PDF));
        assertNotNull(response.getBody());
    }
    @Test
    void testGenererRapportMouvement_ClotureAtteinteMaisNonExpire() throws Exception {
        dossier.setDatclo(LocalDateTime.now().minusDays(1)); // le dossier a ete clôturé 
        dossier.setDateExpiration(LocalDate.now().plusDays(5)); // avant d'atteindre sa date d'expiration

        when(dossierDelegueRepo.findById("123")).thenReturn(Optional.of(dossier));
        when(transfertRepo.findAll()).thenReturn(List.of(transfert));

        ResponseEntity<?> response = dossierDelegueService.genererRapportMouvement("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getHeaders().getContentType().includes(MediaType.APPLICATION_PDF));
        assertNotNull(response.getBody());
    }
    
    @Test
    void testGenererRapportMouvement_DossierNonCloture() throws Exception {
        dossier.setDatclo(null); // Non clôturé
        dossier.setDateExpiration(LocalDate.now().plusDays(5)); // encore actif

        when(dossierDelegueRepo.findById("123")).thenReturn(Optional.of(dossier));

        ResponseEntity<?> response = dossierDelegueService.genererRapportMouvement("123");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Ce dossier n'est pas encore clôturer", response.getBody());
    }
    @Test
    void testGenererRapportMouvement_DejaRapport() throws Exception {
        dossier.setRapportMouvement("test rapport".getBytes());
        when(dossierDelegueRepo.findById("123")).thenReturn(Optional.of(dossier));

        ResponseEntity<?> response = dossierDelegueService.genererRapportMouvement("123");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Ce Dossier Délégué a déja un rapport de mouvement financiers", response.getBody());
    }

}
