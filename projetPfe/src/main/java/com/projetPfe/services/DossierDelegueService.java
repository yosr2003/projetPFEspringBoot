package com.projetPfe.services;
import com.projetPfe.dto.DecompteReliquatDTO;
import com.projetPfe.entities.*;
import com.projetPfe.repositories.DossierDelegueRepository;
import com.projetPfe.entities.DossierDelegue;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DossierDelegueService {
    private final DossierDelegueRepository repository;
    private final PDFGeneratorService pdfGeneratorService;

    public List<DossierDelegue> getAllDossiers() {
        return repository.findAll();
    }

    public Optional<DossierDelegue> getDossierById(String id) {
        return repository.findById(id);
    }

    public DossierDelegue saveDossier(DossierDelegue dossier) {
        return repository.save(dossier);
    }

    public DossierDelegue updateDossier(String id, DossierDelegue updatedDossier) {
        return repository.findById(id).map(dossier -> {
            dossier.setAnneedoss(updatedDossier.getAnneedoss());
            dossier.setEtatDoss(updatedDossier.getEtatDoss());
            dossier.setDatclo(updatedDossier.getDatclo());
            dossier.setMotifclo(updatedDossier.getMotifclo());
            dossier.setMotifProlong(updatedDossier.getMotifProlong());
            dossier.setDateCre(updatedDossier.getDateCre());
            dossier.setDateDebut(updatedDossier.getDateDebut());
            dossier.setDateExpiration(updatedDossier.getDateExpiration());
            dossier.setDateFinProlong(updatedDossier.getDateFinProlong());
            dossier.setSolde(updatedDossier.getSolde());
            dossier.setType(updatedDossier.getType());
            return repository.save(dossier);
        }).orElseThrow(() -> new RuntimeException("Dossier not found"));
    }

    public void deleteDossier(String id) {
        Optional<DossierDelegue> dossier = repository.findById(id);
        if (dossier.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Dossier not found");
        }
    }



    public ResponseEntity<Response<Map<String, Object>>> dupliquerDossier(String id) {
        Optional<DossierDelegue> optionalDossier = repository.findById(id);
        Response<Map<String, Object>> response = new Response<>();

        if (optionalDossier.isPresent()) {
            DossierDelegue dossier = optionalDossier.get();

            if (EtatDoss.Validé.equals(dossier.getEtatDoss())) {
                String newId = genererIdentifiantUnique("DOS");
                DossierDelegue copie = new DossierDelegue();
                copie.setIdDossier(newId);
                copie.setDateDebut(dossier.getDateDebut());
                copie.setEtatDoss(dossier.getEtatDoss());
                copie.setDateExpiration(dossier.getDateExpiration());
                copie.setAnneedoss(dossier.getAnneedoss());
                copie.setType(dossier.getType());
                copie.setSolde(dossier.getSolde());
                copie.setDateCre(LocalDateTime.now());
                copie.setDateFinProlong(dossier.getDateFinProlong());
                copie.setMotifProlong(dossier.getMotifProlong());

                repository.save(copie);

                response.setHeader(new Response.Header(200, "SERVICE_OK"));
                Map<String, Object> body = new HashMap<>();
                body.put("newId", newId);
                response.setBody(body);

                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }

            response.setHeader(new Response.Header(400, "BAD_REQUEST"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.setHeader(new Response.Header(404, "NOT_FOUND"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Response<DossierDelegue>> cloturerDossier(DossierDelegue input, String id) {
        Optional<DossierDelegue> optional = repository.findById(id);
        Response<DossierDelegue> response = new Response<>();

        if (optional.isPresent()) {
            DossierDelegue dossier = optional.get();
            if (EtatDoss.Validé.equals(dossier.getEtatDoss())) {
                dossier.setDatclo(input.getDatclo());
                dossier.setMotifclo(input.getMotifclo());
                dossier.setEtatDoss(EtatDoss.Clôturé);

                repository.save(dossier);

                response.setHeader(new Response.Header(200, "SERVICE_OK"));
                response.setBody(dossier);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setHeader(new Response.Header(400, "BAD_REQUEST"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }

        response.setHeader(new Response.Header(404, "NOT_FOUND"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private String genererIdentifiantUnique(String prefix) {
        String newId;
        Random random = new Random();

        do {
            String randomDigits = String.format("%08d", random.nextInt(100_000_000));
            newId = prefix + randomDigits;
        } while (repository.existsById(newId));

        return newId;
    }
    public byte[] generateDecompteReliquatPdf(String idDossier, String agent) throws Exception {
        DossierScolarite dossier = (DossierScolarite) repository.findById(idDossier)
                .orElseThrow(() -> new EntityNotFoundException("Dossier non trouvé"));

        List<MouvementDTO> mouvements = dossier.getTransferts().stream()
                .filter(t -> t.getTypeTransfert() == TransfertType.FINANCIER)
                .map(t -> new MouvementDTO(
                        t.getDatecre().toLocalDate(),
                        "Retrait Espèce",
                        t.getMontantTransfert(),
                        "0621017001016", // Exemple
                        "TEST COMPAGNY", // Exemple
                        dossier.getNomEtudiant()))
                .toList();

        DecompteReliquatDTO dto = new DecompteReliquatDTO(
                dossier.getIdDossier(),
                dossier.getNomEtudiant() + " " + dossier.getPrenomEtudiant(),
                dossier.getCinEtudiant(),
                dossier.getPaysEtudes(),
                dossier.getSpecialite(),
                dossier.getAnneedoss(),
                dossier.getDateDebut(),
                dossier.getDateExpiration() != null ? dossier.getDateExpiration() : dossier.getDateFinProlong(),
                mouvements,
                agent
        );

        return pdfGeneratorService.generateDecompteReliquat(dto);
    }
}
