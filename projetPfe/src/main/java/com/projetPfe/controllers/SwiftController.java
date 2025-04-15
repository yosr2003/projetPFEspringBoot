package com.projetPfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetPfe.Iservice.ISwift;
import com.projetPfe.entities.Swift;

@RestController
@RequestMapping("/swift")
@CrossOrigin(origins = "*")
public class SwiftController {

    @Autowired
    private ISwift swiftService;

    @PostMapping("/creer/{transfertId}/{format}")
    public ResponseEntity<String> creerSwift(
            @PathVariable String transfertId,
            @PathVariable String format) {

        try {
            boolean existeDeja = swiftService.existeDejaPourTransfert(transfertId);

            if (!existeDeja) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Aucun message SWIFT trouvé pour le transfert " + transfertId +
                              ". Veuillez d'abord créer ou associer un message SWIFT.");
            }

            swiftService.genererSwift(transfertId, format, null); // typeMessage est null ici, à adapter si nécessaire

            String message = "PDF du message SWIFT généré avec succès pour le transfert " + transfertId +
                             " et enregistré à : C:\\Users\\YosrAmamou\\Downloads\\pdf_swift";
            return ResponseEntity.status(HttpStatus.CREATED).body(message);

        } catch (RuntimeException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.contains("n'est pas validé")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
            } else if (errorMsg.contains("vide") || errorMsg.contains("SWIFT trouvé")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erreur lors de la génération du PDF SWIFT : " + errorMsg);
            }
        }
    }



    @GetMapping("/consulter/{transfertId}")
    public ResponseEntity<?> consulterSwift(@PathVariable String transfertId) {
        try {
            Swift swift = swiftService.consulterSwift(transfertId);

            if (swift == null || swift.getPdfgen() == null) {
                String message = "Le transfert " + transfertId + " n'a pas de message SWIFT";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }

            byte[] pdfBytes = swift.getPdfgen();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("swift_" + transfertId + ".pdf").build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la consultation du SWIFT.");
        }
    }


    

}
