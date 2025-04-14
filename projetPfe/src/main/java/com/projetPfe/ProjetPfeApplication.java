package com.projetPfe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierDelegueType;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.TauxChange;
import com.projetPfe.repositories.CompteBancaireRepository;
import com.projetPfe.repositories.DossierDelegueRepository;
import com.projetPfe.repositories.ParticipantRepository;
import com.projetPfe.repositories.PersonneMoraleRepository;
import com.projetPfe.repositories.PersonnePhysiqueRepository;
import com.projetPfe.repositories.TauxChangeRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ProjetPfeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetPfeApplication.class, args);
	}
	
    }

