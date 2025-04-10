package com.projetPfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/generer-et-telecharger/{transfertId}/{format}/{typeMessage}")
    public ResponseEntity<byte[]> genererEtTelechargerSwift(
            @PathVariable String transfertId,
            @PathVariable String format,
            @PathVariable String typeMessage) {
        try {
            // Appelle la méthode unifiée qui gère la création ou consultation
            Swift swift = swiftService.creerOuConsulterSwift(transfertId, format, typeMessage);
            byte[] pdfContent = swift.getPdfgen();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "swift_" + swift.getIdSwift() + ".pdf");

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
