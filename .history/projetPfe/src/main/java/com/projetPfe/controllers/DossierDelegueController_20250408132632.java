package com.projetPfe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetPfe.Iservice.IDossierDelegueService;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierDelegueType;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Response;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/dossiersDelegues")
public class DossierDelegueController {

	@Autowired
	private IDossierDelegueService dossDelService;
	private static final Logger log = LoggerFactory.getLogger(DossierDelegue.class);

	@GetMapping
    public ResponseEntity<List<DossierDelegue>> getAllDossiers() {
        return ResponseEntity.ok(dossDelService.getAllDossierDelegues());
    }
	
	 @GetMapping("/{id}")
	 public ResponseEntity<DossierDelegue> getDossierById(@PathVariable String id) {
	        Optional<DossierDelegue> dossier = dossDelService.getDossierById(id);
	        return dossier.map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
        @PutMapping("/{id}")
        public ResponseEntity<DossierDelegue> cloturerDossier(@RequestBody DossierDelegue d,@PathVariable("id") String id){
            return dossDelService.cloturerDossier(d,id);
        }
	 @PutMapping(value = "/prolonger/{id}", produces = "application/json")
	 public ResponseEntity<Response<DossierDelegue>> prolongerDossier(
	         @PathVariable("id") String id,
	         @RequestBody DossierDelegue request) {

	     log.info("Requête reçue pour prolonger le dossier avec ID : {}", id);

	     // Récupération du dossier depuis la base de données
	     Optional<DossierDelegue> dossierOptional = dossDelService.getDossierById(id);
	     if (dossierOptional.isEmpty()) {
	         log.warn("Dossier non trouvé avec ID : {}", id);
	         return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                 .body(new Response<>(404, "Dossier non trouvé", null));
	     }

	     DossierDelegue dossier = dossierOptional.get();

	     // Vérification du type de dossier (doit être SCOLARITE)
	     int TYPE_SCOLARITE = 0; // Assurez-vous que SCOLARITE correspond bien à 0 en base
	     if (dossier.getType().ordinal() != TYPE_SCOLARITE) {
	         log.warn("Seuls les dossiers de type SCOLARITE peuvent être prolongés. ID : {}", id);
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                 .body(new Response<>(400, "Seuls les dossiers de type SCOLARITE peuvent être prolongés", null));
	     }

	     // Vérification de l'état du dossier (doit être Validé)
	     int ETAT_VALIDE = 3; // Vérifiez que "Validé" correspond bien à 3 en base
	     if (dossier.getEtatDoss().ordinal() != ETAT_VALIDE) {
	         log.warn("Le dossier avec ID {} ne peut être prolongé que s'il est validé.", id);
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                 .body(new Response<>(400, "Le dossier doit être validé pour être prolongé", null));
	     }

	     // Vérification que la date de prolongation est fournie
	     if (request.getDateFinProlong() == null) {
	         log.warn("La date de prolongation est obligatoire pour ID : {}", id);
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                 .body(new Response<>(400, "La date de prolongation est obligatoire", null));
	     }

	     // Vérification que la date de prolongation est après la date d'expiration
	     if (!request.getDateFinProlong().isAfter(dossier.getDateExpiration())) {
	         log.warn("La date de prolongation doit être après la date d'expiration actuelle pour le dossier avec ID : {}", id);
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                 .body(new Response<>(400, "La date de prolongation doit être après la date d'expiration", null));
	     }

	     // Vérification que le motif de prolongation n'est pas vide ou null
	     if (request.getMotifProlong() == null || request.getMotifProlong().trim().isEmpty()) {
	         log.warn("Le motif de prolongation est obligatoire pour ID : {}", id);
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                 .body(new Response<>(400, "Le motif de prolongation ne doit pas être vide", null));
	     }

	     // Mise à jour de la date de prolongation et du motif
	     dossier.setDateFinProlong(request.getDateFinProlong());
	     dossier.setMotifProlong(request.getMotifProlong());

	     // Mise à jour de la date d'expiration avec la nouvelle date de prolongation
	     dossier.setDateExpiration(request.getDateFinProlong());

	     // Mise à jour de l'état du dossier en "Prolongé"
	     int ETAT_PROLONGE = 7; // Vérifiez que "Prolongé" correspond bien à 7 en base
	     dossier.setEtatDoss(EtatDoss.values()[ETAT_PROLONGE]);

	     // Sauvegarde du dossier mis à jour
	     DossierDelegue updatedDossier = dossDelService.updateDossier(dossier);

	     log.info("Dossier mis à jour avec succès : {}", updatedDossier);

	     return ResponseEntity.ok(new Response<>(200, "Dossier prolongé avec succès", updatedDossier));
	 }

}