package com.projetPfe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.projetPfe.entities.Banque;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierInvestissement;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Pays;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.RapportMouvementsFinanciers;
import com.projetPfe.entities.TauxChange;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertPermanent;
import com.projetPfe.repositories.RapportMouvementFinanciersRepository;
import com.projetPfe.repositories.TransfertPermanentRepository;
import com.projetPfe.repositories.TransfertRepository;
import com.projetPfe.repositories.dossierDelegueRepository;
import com.projetPfe.servicesImp.DossierDelegueService;
import com.projetPfe.servicesImp.RapportMvmntFinanciersService;


@ExtendWith(MockitoExtension.class)
public class DossierDelegueTest {
	@InjectMocks
    private DossierDelegueService dossierDelegueService;
	@InjectMocks
    private RapportMvmntFinanciersService rapportService;
	
    @Mock
    private dossierDelegueRepository dossierDelegueRepo;
    @Mock
    private TransfertRepository transfertRepo;
    @Mock
    private TransfertPermanentRepository transfertPermanentRepo;
    @Mock
    private RapportMouvementFinanciersRepository rapportRepo;
    
    private DossierDelegue dossier;
    private Transfert transfert;
    
    
    
//    @Test
//    void testCloturerDossier_NotFound() {
//        //le dossier fourni est inexistant
//        String id = "123";
//        when(dossierDelegueRepo.findById(id)).thenReturn(Optional.empty());
//
//        
//        ResponseEntity<Map<String, Object>> response = dossierDelegueService.cloturerDossier(id,LocalDate.now(),"demenagement");
//
//        
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(dossierDelegueRepo, never()).save(any());
//    }
//    
//    @Test
//    void testCloturerDossier_NotValidated() {
//      //Un dossier existant mais non validé
//      String id = "123";
//      DossierDelegue dossierExistant = new DossierScolarité();
//      dossierExistant.setEtatDossier(EtatDoss.TRAITEMENT);
//
//      when(dossierDelegueRepo.findById(id)).thenReturn(Optional.of(dossierExistant));
//
//        
//      ResponseEntity<Map<String, Object>> response = dossierDelegueService.cloturerDossier(id,LocalDate.now(),"nouvelle année");
//
//        
//      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//      verify(dossierDelegueRepo, never()).save(any());
//    }
    
    
//    @Test
//    void testDupliquerDossier_Success() {
//        String id = "DOS001";
//        DossierDelegue dossier = new DossierInvestissement();
//        dossier.setIdDossier(id);
//        dossier.setEtatDossier(EtatDoss.VALIDE);
//        dossier.setDateDebut(LocalDate.now());
//        dossier.setDateExpiration(LocalDate.now().plusMonths(1));
//
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
//    }
    
    
    
  @BeforeEach
  void setUp() {
	  dossier = new DossierInvestissement();
      dossier.setIdDossier("DOSS001");
      dossier.setDateDebut(LocalDate.of(2023, 1, 1));
      dossier.setDateExpiration(LocalDate.of(2024, 1, 1));
      dossier.setDateCloture(LocalDate.of(2023, 12, 31));
      dossier.setRapportMouvementFinanciers(null);
      Banque banque = new Banque();
      Pays pays = new Pays();
      pays.setPays("Tunisie");
      banque.setPays(pays);
      PersonneMorale emetteur = new PersonneMorale();
      emetteur.setRaisonSociale("Société X");
      PersonnePhysique beneficiaire = new PersonnePhysique();
      beneficiaire.setNom("Ben");
      beneficiaire.setPrenom("Ali");
      CompteBancaire source = new CompteBancaire();
      source.setNumeroCompte("12345");
      source.setParticipant(emetteur);
      CompteBancaire cible = new CompteBancaire();
      cible.setBanque(banque);
      cible.setParticipant(beneficiaire);

      transfert = new TransfertPermanent();
      ((TransfertPermanent) transfert).setDossierDelegue(dossier);
      transfert.setDatecre(LocalDateTime.of(2023, 5, 10, 12, 0));
      transfert.setMontantTransfert(new Double("1000.00"));
      ((TransfertPermanent) transfert).setNatureOperation("Paiement");
      transfert.setCompteBancaire_source(source);
      transfert.setCompteBancaire_cible(cible);
  }
  
 
  @Test
  void testGenererRapportMouvement_Success() throws Exception {
      when(dossierDelegueRepo.findById(dossier.getIdDossier())).thenReturn(Optional.of(dossier));
      when(transfertPermanentRepo.findAll()).thenReturn(List.of((TransfertPermanent)transfert));

      ResponseEntity<?> response = rapportService.genererRapportMouvement(dossier.getIdDossier());

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertTrue(response.getBody() instanceof byte[]);
      assertTrue(response.getHeaders().getContentType().includes(MediaType.APPLICATION_PDF));
      assertNotNull(response.getBody());

      verify(rapportRepo).save(any(RapportMouvementsFinanciers.class));
      verify(dossierDelegueRepo).save(dossier);
  }
  
  @Test
  void testGenererRapportMouvement_DossierNonCloture() throws Exception {
	  dossier.setDateCloture(null);// Non clôturé
      dossier.setDateExpiration(LocalDate.now().plusDays(5));// encore actif
     
      when(dossierDelegueRepo.findById(dossier.getIdDossier())).thenReturn(Optional.of(dossier));
      when(transfertPermanentRepo.findAll()).thenReturn(List.of((TransfertPermanent)transfert));
      
      ResponseEntity<?> response = rapportService.genererRapportMouvement(dossier.getIdDossier());
      
      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      assertEquals("Ce dossier n'est pas encore clôturé", response.getBody());
  }
  
  
  @Test
  void testGenererRapportMouvement_AucunTransfert() throws Exception {
      // on met une date de clôture valide
	  dossier.setDateCloture(LocalDate.now().minusDays(1));
      when(dossierDelegueRepo.findById(dossier.getIdDossier())).thenReturn(Optional.of(dossier));
      when(transfertPermanentRepo.findAll()).thenReturn(Collections.emptyList());
      
      ResponseEntity<?> response = rapportService.genererRapportMouvement(dossier.getIdDossier());

      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      assertEquals("Ce Dossier Délégué ne contient pas encore de transferts permanents", response.getBody());
  }


}
