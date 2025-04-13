package com.projetPfe;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.projetPfe.entities.DossierScolarite;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.Transfert;
import com.projetPfe.entities.TransfertNature;
import com.projetPfe.entities.TransfertType;
import com.projetPfe.repositories.DossierDelegueRepository;
import com.projetPfe.repositories.TransfertRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ProjetPfeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetPfeApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(
            DossierDelegueRepository dossierDelegueRepository,
            TransfertRepository transfertRepository
    ) {
        return args -> {

            // Création d’un dossier SCOLARITE avec données complètes
            DossierScolarite dossierScol = new DossierScolarite();
            dossierScol.setIdDossier("D1000");
            dossierScol.setAnneedoss("2024/2025");
            dossierScol.setEtatDoss(EtatDoss.Traitement);
            dossierScol.setDateCre(LocalDateTime.now().minusMonths(2));
            dossierScol.setDateDebut(LocalDate.of(2024, 9, 1));
            dossierScol.setDateExpiration(LocalDate.of(2025, 6, 30));
            dossierScol.setSolde(9500.0);
            dossierScol.setNomEtudiant("Yasmine");
            dossierScol.setPrenomEtudiant("Ben Ali");
            dossierScol.setCinEtudiant("01234567");
            dossierScol.setPaysEtudes("Belgique");
            dossierScol.setSpecialite("Architecture");

            // Sauvegarder le dossier
            dossierDelegueRepository.save(dossierScol);

            // Transferts associés (différentes dates, montants et états)
            List<Transfert> transferts = List.of(
                    new Transfert("TR001", TransfertType.FINANCIER, 500.0, null, null,
                            LocalDateTime.of(2024, 9, 10, 10, 0), EtatDoss.Validé,
                            LocalDate.of(2024, 9, 30), TransfertNature.COURANT,
                            0.0, FraisType.BEN, 500.0, dossierScol, null),

                    new Transfert("TR002", TransfertType.FINANCIER, 750.0, null, null,
                            LocalDateTime.of(2024, 10, 5, 11, 30), EtatDoss.Validé,
                            LocalDate.of(2024, 10, 30), TransfertNature.COURANT,
                            0.0, FraisType.SHA, 750.0, dossierScol, null),

                    new Transfert("TR003", TransfertType.FINANCIER, 1000.0, null, null,
                            LocalDateTime.of(2024, 11, 2, 9, 45), EtatDoss.Validé,
                            LocalDate.of(2024, 11, 30), TransfertNature.COURANT,
                            0.0, FraisType.OUR, 1000.0, dossierScol, null)
            );

            transfertRepository.saveAll(transferts);

            System.out.println("✅ Dossier scolarité 'D1000' avec 3 transferts créé avec succès !");
        };
    }
}

