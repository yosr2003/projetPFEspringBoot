package com.projetPfe.controllers;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Response;
import com.projetPfe.services.DossierDelegueService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/dossiers")
@RequiredArgsConstructor
public class DossierDelegueController {
    private final DossierDelegueService service;
    private static final Logger log = LoggerFactory.getLogger(DossierDelegueController.class);

    @GetMapping("/ListDossiers")
    public ResponseEntity<Response<List<DossierDelegue>>> getAllDossiers() {
        List<DossierDelegue> dossiers = service.getAllDossiers();
        return ResponseEntity.ok(new Response<>(new Response.Header(200, "LIST_RETRIEVED_SUCCESSFULLY"), dossiers));
    }

    @GetMapping("/ReadDossier/{id}")
    public ResponseEntity<Response<DossierDelegue>> getDossierById(@PathVariable String id) {
        Optional<DossierDelegue> dossierOpt = service.getDossierById(id);

        if (dossierOpt.isPresent()) {
            return ResponseEntity.ok(new Response<>(new Response.Header(200, "DOSSIER_FOUND"), dossierOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>(new Response.Header(404, "DOSSIER_NOT_FOUND"), null));
        }
    }

    @PostMapping("/CreateDossier")
    public ResponseEntity<Response<DossierDelegue>> createDossier(@RequestBody DossierDelegue dossier) {
        DossierDelegue saved = service.saveDossier(dossier);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(new Response.Header(201, "DOSSIER_CREATED_SUCCESSFULLY"), saved));
    }

    @PutMapping("/UpdateDossier/{id}")
    public ResponseEntity<Response<DossierDelegue>> updateDossier(@PathVariable String id, @RequestBody DossierDelegue updatedDossier) {
        try {
            DossierDelegue updated = service.updateDossier(id, updatedDossier);
            return ResponseEntity.ok(new Response<>(new Response.Header(200, "DOSSIER_UPDATED_SUCCESSFULLY"), updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>(new Response.Header(404, "DOSSIER_NOT_FOUND"), null));
        }
    }

    @DeleteMapping("/DeleteDossier/{id}")
    public ResponseEntity<Response<Void>> deleteDossier(@PathVariable String id) {
        try {
            service.deleteDossier(id);
            return ResponseEntity.ok(new Response<>(new Response.Header(200, "DOSSIER_DELETED_SUCCESSFULLY"), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>(new Response.Header(404, "DOSSIER_NOT_FOUND"), null));
        }
    }

    @PostMapping("/DuplicateDossier/{id}")
    public ResponseEntity<Response<Map<String, Object>>> duplicateDossier(@PathVariable String id) {
        return service.dupliquerDossier(id);
    }

    @PutMapping("/CloseDossier/{id}")
    public ResponseEntity<Response<DossierDelegue>> closeDossier(@RequestBody DossierDelegue input, @PathVariable String id) {
        return service.cloturerDossier(input, id);
    }

    @PutMapping("/ExtendDossier/{id}")
    public ResponseEntity<Response<DossierDelegue>> extendDossier(@PathVariable String id, @RequestBody DossierDelegue request) {
        log.info("Received request to extend dossier with ID: {}", id);

        Optional<DossierDelegue> dossierOpt = service.getDossierById(id);
        if (dossierOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>(new Response.Header(404, "DOSSIER_NOT_FOUND"), null));
        }

        DossierDelegue dossier = dossierOpt.get();

        if (dossier.getType().ordinal() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(new Response.Header(400, "ONLY_SCHOOL_TYPE_CAN_BE_EXTENDED"), null));
        }

        if (dossier.getEtatDoss().ordinal() != 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(new Response.Header(400, "DOSSIER_MUST_BE_VALIDATED_BEFORE_EXTENSION"), null));
        }

        if (request.getDateFinProlong() == null || !request.getDateFinProlong().isAfter(dossier.getDateExpiration())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(new Response.Header(400, "INVALID_EXTENSION_DATE"), null));
        }

        if (request.getMotifProlong() == null || request.getMotifProlong().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(new Response.Header(400, "EXTENSION_REASON_REQUIRED"), null));
        }

        dossier.setDateFinProlong(request.getDateFinProlong());
        dossier.setMotifProlong(request.getMotifProlong());
        dossier.setEtatDoss(EtatDoss.values()[7]); // Prolongé

        DossierDelegue updated = service.saveDossier(dossier);

        return ResponseEntity.ok(new Response<>(new Response.Header(200, "DOSSIER_EXTENDED_SUCCESSFULLY"), updated));
    }
    private final DossierDelegueService dossierService;

    @GetMapping("/{id}/decompte-reliquat")
    public ResponseEntity<byte[]> getDecompteReliquat(@PathVariable String id,
                                                      @RequestParam(defaultValue = "ADMIN") String agent) throws Exception {
        byte[] pdf = dossierService.generateDecompteReliquatPdf(id, agent);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=decompte_reliquat.pdf")
                .body(pdf);
    }

}
