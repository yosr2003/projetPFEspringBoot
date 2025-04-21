package com.projetPfe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierDelegueType;
import com.projetPfe.entities.EtatDoss;

import com.projetPfe.entities.Participant;
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
	//databasePunicTrade

	public static void main(String[] args) {
		SpringApplication.run(ProjetPfeApplication.class, args);
	}
	@Bean
	CommandLineRunner initDatabase(DossierDelegueRepository dossierDelegueRepository,CompteBancaireRepository compteBancaireRepository,  PersonnePhysiqueRepository personnePhysiqueRepository,
            PersonneMoraleRepository personneMoraleRepository,
			ParticipantRepository participantRepository,
			TauxChangeRepository tauxChangeRepository) {
	    return args -> {
	    	PersonnePhysique pp1 = new PersonnePhysique();
	    	pp1.setNom("Amamou");
	    	pp1.setPrenom("Yosr");
	    	pp1.setCin("09876543");
	    	pp1.setPays("Tunisie");
	    	pp1.setAdresse("Tunis, Centre Ville");
	    	pp1.setEmail("yosr.amamou@example.com");
	    	pp1.setNumTel(20203040);

	    	PersonnePhysique pp2 = new PersonnePhysique();
	    	pp2.setNom("Ben Ali");
	    	pp2.setPrenom("Salma");
	    	pp2.setCin("12345678");
	    	pp2.setPays("Tunisie");
	    	pp2.setAdresse("Sfax, Route Mahdia");
	    	pp2.setEmail("salma.benali@example.com");
	    	pp2.setNumTel(24567890);

	    	PersonnePhysique pp3 = new PersonnePhysique();
	    	pp3.setNom("Smith");
	    	pp3.setPrenom("John");
	    	pp3.setCin("A1234567");
	    	pp3.setPays("États-Unis");
	    	pp3.setAdresse("New York, 5th Avenue");
	    	pp3.setEmail("john.smith@example.com");
	    	pp3.setNumTel(123456789);

	    	PersonnePhysique pp4 = new PersonnePhysique();
	    	pp4.setNom("Dupont");
	    	pp4.setPrenom("Marie");
	    	pp4.setCin("B7654321");
	    	pp4.setPays("France");
	    	pp4.setAdresse("Paris, Rue de Rivoli");
	    	pp4.setEmail("marie.dupont@example.com");
	    	pp4.setNumTel(336123456);

	    	PersonnePhysique pp5 = new PersonnePhysique();
	    	pp5.setNom("Nguyen");
	    	pp5.setPrenom("Linh");
	    	pp5.setCin("VN998877");
	    	pp5.setPays("Vietnam");
	    	pp5.setAdresse("Hanoi, Ba Dinh");
	    	pp5.setEmail("linh.nguyen@example.com");
	    	pp5.setNumTel(849012345);

	    	PersonnePhysique pp6 = new PersonnePhysique();
	    	pp6.setNom("Alvarez");
	    	pp6.setPrenom("Carlos");
	    	pp6.setCin("ES334455");
	    	pp6.setPays("Espagne");
	    	pp6.setAdresse("Madrid, Gran Via");
	    	pp6.setEmail("carlos.alvarez@example.com");
	    	pp6.setNumTel(346789012);

	    	PersonnePhysique pp7 = new PersonnePhysique();
	    	pp7.setNom("Mejri");
	    	pp7.setPrenom("Rania");
	    	pp7.setCin("87654321");
	    	pp7.setPays("Tunisie");
	    	pp7.setAdresse("Bizerte, El Alia");
	    	pp7.setEmail("rania.mejri@example.com");
	    	pp7.setNumTel(21123456);

	    	PersonnePhysique pp8 = new PersonnePhysique();
	    	pp8.setNom("karim");
	    	pp8.setPrenom("Ali");
	    	pp8.setCin("LY112233");
	    	pp8.setPays("Libye");
	    	pp8.setAdresse("Tripoli, Al Andalus");
	    	pp8.setEmail("ali.omar@example.com");
	    	pp8.setNumTel(218912345);

	    	PersonnePhysique pp9 = new PersonnePhysique();
	    	pp9.setNom("Kouassi");
	    	pp9.setPrenom("Jean");
	    	pp9.setCin("CI998877");
	    	pp9.setPays("Côte d'Ivoire");
	    	pp9.setAdresse("Abidjan, Cocody");
	    	pp9.setEmail("jean.kouassi@example.com");
	    	pp9.setNumTel(225070012);

	    	PersonnePhysique pp10 = new PersonnePhysique();
	    	pp10.setNom("Baccar");
	    	pp10.setPrenom("Imen");
	    	pp10.setCin("45678912");
	    	pp10.setPays("Tunisie");
	    	pp10.setAdresse("Sousse, Hammam Sousse");
	    	pp10.setEmail("imen.baccar@example.com");
	    	pp10.setNumTel(22119876);


	        PersonneMorale pm1 = new PersonneMorale();
	        pm1.setCodeDouane("TN45678");
	        pm1.setRaisonSociale("Tech Innov SARL");
	        pm1.setPays("Tunisie");
	        pm1.setAdresse("Sfax Technopôle");
	        pm1.setEmail("contact@techinnov.tn");
	        pm1.setNumTel(71234567);
	        PersonneMorale pm2 = new PersonneMorale();
	        pm2.setCodeDouane("FR98765");
	        pm2.setRaisonSociale("Global Trade Solutions");
	        pm2.setPays("France");
	        pm2.setAdresse("21 Rue des Entrepreneurs, Paris");
	        pm2.setEmail("info@globaltrade.fr");
	        pm2.setNumTel(331789456);
	        PersonneMorale pm3 = new PersonneMorale();
	        pm3.setCodeDouane("DE12345");
	        pm3.setRaisonSociale("Bavaria Export GmbH");
	        pm3.setPays("Allemagne");
	        pm3.setAdresse("Königsplatz 5, Munich");
	        pm3.setEmail("export@bavaria-gmbh.de");
	        pm3.setNumTel(49891234);

	        PersonneMorale pm4 = new PersonneMorale();
	        pm4.setCodeDouane("US67890");
	        pm4.setRaisonSociale("California Trading Inc.");
	        pm4.setPays("États-Unis");
	        pm4.setAdresse("500 Market Street, San Francisco, CA");
	        pm4.setEmail("support@caltrading.com");
	        pm4.setNumTel(140855512);

	        PersonneMorale pm5 = new PersonneMorale();
	        pm5.setCodeDouane("JP11223");
	        pm5.setRaisonSociale("Nippon Tech Co. Ltd.");
	        pm5.setPays("Japon");
	        pm5.setAdresse("2-1 Marunouchi, Tokyo");
	        pm5.setEmail("info@nippontech.jp");
	        pm5.setNumTel(813123456);

	        PersonneMorale pm6 = new PersonneMorale();
	        pm6.setCodeDouane("MA33445");
	        pm6.setRaisonSociale("Casablanca Export");
	        pm6.setPays("Maroc");
	        pm6.setAdresse("Zone Industrielle Ain Sebaa, Casablanca");
	        pm6.setEmail("contact@casablancaexport.ma");
	        pm6.setNumTel(522345678);

	        PersonneMorale pm7 = new PersonneMorale();
	        pm7.setCodeDouane("IT55667");
	        pm7.setRaisonSociale("Milano Mercato S.p.A");
	        pm7.setPays("Italie");
	        pm7.setAdresse("Via Torino 8, Milano");
	        pm7.setEmail("info@milano-mercato.it");
	        pm7.setNumTel(390223456);

	        PersonneMorale pm8 = new PersonneMorale();
	        pm8.setCodeDouane("CA88990");
	        pm8.setRaisonSociale("Maple Leaf Logistics");
	        pm8.setPays("Canada");
	        pm8.setAdresse("1200 Rue Sainte-Catherine, Montréal");
	        pm8.setEmail("contact@maplelogistics.ca");
	        pm8.setNumTel(151498765);

	        PersonneMorale pm9 = new PersonneMorale();
	        pm9.setCodeDouane("CN77889");
	        pm9.setRaisonSociale("Shenzhen Import Co.");
	        pm9.setPays("Chine");
	        pm9.setAdresse("Huaqiangbei Street, Shenzhen");
	        pm9.setEmail("sales@shenzhenimport.cn");
	        pm9.setNumTel(867558964);

	        PersonneMorale pm10 = new PersonneMorale();
	        pm10.setCodeDouane("BR44556");
	        pm10.setRaisonSociale("Rio Export Ltda");
	        pm10.setPays("Brésil");
	        pm10.setAdresse("Av. Atlântica 1500, Rio de Janeiro");
	        pm10.setEmail("info@rioexport.com.br");
	        pm10.setNumTel(552198765);

	        PersonneMorale pm11 = new PersonneMorale();
	        pm11.setCodeDouane("AE22334");
	        pm11.setRaisonSociale("Dubai Trade Hub");
	        pm11.setPays("Émirats Arabes Unis");
	        pm11.setAdresse("Jebel Ali Free Zone, Dubaï");
	        pm11.setEmail("services@dubaith.com");
	        pm11.setNumTel(97145001);
	        personnePhysiqueRepository.saveAll(List.of(pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp8, pp9, pp10));
	        personneMoraleRepository.saveAll(List.of(pm1, pm2, pm3, pm4, pm5, pm6, pm7, pm8, pm9, pm10, pm11));

	        

	     // 2️⃣ Créer quelques Taux de change
	        TauxChange devise1 = new TauxChange();
	        devise1.setDevise("AED");
	        devise1.setCoursVente(8.593);
	        devise1.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise1);

	        TauxChange devise2 = new TauxChange();
	        devise2.setDevise("BHD");
	        devise2.setCoursVente(8.373);
	        devise2.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise2);

	        TauxChange devise3 = new TauxChange();
	        devise3.setDevise("CAD");
	        devise3.setCoursVente(2.201);
	        devise3.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise3);

	        TauxChange devise4 = new TauxChange();
	        devise4.setDevise("CHF");
	        devise4.setCoursVente(35.763);
	        devise4.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise4);

	        TauxChange devise5 = new TauxChange();
	        devise5.setDevise("CNY");
	        devise5.setCoursVente(0.435);
	        devise5.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise5);

	        TauxChange devise6 = new TauxChange();
	        devise6.setDevise("DKK");
	        devise6.setCoursVente(45.855);
	        devise6.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise6);

	        TauxChange devise7 = new TauxChange();
	        devise7.setDevise("EUR");
	        devise7.setCoursVente(3.398);
	        devise7.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise7);

	        TauxChange devise8 = new TauxChange();
	        devise8.setDevise("GBP");
	        devise8.setCoursVente(4.082);
	        devise8.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise8);

	        TauxChange devise9 = new TauxChange();
	        devise9.setDevise("JPY");
	        devise9.setCoursVente(21.098);
	        devise9.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise9);

	        TauxChange devise10 = new TauxChange();
	        devise10.setDevise("USD");
	        devise10.setCoursVente(3.156);
	        devise10.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise10);

	        TauxChange devise11 = new TauxChange();
	        devise11.setDevise("KWD");
	        devise11.setCoursVente(10.245);
	        devise11.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise11);

	        TauxChange devise12 = new TauxChange();
	        devise12.setDevise("NOK");
	        devise12.setCoursVente(29.988);
	        devise12.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise12);

	        TauxChange devise13 = new TauxChange();
	        devise13.setDevise("QAR");
	        devise13.setCoursVente(8.660);
	        devise13.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise13);

	        TauxChange devise14 = new TauxChange();
	        devise14.setDevise("SAR");
	        devise14.setCoursVente(8.439);
	        devise14.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise14);

	        TauxChange devise15 = new TauxChange();
	        devise15.setDevise("SEK");
	        devise15.setCoursVente(3.125);
	        devise15.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(devise15);

	        // Créer TauxChange pour TND
	        TauxChange deviseTND = new TauxChange();
	        deviseTND.setDevise("TND");
	        deviseTND.setCoursVente(1.0);  // Exemple de taux, à ajuster selon les valeurs réelles
	        deviseTND.setDateMiseAJour(LocalDate.now());
	        tauxChangeRepository.save(deviseTND);


	        // Compte 1 – associé à une personne physique (pm2) avec devise AED
	        CompteBancaire compte1 = new CompteBancaire();
	        compte1.setNumeroCompte("FR7630006000");
	        compte1.setMontant(10000.0);
	        compte1.setBanque("BNP Paribas");
	        compte1.setBIC("BNPAFRPPXXX");
	        compte1.setPays("France");
	        compte1.setCodePays(2024);
	        compte1.setParticipant(pm2);
	        compte1.setDevise(devise1);  // Devise AED
	        compteBancaireRepository.save(compte1);

	        // Compte 2 – associé à une personne physique (pp1) avec devise TND
	        CompteBancaire compte2 = new CompteBancaire();
	        compte2.setNumeroCompte("TN5900012345");
	        compte2.setMontant(4800.0);
	        compte2.setBanque("BIAT");
	        compte2.setBIC("BIATTNTTXXX");
	        compte2.setPays("Tunisie");
	        compte1.setCodePays(3035);
	        compte2.setParticipant(pp1);
	        compte2.setDevise(deviseTND);  // Devise TND
	        compteBancaireRepository.save(compte2);

	        // Compte 3 – associé à une personne morale (pm3) avec devise CAD
	        CompteBancaire compte3 = new CompteBancaire();
	        compte3.setNumeroCompte("DE8937040044");
	        compte3.setMontant(150000.0);
	        compte3.setBanque("Deutsche Bank");
	        compte3.setBIC("DEUTDEFFXXX");
	        compte3.setPays("Allemagne");
	        compte1.setCodePays(8084);
	        compte3.setParticipant(pm3);
	        compte3.setDevise(devise3);  // Devise CAD
	        compteBancaireRepository.save(compte3);

	        // Compte 4 – associé à pp3 avec devise USD
	        CompteBancaire compte4 = new CompteBancaire();
	        compte4.setNumeroCompte("US1234567890");
	        compte4.setMontant(7200.0);
	        compte4.setBanque("Chase Bank");
	        compte4.setBIC("CHASUS33XXX");
	        compte4.setPays("États-Unis");
	        compte1.setCodePays(3054);
	        compte4.setParticipant(pp3);
	        compte4.setDevise(devise10);  // Devise USD
	        compteBancaireRepository.save(compte4);

	        // Compte 5 – associé à pm5 avec devise JPY
	        CompteBancaire compte5 = new CompteBancaire();
	        compte5.setNumeroCompte("JP5600001234");
	        compte5.setMontant(95000.0);
	        compte5.setBanque("Mizuho Bank");
	        compte5.setBIC("MHCBJPJTXXX");
	        compte5.setPays("Japon");
	        compte1.setCodePays(3025);
	        compte5.setParticipant(pm5);
	        compte5.setDevise(devise9);  // Devise JPY
	        compteBancaireRepository.save(compte5);

	        // Compte 6 – associé à pp6 avec devise EUR
	        CompteBancaire compte6 = new CompteBancaire();
	        compte6.setNumeroCompte("ES2100067890");
	        compte6.setMontant(6100.0);
	        compte6.setBanque("Banco Santander");
	        compte6.setBIC("BSCHESMMXXX");
	        compte6.setPays("Espagne");
	        compte1.setCodePays(9874);
	        compte6.setParticipant(pp6);
	        compte6.setDevise(devise7);  // Devise EUR
	        compteBancaireRepository.save(compte6);

	        // Compte 7 – associé à pm8 avec devise CAD
	        CompteBancaire compte7 = new CompteBancaire();
	        compte7.setNumeroCompte("CA9000123456");
	        compte7.setMontant(200000.0);
	        compte7.setBanque("Royal Bank of Canada");
	        compte7.setBIC("ROYCCAT2XXX");
	        compte7.setPays("Canada");
	        compte1.setCodePays(7852);
	        compte7.setParticipant(pm8);
	        compte7.setDevise(devise3);  // Devise CAD
	        compteBancaireRepository.save(compte7);

	        // Compte 8 – associé à pp9 avec devise EUR
	        CompteBancaire compte8 = new CompteBancaire();
	        compte8.setNumeroCompte("CI1234005678");
	        compte8.setMontant(5300.0);
	        compte8.setBanque("Banque Atlantique");
	        compte8.setBIC("ATCIxxxxXXX");
	        compte8.setPays("Côte d'Ivoire");
	        compte1.setCodePays(3024);
	        compte8.setParticipant(pp9);
	        compte8.setDevise(devise7);  // Devise EUR
	        compteBancaireRepository.save(compte8);

	        // Compte 9 – associé à pm10 avec devise BRL
	        CompteBancaire compte9 = new CompteBancaire();
	        compte9.setNumeroCompte("BR1200456789");
	        compte9.setMontant(88000.0);
	        compte9.setBanque("Banco do Brasil");
	        compte9.setBIC("BRASBRRXXXX");
	        compte9.setPays("Brésil");
	        compte1.setCodePays(7894);
	        compte9.setParticipant(pm10);
	        compte9.setDevise(devise11);  // Devise BRL
	        compteBancaireRepository.save(compte9);

	        // Compte 10 – associé à pp10 avec devise TND
	        CompteBancaire compte10 = new CompteBancaire();
	        compte10.setNumeroCompte("TN4500987612");
	        compte10.setMontant(4100.0);
	        compte10.setBanque("STB");
	        compte10.setBIC("STBKTNTTXXX");
	        compte10.setPays("Tunisie");
	        compte1.setCodePays(3221);
	        compte10.setParticipant(pp10);
	        compte10.setDevise(deviseTND);  // Devise TND
	        compteBancaireRepository.save(compte10);

	        // Compte 11 – associé à pm11 avec devise AED
	        CompteBancaire compte11 = new CompteBancaire();
	        compte11.setNumeroCompte("AE0987001234");
	        compte11.setMontant(175000.0);
	        compte11.setBanque("Emirates NBD");
	        compte11.setBIC("EBILAEADXXX");
	        compte11.setPays("Émirats Arabes Unis");
	        compte1.setCodePays(8795);
	        compte11.setParticipant(pm11);
	        compte11.setDevise(devise1);  // Devise AED
	        compteBancaireRepository.save(compte11);

		        System.out.println("✅ Compte bancaire ajouté avec succès !");
	        List<DossierDelegue> dossiers = List.of(
	            new DossierDelegue("D0016255", "2025", EtatDoss.TRAITEMENT, null, null, null, LocalDateTime.now(), LocalDate.of(2025, 3, 1), LocalDate.of(2025, 12, 31), null, 10000.0, DossierDelegueType.INVESTISSEMENT, List.of()),
	            new DossierDelegue("D003", "2023", EtatDoss.VALIDE, LocalDateTime.now().minusDays(60), "Problème administratif", "Demande en cours", LocalDateTime.now().minusMonths(12), LocalDate.of(2023, 2, 10), LocalDate.of(2023, 12, 10), LocalDate.of(2024, 1, 10), 2000.0, DossierDelegueType.SCOLARITE, List.of()),
	            new DossierDelegue("D004", "2024", EtatDoss.ANNULE, LocalDateTime.now().minusDays(30), "Dossier traité", null, LocalDateTime.now().minusMonths(6), LocalDate.of(2024, 1, 5), LocalDate.of(2024, 11, 15), null, 5000.0, DossierDelegueType.CONTRAT_COMMERCIALE, List.of()),
	            new DossierDelegue("D005", "2022", EtatDoss.TRAITEMENT, null, null, null, LocalDateTime.now().minusYears(2), LocalDate.of(2022, 5, 20), LocalDate.of(2022, 12, 31), null, 1500.0, DossierDelegueType.INVESTISSEMENT, List.of()),
	            new DossierDelegue("D006", "2021", EtatDoss.REJETE, LocalDateTime.now().minusDays(90), "Manque de justificatifs", "Révision en cours", LocalDateTime.now().minusYears(3), LocalDate.of(2021, 4, 14), LocalDate.of(2021, 9, 30), LocalDate.of(2021, 12, 15), 3000.0, DossierDelegueType.EMPREINT_EXTERIEUR, List.of()),
	            new DossierDelegue("D007", "2020", EtatDoss.OUVERT, LocalDateTime.now().minusDays(180), "Fin du programme", null, LocalDateTime.now().minusYears(4), LocalDate.of(2020, 7, 1), LocalDate.of(2020, 11, 30), null, 8000.0, DossierDelegueType.SCOLARITE, List.of()),
	            new DossierDelegue("D008", "2025", EtatDoss.TRAITEMENT, null, null, null, LocalDateTime.now(), LocalDate.of(2025, 6, 10), LocalDate.of(2025, 12, 31), null, 12000.0, DossierDelegueType.ECONOMIE_SUR_SALAIRE, List.of()),
	            new DossierDelegue("D009", "2023", EtatDoss.TRAITEMENT, null, null, null, LocalDateTime.now().minusMonths(9), LocalDate.of(2023, 3, 15), LocalDate.of(2023, 9, 30), null, 4000.0, DossierDelegueType.INVESTISSEMENT, List.of()),
	            new DossierDelegue("D010", "2024", EtatDoss.REJETE , LocalDateTime.now().minusDays(45), "Analyse complémentaire requise", "Documents en attente", LocalDateTime.now().minusMonths(3), LocalDate.of(2024, 2, 20), LocalDate.of(2024, 12, 10), LocalDate.of(2025, 1, 5), 6000.0, DossierDelegueType.SCOLARITE, List.of()),
	            new DossierDelegue("D011", "2022", EtatDoss.CLOTURE, LocalDateTime.now().minusDays(365), "Dossier terminé", null, LocalDateTime.now().minusYears(2), LocalDate.of(2022, 8, 10), LocalDate.of(2022, 10, 30), null, 2500.0, DossierDelegueType.EMPREINT_EXTERIEUR, List.of())
	           
	        );
	        
	        dossierDelegueRepository.saveAll(dossiers);
	        System.out.println("✅ Tous les dossiers ont été créés avec succès !");
	    };
	}

    }

