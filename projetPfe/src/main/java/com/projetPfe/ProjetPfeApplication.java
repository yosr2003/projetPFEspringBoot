package com.projetPfe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierDelegueType;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.repositories.DossierDelegueRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ProjetPfeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetPfeApplication.class, args);
	}
//	@Bean
//	CommandLineRunner initDatabase(DossierDelegueRepository dossierDelegueRepository) {
//	    return args -> {
//	        List<DossierDelegue> dossiers = List.of(
//	            new DossierDelegue("D0016255", "2025", EtatDoss.TRAITEMENT, null, null, null, LocalDateTime.now(), LocalDate.of(2025, 3, 1), LocalDate.of(2025, 12, 31), null, 10000.0, DossierDelegueType.INVESTISSEMENT, List.of()),
//	            new DossierDelegue("D003", "2023", EtatDoss.SUSPENDU, LocalDateTime.now().minusDays(60), "Problème administratif", "Demande en cours", LocalDateTime.now().minusMonths(12), LocalDate.of(2023, 2, 10), LocalDate.of(2023, 12, 10), LocalDate.of(2024, 1, 10), 2000.0, DossierDelegueType.ECONOMIE_SUR_SALAIRE, List.of()),
//	            new DossierDelegue("D004", "2024", EtatDoss.ANNULE, LocalDateTime.now().minusDays(30), "Dossier traité", null, LocalDateTime.now().minusMonths(6), LocalDate.of(2024, 1, 5), LocalDate.of(2024, 11, 15), null, 5000.0, DossierDelegueType.CONTRAT_COMMERCIALE, List.of()),
//	            new DossierDelegue("D005", "2022", EtatDoss.TRAITEMENT, null, null, null, LocalDateTime.now().minusYears(2), LocalDate.of(2022, 5, 20), LocalDate.of(2022, 12, 31), null, 1500.0, DossierDelegueType.INVESTISSEMENT, List.of()),
//	            new DossierDelegue("D006", "2021", EtatDoss.SUSPENDU, LocalDateTime.now().minusDays(90), "Manque de justificatifs", "Révision en cours", LocalDateTime.now().minusYears(3), LocalDate.of(2021, 4, 14), LocalDate.of(2021, 9, 30), LocalDate.of(2021, 12, 15), 3000.0, DossierDelegueType.EMPREINT_EXTERIEUR, List.of()),
//	            new DossierDelegue("D007", "2020", EtatDoss.ENOVYE, LocalDateTime.now().minusDays(180), "Fin du programme", null, LocalDateTime.now().minusYears(4), LocalDate.of(2020, 7, 1), LocalDate.of(2020, 11, 30), null, 8000.0, DossierDelegueType.SCOLARITE, List.of()),
//	            new DossierDelegue("D008", "2025", EtatDoss.TRAITEMENT, null, null, null, LocalDateTime.now(), LocalDate.of(2025, 6, 10), LocalDate.of(2025, 12, 31), null, 12000.0, DossierDelegueType.ECONOMIE_SUR_SALAIRE, List.of()),
//	            new DossierDelegue("D009", "2023", EtatDoss.TRAITEMENT, null, null, null, LocalDateTime.now().minusMonths(9), LocalDate.of(2023, 3, 15), LocalDate.of(2023, 9, 30), null, 4000.0, DossierDelegueType.INVESTISSEMENT, List.of()),
//	            new DossierDelegue("D010", "2024", EtatDoss.SUSPENDU, LocalDateTime.now().minusDays(45), "Analyse complémentaire requise", "Documents en attente", LocalDateTime.now().minusMonths(3), LocalDate.of(2024, 2, 20), LocalDate.of(2024, 12, 10), LocalDate.of(2025, 1, 5), 6000.0, DossierDelegueType.SCOLARITE, List.of()),
//	            new DossierDelegue("D011", "2022", EtatDoss.CLOTURE, LocalDateTime.now().minusDays(365), "Dossier terminé", null, LocalDateTime.now().minusYears(2), LocalDate.of(2022, 8, 10), LocalDate.of(2022, 10, 30), null, 2500.0, DossierDelegueType.EMPREINT_EXTERIEUR, List.of())
//	           
//	        );
//	        
//	        dossierDelegueRepository.saveAll(dossiers);
//	        System.out.println("✅ Tous les dossiers ont été créés avec succès !");
//	    };
//	}
    }

