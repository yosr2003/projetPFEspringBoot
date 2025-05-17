package com.projetPfe;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.projetPfe.entities.Banque;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.EtatTransfert;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.Pays;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.Swift;
import com.projetPfe.entities.TauxChange;
import com.projetPfe.entities.TransfertPonctuel;
import com.projetPfe.repositories.BanqueRepository;
import com.projetPfe.repositories.CompteBancaireRepository;
import com.projetPfe.repositories.PaysRepository;
import com.projetPfe.repositories.SwiftRepository;
import com.projetPfe.repositories.TauxChangeRepository;
import com.projetPfe.repositories.TransfertRepository;
import com.projetPfe.servicesImp.SwiftServiceImp;

@SpringBootTest
@Transactional
@Rollback
public class SwiftServiceIntegrationTest {

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

    @Test
    public void testCreerSwift() {
        // 1. Créer et sauver Pays
        Pays france = new Pays("France");
        paysRepo.save(france);

        // 2. Créer et sauver Banque
        Banque banque = new Banque("BANK2587419", "Banque Source", france);
        banqueRepo.save(banque);

        // 3. Créer et sauver TauxChange pour AED
        TauxChange deviseAED = new TauxChange();
        deviseAED.setDevise("AED");
        deviseAED.setCoursVente(8.593);
        deviseAED.setDateMiseAJour(LocalDate.now());
        tauxChangeRepo.save(deviseAED);

        // 4. Créer le Compte source avec Participant (cascade persist attendu sur CompteBancaire → Participant)
        PersonnePhysique donneur = new PersonnePhysique();
        donneur.setNom("Dupont");
        donneur.setPrenom("Jean");
        donneur.setAdresse("123 rue de Paris");

        CompteBancaire source = new CompteBancaire();
        source.setBanque(banque);
        source.setDevise(deviseAED);
        source.setNumeroCompte("FR1234567890");
        source.setParticipant(donneur);
        compteRepo.save(source);

        // 5. Créer le Compte cible avec Participant
        PersonneMorale beneficiaire = new PersonneMorale();
        beneficiaire.setRaisonSociale("Société ABC");
        beneficiaire.setAdresse("456 avenue de Lyon");

        CompteBancaire cible = new CompteBancaire();
        cible.setBanque(banque);
        cible.setDevise(deviseAED);
        cible.setNumeroCompte("FR0987654321");
        cible.setParticipant(beneficiaire);
        compteRepo.save(cible);

        // 6. Créer et sauver le TransfertPonctuel validé
        TransfertPonctuel transfert = new TransfertPonctuel();
        transfert.setRefTransfert("TES293625");
        transfert.setDatecre(LocalDateTime.now());
        transfert.setCompteBancaire_source(source);
        transfert.setCompteBancaire_cible(cible);
        transfert.setMontantTransfert(1000.00);
        transfert.setEtatTransfert(EtatTransfert.VALIDE);
        transfert.setTypeFrais(FraisType.OUR);
        transfert = transfertRepository.save(transfert);

        // 7. Appeler la méthode à tester
        Swift swift = swiftService.creerSwift(transfert.getRefTransfert());

        // 8. Assertions
        Assertions.assertNotNull(swift, "Le Swift ne doit pas être null");
        Assertions.assertEquals("MT103", swift.getTypemsg(), "Le type du message doit être MT103");
        Assertions.assertTrue(swift.getTxtmsg().contains("TES293625"), "Le texte doit contenir la référence du transfert");
        Assertions.assertNotNull(swift.getPdfgen(), "Le PDF généré ne doit pas être null");
        Assertions.assertTrue(swiftRepository.existsByTransfert_RefTransfert("REF12345"),
                              "Le repository doit contenir le Swift pour le transfert REF12345");
    }
}
