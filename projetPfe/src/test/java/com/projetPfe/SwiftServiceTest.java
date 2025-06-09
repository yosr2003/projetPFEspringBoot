package com.projetPfe;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.projetPfe.entities.*;
import com.projetPfe.repositories.*;
import com.projetPfe.servicesImp.SwiftServiceImp;

@SpringBootTest
@Transactional
@Rollback
public class SwiftServiceTest {

    @Autowired
    private SwiftServiceImp swiftService;

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private SwiftRepository swiftRepository;

    @Autowired
    private PaysRepository paysRepo;

    @Autowired
    private BanqueRepository banqueRepo;

    @Autowired
    private TauxChangeRepository tauxChangeRepo;

    @Autowired
    private CompteBancaireRepository compteRepo;

    @Autowired
    private ParticipantRepository participantRepo;

    @Test
    public void testCreerSwift() {
   //declaration des entités
        Pays france = new Pays("France");
        paysRepo.save(france);

        Banque banque = new Banque("BANK2587419", "Banque Source", france);
        banqueRepo.save(banque);

        TauxChange deviseAED = new TauxChange();
        deviseAED.setDevise("AED");
        deviseAED.setCoursVente(8.593);
        deviseAED.setDateMiseAJour(LocalDate.now());
        tauxChangeRepo.save(deviseAED);
        
        PersonnePhysique donneur = new PersonnePhysique();
        donneur.setNom("Dupont");
        donneur.setPrenom("Jean");
        donneur.setAdresse("123 rue de Paris");
        participantRepo.save(donneur); 


        CompteBancaire source = new CompteBancaire();
        source.setBanque(banque);
        source.setDevise(deviseAED);
        source.setNumeroCompte("FR1234567890");
        source.setParticipant(donneur);
        compteRepo.save(source);

 
        PersonneMorale beneficiaire = new PersonneMorale();
        beneficiaire.setRaisonSociale("Société ABC");
        beneficiaire.setAdresse("456 avenue de Lyon");
        participantRepo.save(beneficiaire); 

        CompteBancaire cible = new CompteBancaire();
        cible.setBanque(banque);
        cible.setDevise(deviseAED);
        cible.setNumeroCompte("FR0987654321");
        cible.setParticipant(beneficiaire);
        compteRepo.save(cible);

        // sauvegarder le transfet en état valide
        TransfertPonctuel transfert = new TransfertPonctuel();
        transfert.setRefTransfert("TES2936");
        transfert.setDatecre(LocalDateTime.now());
        transfert.setCompteBancaire_source(source);
        transfert.setCompteBancaire_cible(cible);
        transfert.setMontantTransfert(1000.00);
        transfert.setEtatTransfert(EtatTransfert.VALIDE);
        transfert.setTypeFrais(FraisType.OUR);
        transfert = transfertRepository.save(transfert);

        ResponseEntity<byte[]> response2 = swiftService.creerSwift("TES2936");

        // Vérifications
        Assertions.assertEquals(HttpStatus.OK, response2.getStatusCode());
        Assertions.assertEquals("application/pdf", response2.getHeaders().getContentType().toString());
        Assertions.assertNotNull(response2.getBody());
        Assertions.assertTrue(response2.getBody().length > 0, "Le contenu PDF ne doit pas être vide");

        // Vérification en base : le Swift doit avoir été créé
        Swift swiftCree = swiftRepository.findByTransfert(transfert)
                              .orElse(null);
        Assertions.assertNotNull(swiftCree, "Le message Swift doit être enregistré en base");
        Assertions.assertTrue(swiftCree.getTxtmsg().contains("TES2936"), "Le message SWIFT doit contenir le code du transfert");
        
        
        
        
        //var response = swiftService.creerSwift("TES2936");
        
        
        
            // Vérifications
		/*
		 * Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
		 * response.getStatusCode()); String messageErreur = new
		 * String(response.getBody()); Assertions.assertTrue(messageErreur.
		 * contains("Le transfert avec ID TES2936 n'est pas validé. SWIFT non généré."))
		 * ;
		 */
        
    }
}
