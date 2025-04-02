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

@SpringBootApplication
public class ProjetPfeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetPfeApplication.class, args);
	}
	 @Bean
	    CommandLineRunner initDatabase(DossierDelegueRepository dossierDelegueRepository) {
	        return args -> {
	            DossierDelegue dossier1 = new DossierDelegue(
	                "D001", 
	                "2025", 
	                EtatDoss.VALIDE, 
	                null, 
	                null, 
	                null, 
	                LocalDateTime.now(), 
	                LocalDate.of(2025, 3, 1), 
	                LocalDate.of(2025, 12, 31), 
	                null, 
	                10000.0, 
	                DossierDelegueType.SCOLARITE, 
	                List.of()
	            );

	            DossierDelegue dossier2 = new DossierDelegue(
	                "D002", 
	                "2024", 
	                EtatDoss.CLOTURE, 
	                LocalDateTime.now().minusDays(30), 
	                "Dossier terminé", 
	                null, 
	                LocalDateTime.now().minusMonths(6), 
	                LocalDate.of(2024, 1, 15), 
	                LocalDate.of(2024, 10, 15), 
	                LocalDate.of(2024, 11, 15), 
	                5000.0, 
	                DossierDelegueType.ECONOMIE_SUR_SALAIRE, 
	                List.of()
	            );

	            dossierDelegueRepository.save(dossier1);
	            dossierDelegueRepository.save(dossier2);
	        };
	    }
}
