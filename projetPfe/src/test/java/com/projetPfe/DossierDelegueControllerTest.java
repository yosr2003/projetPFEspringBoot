package com.projetPfe;
import com.projetPfe.controllers.*;
import com.projetPfe.entities.*;
import com.projetPfe.services.DossierDelegueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class DossierDelegueControllerTest {

    @InjectMocks
    private DossierDelegueController controller;

    @Mock
    private DossierDelegueService service;

    private DossierDelegue dossier;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dossier = new DossierDelegue();
        dossier.setIdDossier("DOS000001");
        dossier.setSolde(2000.0);
        dossier.setEtatDoss(EtatDoss.Validé);
        dossier.setType(DossierDelegueType.SCOLARITE);
        dossier.setDateExpiration(LocalDate.now().plusDays(30));
    }

    @Test
    void testGetAllDossiers() {
        List<DossierDelegue> dossiers = List.of(dossier);
        when(service.getAllDossiers()).thenReturn(dossiers);

        ResponseEntity<Response<List<DossierDelegue>>> response = controller.getAllDossiers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("LIST_RETRIEVED_SUCCESSFULLY", response.getBody().getHeader().getLibelle());
        assertEquals(1, response.getBody().getBody().size());
    }

    @Test
    void testGetDossierById_Found() {
        when(service.getDossierById(eq("DOS000001"))).thenReturn(Optional.of(dossier));

        ResponseEntity<Response<DossierDelegue>> response = controller.getDossierById("DOS000001");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DOSSIER_FOUND", response.getBody().getHeader().getLibelle());
        assertEquals(dossier.getIdDossier(), response.getBody().getBody().getIdDossier());
    }

    @Test
    void testGetDossierById_NotFound() {
        when(service.getDossierById(eq("UNKNOWN"))).thenReturn(Optional.empty());

        ResponseEntity<Response<DossierDelegue>> response = controller.getDossierById("UNKNOWN");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("DOSSIER_NOT_FOUND", response.getBody().getHeader().getLibelle());
    }

    @Test
    void testCreateDossier() {
        when(service.saveDossier(any(DossierDelegue.class))).thenReturn(dossier);

        ResponseEntity<Response<DossierDelegue>> response = controller.createDossier(dossier);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("DOSSIER_CREATED_SUCCESSFULLY", response.getBody().getHeader().getLibelle());
    }

    @Test
    void testUpdateDossier_Success() {
        DossierDelegue updated = new DossierDelegue();
        updated.setIdDossier("DOS000001");
        updated.setSolde(3000.0);

        when(service.updateDossier(eq("DOS000001"), any(DossierDelegue.class))).thenReturn(updated);

        ResponseEntity<Response<DossierDelegue>> response = controller.updateDossier("DOS000001", updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DOSSIER_UPDATED_SUCCESSFULLY", response.getBody().getHeader().getLibelle());
        assertEquals(3000.0, response.getBody().getBody().getSolde());
    }

    @Test
    void testUpdateDossier_NotFound() {
        when(service.updateDossier(eq("UNKNOWN"), any(DossierDelegue.class))).thenThrow(new RuntimeException());

        ResponseEntity<Response<DossierDelegue>> response = controller.updateDossier("UNKNOWN", dossier);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("DOSSIER_NOT_FOUND", response.getBody().getHeader().getLibelle());
    }

    @Test
    void testDeleteDossier_Success() {
        doNothing().when(service).deleteDossier("DOS000001");

        ResponseEntity<Response<Void>> response = controller.deleteDossier("DOS000001");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DOSSIER_DELETED_SUCCESSFULLY", response.getBody().getHeader().getLibelle());
    }

    @Test
    void testDeleteDossier_NotFound() {
        doThrow(new RuntimeException()).when(service).deleteDossier("UNKNOWN");

        ResponseEntity<Response<Void>> response = controller.deleteDossier("UNKNOWN");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("DOSSIER_NOT_FOUND", response.getBody().getHeader().getLibelle());
    }

    @Test
    void testDuplicateDossier() {
        Map<String, Object> responseMap = Map.of("message", "Dossier duplicated");
        when(service.dupliquerDossier(eq("DOS000001"))).thenReturn(new ResponseEntity<>(new Response<>(new Response.Header(200, "DOSSIER_DUPLICATED_SUCCESSFULLY"), responseMap), HttpStatus.OK));

        ResponseEntity<Response<Map<String, Object>>> response = controller.duplicateDossier("DOS000001");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DOSSIER_DUPLICATED_SUCCESSFULLY", response.getBody().getHeader().getLibelle());
    }

    @Test
    void testCloseDossier() {
        DossierDelegue updated = new DossierDelegue();
        updated.setIdDossier("DOS000001");
        when(service.cloturerDossier(any(DossierDelegue.class), eq("DOS000001"))).thenReturn(ResponseEntity.ok(new Response<>(new Response.Header(200, "DOSSIER_CLOSED_SUCCESSFULLY"), updated)));

        ResponseEntity<Response<DossierDelegue>> response = controller.closeDossier(dossier, "DOS000001");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DOSSIER_CLOSED_SUCCESSFULLY", response.getBody().getHeader().getLibelle());
    }

    @Test
    void testExtendDossier_Success() {
        DossierDelegue extendedDossier = new DossierDelegue();
        extendedDossier.setDateFinProlong(dossier.getDateExpiration().plusDays(30));
        extendedDossier.setMotifProlong("Extension Reason");

        when(service.getDossierById(eq("DOS000001"))).thenReturn(Optional.of(dossier));
        when(service.saveDossier(any(DossierDelegue.class))).thenReturn(extendedDossier);

        ResponseEntity<Response<DossierDelegue>> response = controller.extendDossier("DOS000001", extendedDossier);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DOSSIER_EXTENDED_SUCCESSFULLY", response.getBody().getHeader().getLibelle());
    }

    @Test
    void testExtendDossier_InvalidDossier() {
        DossierDelegue extendedDossier = new DossierDelegue();
        extendedDossier.setDateFinProlong(dossier.getDateExpiration().plusDays(30));
        extendedDossier.setMotifProlong("Extension Reason");

        when(service.getDossierById(eq("DOS000001"))).thenReturn(Optional.empty());

        ResponseEntity<Response<DossierDelegue>> response = controller.extendDossier("DOS000001", extendedDossier);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("DOSSIER_NOT_FOUND", response.getBody().getHeader().getLibelle());
    }

    @Test
    void testExtendDossier_BadRequest() {
        DossierDelegue extendedDossier = new DossierDelegue();
        extendedDossier.setDateFinProlong(null);  // Invalid date
        extendedDossier.setMotifProlong(null);    // Invalid reason

        when(service.getDossierById(eq("DOS000001"))).thenReturn(Optional.of(dossier));

        ResponseEntity<Response<DossierDelegue>> response = controller.extendDossier("DOS000001", extendedDossier);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("INVALID_EXTENSION_DATE", response.getBody().getHeader().getLibelle());
    }
}
