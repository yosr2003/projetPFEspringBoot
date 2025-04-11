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

    @PostMapping("/creer/{transfertId}/{format}/{typeMessage}")
    public ResponseEntity<String> creerSwift(
            @PathVariable String transfertId,
            @PathVariable String format,
            @PathVariable String typeMessage) {

    
        boolean existeDeja = swiftService.existeDejaPourTransfert(transfertId);

        if (existeDeja) {
            String message = "Un message SWIFT existe déjà pour le transfert " + transfertId;
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message); 
        }


        swiftService.creerSwift(transfertId, format, typeMessage);
        String message = "Message SWIFT créé avec succès pour le transfert " + transfertId+ " et PDF enregistré à : C:\\Users\\YosrAmamou\\Downloads\\pdf_swift";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
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
