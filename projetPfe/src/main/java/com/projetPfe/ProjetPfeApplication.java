package com.projetPfe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.projetPfe.entities.Banque;
import com.projetPfe.entities.CompteBancaire;
import com.projetPfe.entities.DossierContratCommercial;
import com.projetPfe.entities.DossierEconomieSurSalaire;
import com.projetPfe.entities.DossierEmpreint;
import com.projetPfe.entities.DossierFormationProfessionnelle;
import com.projetPfe.entities.DossierInvestissement;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.entities.DossierSoinMedical;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.EtatTransfert;
import com.projetPfe.entities.FraisType;
import com.projetPfe.entities.Pays;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.TauxChange;
import com.projetPfe.entities.TransfertPermanent;
import com.projetPfe.repositories.BanqueRepository;
import com.projetPfe.repositories.CompteBancaireRepository;
import com.projetPfe.repositories.ParticipantRepository;
import com.projetPfe.repositories.PaysRepository;
import com.projetPfe.repositories.PersonneMoralRepository;
import com.projetPfe.repositories.PersonnePhysiqueRepository;
import com.projetPfe.repositories.TauxChangeRepository;
import com.projetPfe.repositories.TransfertPermanentRepository;
import com.projetPfe.repositories.dossierContratCommercialRepository;
import com.projetPfe.repositories.dossierDelegueRepository;
import com.projetPfe.repositories.dossierEconomieSurSalaireRepository;
import com.projetPfe.repositories.dossierEmpreintRepository;
import com.projetPfe.repositories.dossierFormationProfRepository;
import com.projetPfe.repositories.dossierInvestissementRepository;
import com.projetPfe.repositories.dossierScolariteRepository;



@SpringBootApplication
public class ProjetPfeApplication {
	//databasePunicTrade

	public static void main(String[] args) {
		SpringApplication.run(ProjetPfeApplication.class, args);
		
	}
	@Bean
	CommandLineRunner initDatabase(dossierDelegueRepository dossierDelegueRepository,TransfertPermanentRepository transfertpRepop,CompteBancaireRepository compteBancaireRepository,  PersonnePhysiqueRepository personnePhysiqueRepository,
            PersonneMoralRepository personneMoraleRepository, dossierScolariteRepository scRepository, dossierEconomieSurSalaireRepository salaireRepository, dossierEmpreintRepository empRepository,  dossierFormationProfRepository  DossierFormationProfRepository, 
            dossierInvestissementRepository  dossierInvestissementRepository,  dossierContratCommercialRepository dossierContratCommercial, 
			ParticipantRepository participantRepository,
			TauxChangeRepository tauxChangeRepository, PaysRepository paysRepository, BanqueRepository banqueRepository) {
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


	   


		        Pays france = new Pays("France");
		        Pays tunisie = new Pays("Tunisie");
		        Pays allemagne = new Pays("Allemagne");
		        Pays usa = new Pays("États-Unis");
		        Pays japon = new Pays("Japon");
		        Pays espagne = new Pays("Espagne");
		        Pays canada = new Pays("Canada");
		        Pays coteIvoire = new Pays("Côte d'Ivoire");
		        Pays bresil = new Pays("Brésil");
		        Pays emirats = new Pays("Émirats Arabes Unis");

		        paysRepository.saveAll(Arrays.asList(
		            france, tunisie, allemagne, usa, japon,
		            espagne, canada, coteIvoire, bresil, emirats
		        ));
		        
		        
		        Banque bnpParibas = new Banque( "BNPAFRPPXXX","BNP Paribas", france);
		        Banque biat = new Banque( "BIATTNTTXXX", "BIAT",tunisie);
		        Banque deutscheBank = new Banque("DEUTDEFFXXX","Deutsche Bank",  allemagne);
		        Banque bankOfAmerica = new Banque("BOFAUS3NXXX","Bank of America",  usa);
		        Banque mufg = new Banque("BOTKJPJTXXX","MUFG",  japon);
		        Banque santander = new Banque( "BSCHESMMXXX","Santander", espagne);
		        Banque rbc = new Banque("ROYCCAT2XXX","RBC",  canada);
		        Banque sgci = new Banque("SGCICIABXXX", "Société Générale CI", coteIvoire);
		        Banque bancoDoBrasil = new Banque( "BRASBRRJBHE","Banco do Brasil", bresil);
		        Banque atb = new Banque("ATBKTNTTXXX", "ATB", tunisie);
		        Banque emiratesNBD = new Banque("EBILAEADXXX","Emirates NBD",  emirats);

		        banqueRepository.saveAll(Arrays.asList(
		            bnpParibas, biat, deutscheBank, bankOfAmerica, mufg,
		            santander, rbc, sgci, bancoDoBrasil, atb, emiratesNBD
		        ));


		        // Compte 1 – associé à une personne physique (pm2) avec devise AED
		        CompteBancaire compte1 = new CompteBancaire();
		        compte1.setNumeroCompte("FR7630006000");
		        compte1.setMontant(10000.0);
		        compte1.setParticipant(pm2);
		        compte1.setDevise(devise1);  // AED
		        compte1.setBanque(emiratesNBD);
		        compteBancaireRepository.save(compte1);

		        // Compte 2 – associé à une personne physique (pp1) avec devise TND
		        CompteBancaire compte2 = new CompteBancaire();
		        compte2.setNumeroCompte("TN5900012345");
		        compte2.setMontant(4800.0);
		        compte2.setParticipant(pp1);
		        compte2.setDevise(deviseTND);
		        compte2.setBanque(biat);
		        compteBancaireRepository.save(compte2);

		        // Compte 3 – associé à une personne morale (pm3) avec devise CAD
		        CompteBancaire compte3 = new CompteBancaire();
		        compte3.setNumeroCompte("DE8937040044");
		        compte3.setMontant(150000.0);
		        compte3.setParticipant(pm3);
		        compte3.setDevise(devise3);  // CAD
		        compte3.setBanque(deutscheBank);
		        compteBancaireRepository.save(compte3);

		        // Compte 4 – associé à pp3 avec devise USD
		        CompteBancaire compte4 = new CompteBancaire();
		        compte4.setNumeroCompte("US1234567890");
		        compte4.setMontant(7200.0);
		        compte4.setParticipant(pp3);
		        compte4.setDevise(devise10);  // USD
		        compte4.setBanque(bankOfAmerica);
		        compteBancaireRepository.save(compte4);

		        // Compte 5 – associé à pm5 avec devise JPY
		        CompteBancaire compte5 = new CompteBancaire();
		        compte5.setNumeroCompte("JP5600001234");
		        compte5.setMontant(95000.0);
		        compte5.setParticipant(pm5);
		        compte5.setDevise(devise9);  // JPY
		        compte5.setBanque(mufg);
		        compteBancaireRepository.save(compte5);

		        // Compte 6 – associé à pp6 avec devise EUR
		        CompteBancaire compte6 = new CompteBancaire();
		        compte6.setNumeroCompte("ES2100067890");
		        compte6.setMontant(6100.0);
		        compte6.setParticipant(pp6);
		        compte6.setDevise(devise7);  // EUR
		        compte6.setBanque(santander);
		        compteBancaireRepository.save(compte6);

		        // Compte 7 – associé à pm8 avec devise CAD
		        CompteBancaire compte7 = new CompteBancaire();
		        compte7.setNumeroCompte("CA9000123456");
		        compte7.setMontant(200000.0);
		        compte7.setParticipant(pm8);
		        compte7.setDevise(devise3);  // CAD
		        compte7.setBanque(rbc);
		        compteBancaireRepository.save(compte7);

		        // Compte 8 – associé à pp9 avec devise EUR
		        CompteBancaire compte8 = new CompteBancaire();
		        compte8.setNumeroCompte("CI1234005678");
		        compte8.setMontant(5300.0);
		        compte8.setParticipant(pp9);
		        compte8.setDevise(devise7);  // EUR
		        compte8.setBanque(sgci);
		        compteBancaireRepository.save(compte8);

		        // Compte 9 – associé à pm10 avec devise BRL
		        CompteBancaire compte9 = new CompteBancaire();
		        compte9.setNumeroCompte("BR1200456789");
		        compte9.setMontant(88000.0);
		        compte9.setParticipant(pm10);
		        compte9.setDevise(devise11);  // BRL
		        compte9.setBanque(bancoDoBrasil);
		        compteBancaireRepository.save(compte9);

		        // Compte 10 – associé à pp10 avec devise TND
		        CompteBancaire compte10 = new CompteBancaire();
		        compte10.setNumeroCompte("TN4500987612");
		        compte10.setMontant(4100.0);
		        compte10.setParticipant(pp10);
		        compte10.setDevise(deviseTND);  // TND
		        compte10.setBanque(atb);
		        compteBancaireRepository.save(compte10);

		        // Compte 11 – associé à pm11 avec devise AED
		        CompteBancaire compte11 = new CompteBancaire();
		        compte11.setNumeroCompte("AE0987001234");
		        compte11.setMontant(175000.0);
		        compte11.setParticipant(pm11);
		        compte11.setDevise(devise1);  // AED
		        compte11.setBanque(emiratesNBD);
		        compteBancaireRepository.save(compte11);

		        System.out.println("✅ Compte bancaire ajouté avec succès !");
		        
		        
		        DossierEconomieSurSalaire doss1 = new DossierEconomieSurSalaire();
		        doss1.setIdDossier("DOSS001");
		        doss1.setDateDebut(LocalDate.of(2025, 7, 10));
		        doss1.setDateExpiration(LocalDate.of(2026, 7, 10));
		        doss1.setDateCre(LocalDateTime.now());
		        doss1.setEtatDossier(EtatDoss.VALIDE);  // valide mais non ouvert
		        doss1.setMontantMensuel(1200.0);
		        
		        TransfertPermanent tes1 = new TransfertPermanent();
		        tes1.setRefTransfert("TES001");
		        tes1.setDossierDelegue(doss1);
		        tes1.setCompteBancaire_source(compte2);
		        tes1.setCompteBancaire_cible(compte11);
		        tes1.setMontantTransfert(5000.0);
		        tes1.setTypeFrais(FraisType.OUR);
		        tes1.setFrais(0.0);
		        tes1.setMontantFrais(0.0);
		        tes1.setMontantFinal(43000.0);
		        tes1.setNatureOperation("épargne mensuelle");
		        tes1.setEtatTransfert(EtatTransfert.TRAITEMENT);
		        tes1.setDatecre(LocalDateTime.of(2025, 6, 25, 10, 0));
		        tes1.setDateEnvoie(null); // pas encore envoyé

		        // Deuxième transfert en traitement, pas encore envoyé
		        TransfertPermanent tes2 = new TransfertPermanent();
		        tes2.setRefTransfert("TES002");
		        tes2.setDossierDelegue(doss1);
		        tes2.setCompteBancaire_source(compte2);
		        tes2.setCompteBancaire_cible(compte11);
		        tes2.setMontantTransfert(3000.0);
		        tes2.setTypeFrais(FraisType.BEN);
		        tes2.setFrais(50.0);
		        tes2.setMontantFrais(50.0);
		        tes2.setMontantFinal(25800.0);
		        tes2.setNatureOperation("intérêts épargne");
		        tes2.setEtatTransfert(EtatTransfert.TRAITEMENT);
		        tes2.setDatecre(LocalDateTime.of(2025, 6, 28, 11, 30));
		        tes2.setDateEnvoie(null);

//		        DossierEconomieSurSalaire doss2 = new DossierEconomieSurSalaire();
//		        doss2.setIdDossier("DOSS002");
//		        doss2.setDateDebut(LocalDate.of(2023, 6, 15));
//		        doss2.setDateExpiration(LocalDate.of(2024, 6, 15));
//		        doss2.setDateCre(LocalDateTime.now());
//		        doss2.setEtatDossier(EtatDoss.CLOTURE);  // Enum à ajuster
//		        doss2.setMotifcloture("Fin du contrat");
//		        doss2.setMontantMensuel(900.0);
//
//		        DossierEconomieSurSalaire doss3 = new DossierEconomieSurSalaire();
//		        doss3.setIdDossier("DOSS003");
//		        doss3.setDateDebut(LocalDate.of(2024, 3, 1));
//		        doss3.setDateExpiration(LocalDate.of(2024, 12, 31));
//		        doss3.setDateCre(LocalDateTime.now());
//		        doss3.setEtatDossier(EtatDoss.VALIDE);  // Enum à adapter
//		        doss3.setMontantMensuel(1500.0);
		        
		        
		        DossierContratCommercial contrat1 = new DossierContratCommercial();
		        contrat1.setIdDossier("DOSS_CC001");
		        contrat1.setDateDebut(LocalDate.of(2024, 2, 1));
		        contrat1.setDateExpiration(LocalDate.of(2025, 2, 1));
		        contrat1.setDateCre(LocalDateTime.now());
		        contrat1.setEtatDossier(EtatDoss.TRAITEMENT);  // Enum à ajuster
		        contrat1.setMontantContrat(50000.0);
		        
		        TransfertPermanent tcc1 = new TransfertPermanent();
		        tcc1.setRefTransfert("TCC001");
		        tcc1.setDossierDelegue(contrat1);
		        tcc1.setCompteBancaire_source(compte2);
		        tcc1.setCompteBancaire_cible(compte11);
		        tcc1.setMontantTransfert(15000.0);
		        tcc1.setTypeFrais(FraisType.OUR);
		        tcc1.setFrais(0.0);
		        tcc1.setMontantFrais(0.0);
		        tcc1.setMontantFinal(129000.0);
		        tcc1.setNatureOperation("avance contrat");
		        tcc1.setEtatTransfert(EtatTransfert.TRAITEMENT);
		        tcc1.setDatecre(LocalDateTime.of(2025, 6, 15, 10, 0));
		        tcc1.setDateEnvoie(null); // pas encore envoyé

		        // Deuxième transfert en traitement sans date d'envoi
		        TransfertPermanent tcc2 = new TransfertPermanent();
		        tcc2.setRefTransfert("TCC002");
		        tcc2.setDossierDelegue(contrat1);
		        tcc2.setCompteBancaire_source(compte2);
		        tcc2.setCompteBancaire_cible(compte11);
		        tcc2.setMontantTransfert(10000.0);
		        tcc2.setTypeFrais(FraisType.BEN);
		        tcc2.setFrais(50.0);
		        tcc2.setMontantFrais(50.0);
		        tcc2.setMontantFinal(86000.0);
		        tcc2.setNatureOperation("frais administratifs");
		        tcc2.setEtatTransfert(EtatTransfert.TRAITEMENT);
		        tcc2.setDatecre(LocalDateTime.of(2025, 6, 20, 15, 0));
		        tcc2.setDateEnvoie(null);

//		        DossierContratCommercial contrat2 = new DossierContratCommercial();
//		        contrat2.setIdDossier("DOSS_CC002");
//		        contrat2.setDateDebut(LocalDate.of(2023, 9, 15));
//		        contrat2.setDateExpiration(LocalDate.of(2024, 9, 15));
//		        contrat2.setDateCre(LocalDateTime.now());
//		        contrat2.setEtatDossier(EtatDoss.CLOTURE);
//		        contrat2.setMontantContrat(80000.0);
//		        contrat2.setMotifcloture("Projet terminé avec succès");
//
//		        DossierContratCommercial contrat3 = new DossierContratCommercial();
//		        contrat3.setIdDossier("DOSS_CC003");
//		        contrat3.setDateDebut(LocalDate.of(2024, 4, 1));
//		        contrat3.setDateExpiration(LocalDate.of(2024, 12, 1));
//		        contrat3.setDateCre(LocalDateTime.now());
//		        contrat3.setEtatDossier(EtatDoss.VALIDE);
//		        contrat3.setMontantContrat(120000.0);

		        DossierEmpreint empreint1 = new DossierEmpreint();
		        empreint1.setIdDossier("DOSS_EM001");
		        empreint1.setDateDebut(LocalDate.of(2025, 7, 1));
		        empreint1.setDateExpiration(LocalDate.of(2026, 1, 10));
		        empreint1.setDateCre(LocalDateTime.now());
		        empreint1.setEtatDossier(EtatDoss.VALIDE);
		        
		        TransfertPermanent tep1 = new TransfertPermanent();
		        tep1.setRefTransfert("TEM001");
		        tep1.setDossierDelegue(empreint1);
		        tep1.setCompteBancaire_source(compte2);
		        tep1.setCompteBancaire_cible(compte11);
		        tep1.setMontantTransfert(15000.0);
		        tep1.setTypeFrais(FraisType.OUR);
		        tep1.setFrais(0.0);
		        tep1.setMontantFrais(0.0);
		        tep1.setMontantFinal(129000.0);
		        tep1.setNatureOperation("prêt matériel");
		        tep1.setEtatTransfert(EtatTransfert.TRAITEMENT);
		        tep1.setDatecre(LocalDateTime.of(2025, 6, 15, 10, 0));
		        tep1.setDateEnvoie(null); // pas encore envoyé

		        // Deuxième transfert en traitement sans date d'envoi
		        TransfertPermanent tep2 = new TransfertPermanent();
		        tep2.setRefTransfert("TEM002");
		        tep2.setDossierDelegue(empreint1);
		        tep2.setCompteBancaire_source(compte2);
		        tep2.setCompteBancaire_cible(compte11);
		        tep2.setMontantTransfert(8000.0);
		        tep2.setTypeFrais(FraisType.BEN);
		        tep2.setFrais(50.0);
		        tep2.setMontantFrais(50.0);
		        tep2.setMontantFinal(68800.0);
		        tep2.setNatureOperation("frais dossier");
		        tep2.setEtatTransfert(EtatTransfert.TRAITEMENT);
		        tep2.setDatecre(LocalDateTime.of(2025, 6, 20, 15, 0));
		        tep2.setDateEnvoie(null); 

//		        DossierEmpreint empreint2 = new DossierEmpreint();
//		        empreint2.setIdDossier("DOSS_EM002");
//		        empreint2.setDateDebut(LocalDate.of(2022, 6, 1));
//		        empreint2.setDateExpiration(LocalDate.of(2025, 6, 1));
//		        empreint2.setDateCre(LocalDateTime.now());
//		        empreint2.setEtatDossier(EtatDoss.CLOTURE);
//		        empreint2.setMontantEmpreint(95000.0);
//		        empreint2.setMotifcloture("Remboursement complet");
//
//		        DossierEmpreint empreint3 = new DossierEmpreint();
//		        empreint3.setIdDossier("DOSS_EM003");
//		        empreint3.setDateDebut(LocalDate.of(2024, 3, 20));
//		        empreint3.setDateExpiration(LocalDate.of(2027, 3, 20));
//		        empreint3.setDateCre(LocalDateTime.now());
//		        empreint3.setEtatDossier(EtatDoss.TRAITEMENT);
//		        empreint3.setMontantEmpreint(220000.0);
                ///formationp
		        DossierFormationProfessionnelle dossierFP = new DossierFormationProfessionnelle();
		        dossierFP.setIdDossier("DOSS_FP001");
		        dossierFP.setDateDebut(LocalDate.of(2025, 9, 1));
		        dossierFP.setDateExpiration(LocalDate.of(2026, 6, 30));
		        dossierFP.setDateCre(LocalDateTime.of(2025, 5, 1, 10, 30));
		        dossierFP.setEtatDossier(EtatDoss.OUVERT);
		        dossierFP.setNomFormation("Formation DevOps");
		        
		        TransfertPermanent tfp1 = new TransfertPermanent();
		        tfp1.setRefTransfert("TFP001");
		        tfp1.setDossierDelegue(dossierFP);
		        tfp1.setCompteBancaire_source(compte2);  
		        tfp1.setCompteBancaire_cible(compte11);
		        tfp1.setMontantTransfert(8000.0);
		        tfp1.setTypeFrais(FraisType.OUR);
		        tfp1.setFrais(0.0);
		        tfp1.setMontantFrais(0.0);
		        tfp1.setMontantFinal(72000.0);
		        tfp1.setNatureOperation("financement formation");
		        tfp1.setEtatTransfert(EtatTransfert.ENVOYE);
		        tfp1.setDatecre(LocalDateTime.of(2025, 6, 10, 10, 0));
		        tfp1.setDateEnvoie(LocalDateTime.of(2025, 7, 10, 11, 0));

		        TransfertPermanent tfp2 = new TransfertPermanent();
		        tfp2.setRefTransfert("TFP002");
		        tfp2.setDossierDelegue(dossierFP);
		        tfp2.setCompteBancaire_source(compte2);
		        tfp2.setCompteBancaire_cible(compte11);
		        tfp2.setMontantTransfert(3000.0);
		        tfp2.setTypeFrais(FraisType.BEN);
		        tfp2.setFrais(50.0);
		        tfp2.setMontantFrais(50.0);
		        tfp2.setMontantFinal(27000.0);
		        tfp2.setNatureOperation("frais matériel pédagogique");
		        tfp2.setEtatTransfert(EtatTransfert.ENVOYE);
		        tfp2.setDatecre(LocalDateTime.of(2025, 6, 10, 10, 0));
		        tfp2.setDateEnvoie(LocalDateTime.of(2025, 7, 10, 11, 0));

		        TransfertPermanent tfp3 = new TransfertPermanent();
		        tfp3.setRefTransfert("TFP003");
		        tfp3.setDossierDelegue(dossierFP);
		        tfp3.setCompteBancaire_source(compte2);
		        tfp3.setCompteBancaire_cible(compte11);
		        tfp3.setMontantTransfert(2000.0);
		        tfp3.setTypeFrais(FraisType.BEN);
		        tfp3.setFrais(30.0);
		        tfp3.setMontantFrais(30.0);
		        tfp3.setMontantFinal(18000.0);
		        tfp3.setNatureOperation("logistique formation");
		        tfp3.setEtatTransfert(EtatTransfert.ENVOYE);
		        tfp3.setDatecre(LocalDateTime.of(2025, 6, 10, 10, 0));
		        tfp3.setDateEnvoie(LocalDateTime.of(2025, 7, 10, 11, 0));
		        
		        
                /// investissmenet
		        DossierInvestissement dossierInv = new DossierInvestissement();
		        dossierInv.setIdDossier("DOSS_INV001");
		        dossierInv.setDateDebut(LocalDate.of(2025, 9, 1));
		        dossierInv.setDateExpiration(LocalDate.of(2026, 6, 30));
		        dossierInv.setDateCre(LocalDateTime.of(2025, 5, 1, 10, 30));
		        dossierInv.setEtatDossier(EtatDoss.OUVERT);
		        dossierInv.setSecteurActivité("Agroalimentaire");
		        
		        TransfertPermanent tpm = new TransfertPermanent();
		        tpm.setRefTransfert("TINV001");
		        tpm.setDossierDelegue(dossierInv);
		        tpm.setCompteBancaire_source(compte10);
		        tpm.setCompteBancaire_cible(compte8);
		        tpm.setMontantTransfert(25000.0);
		        tpm.setTypeFrais(FraisType.OUR);
		        tpm.setFrais(0.0);
		        tpm.setMontantFrais(0.0);
		        tpm.setMontantFinal(214000.0);
		        tpm.setNatureOperation("investissement initial");
		        tpm.setEtatTransfert(EtatTransfert.ENVOYE);
		        tpm.setDatecre(LocalDateTime.of(2025, 6, 10, 10, 0));
		        tpm.setDateEnvoie(LocalDateTime.of(2025, 7, 10, 11, 0));

		        TransfertPermanent tpm2 = new TransfertPermanent();
		        tpm2.setRefTransfert("TINV002");
		        tpm2.setDossierDelegue(dossierInv);
		        tpm2.setCompteBancaire_source(compte10);
		        tpm2.setCompteBancaire_cible(compte8);
		        tpm2.setMontantTransfert(15000.0);
		        tpm2.setTypeFrais(FraisType.BEN);
		        tpm2.setFrais(200.0);
		        tpm2.setMontantFrais(200.0);
		        tpm2.setMontantFinal(129000.0);
		        tpm2.setNatureOperation("investissement complémentaire");
		        tpm2.setEtatTransfert(EtatTransfert.ENVOYE);
		        tpm2.setDatecre(LocalDateTime.of(2025, 7, 20, 9, 30));
		        tpm2.setDateEnvoie(LocalDateTime.of(2025, 8, 15, 10, 0));

		        TransfertPermanent tpm3 = new TransfertPermanent();
		        tpm3.setRefTransfert("TINV003");
		        tpm3.setDossierDelegue(dossierInv);
		        tpm3.setCompteBancaire_source(compte10);
		        tpm3.setCompteBancaire_cible(compte8);
		        tpm3.setMontantTransfert(10000.0);
		        tpm3.setTypeFrais(FraisType.BEN);
		        tpm3.setFrais(150.0);
		        tpm3.setMontantFrais(150.0);
		        tpm3.setMontantFinal(86000.0);
		        tpm3.setNatureOperation("frais divers");
		        tpm3.setEtatTransfert(EtatTransfert.ENVOYE);
		        tpm3.setDatecre(LocalDateTime.of(2025, 8, 5, 14, 0));
		        tpm3.setDateEnvoie(LocalDateTime.of(2025, 9, 1, 14, 0));
		        
		        
                ///scolarite 
		        DossierScolarité dossierScol = new DossierScolarité();
		        dossierScol.setIdDossier("DOSS_SC001");
		        dossierScol.setDateDebut(LocalDate.of(2025, 9, 1));
		        dossierScol.setDateExpiration(LocalDate.of(2026, 6, 30));
		        dossierScol.setDateCre(LocalDateTime.of(2025, 5, 1, 10, 30));
		        dossierScol.setEtatDossier(EtatDoss.OUVERT);
		        dossierScol.setNiveauEtude("Master Informatique");
		        
		        TransfertPermanent tp= new TransfertPermanent();
		        tp.setRefTransfert("TSC001");
		        tp.setDossierDelegue(dossierScol);
		        tp.setCompteBancaire_source(compte2);
		        tp.setCompteBancaire_cible(compte1);
		        tp.setMontantTransfert(9000.0);
		        tp.setTypeFrais(FraisType.OUR);
		        tp.setFrais(0.0);
		        tp.setMontantFrais(0.0);
		        tp.setMontantFinal(77337.0);
		        tp.setNatureOperation("frais d'inscription");
		        tp.setEtatTransfert(EtatTransfert.ENVOYE);
		        tp.setDatecre(LocalDateTime.of(2025, 5, 1, 10, 30));
		        tp.setDateEnvoie(LocalDateTime.of(2025, 7, 1, 10, 30));
		        
		        TransfertPermanent tp2= new TransfertPermanent();
		        tp2.setRefTransfert("TSC002");
		        tp2.setDossierDelegue(dossierScol);
		        tp2.setCompteBancaire_source(compte2);
		        tp2.setCompteBancaire_cible(compte1);
		        tp2.setMontantTransfert(2500.0);
		        tp2.setTypeFrais(FraisType.BEN);
		        tp2.setFrais(50.0);
		        tp2.setMontantFrais(50.0);
		        tp2.setMontantFinal(21432.5);
		        tp2.setNatureOperation("hebergement");
		        tp2.setEtatTransfert(EtatTransfert.ENVOYE);
		        tp2.setDatecre(LocalDateTime.of(2025, 8, 1, 10, 30));
		        tp2.setDateEnvoie(LocalDateTime.of(2025, 9, 1, 10, 30));
		        
		        TransfertPermanent tp3= new TransfertPermanent();
		        tp3.setRefTransfert("TSC003");
		        tp3.setDossierDelegue(dossierScol);
		        tp3.setCompteBancaire_source(compte2);
		        tp3.setCompteBancaire_cible(compte1);
		        tp3.setMontantTransfert(2500.0);
		        tp3.setTypeFrais(FraisType.BEN);
		        tp3.setFrais(50.0);
		        tp3.setMontantFrais(50.0);
		        tp3.setMontantFinal(21432.5);
		        tp3.setNatureOperation("materielle scolaire");
		        tp3.setEtatTransfert(EtatTransfert.ENVOYE);
		        tp3.setDatecre(LocalDateTime.of(2025, 9, 1, 10, 30));
		        tp3.setDateEnvoie(LocalDateTime.of(2025, 9, 15, 10, 30));
		        
		        
                ////soins
		        DossierSoinMedical dossierSoin = new DossierSoinMedical();
		        dossierSoin.setIdDossier("DOSS_SM001");
		        dossierSoin.setDateDebut(LocalDate.of(2025, 9, 1));
		        dossierSoin.setDateExpiration(LocalDate.of(2026, 6, 30));
		        dossierSoin.setDateCre(LocalDateTime.of(2025, 5, 1, 10, 30));
		        dossierSoin.setEtatDossier(EtatDoss.OUVERT);
		        dossierSoin.setTypeTraitement("Chirurgie cardiaque");

		        TransfertPermanent tps = new TransfertPermanent();
		        tps.setRefTransfert("TSM001");
		        tps.setDossierDelegue(dossierSoin);
		        tps.setCompteBancaire_source(compte2); // compte tunisien
		        tps.setCompteBancaire_cible(compte11);
		        tps.setMontantTransfert(12000.0);
		        tps.setTypeFrais(FraisType.OUR);
		        tps.setFrais(0.0);
		        tps.setMontantFrais(0.0);
		        tps.setMontantFinal(103200.0);
		        tps.setNatureOperation("soins médicaux");
		        tps.setEtatTransfert(EtatTransfert.ENVOYE);
		        tps.setDatecre(LocalDateTime.of(2025, 6, 20, 9, 0));
		        tps.setDateEnvoie(LocalDateTime.of(2025, 7, 5, 10, 0));

		        TransfertPermanent tps2 = new TransfertPermanent();
		        tps2.setRefTransfert("TSM002");
		        tps2.setDossierDelegue(dossierSoin);
		        tps2.setCompteBancaire_source(compte2);
		        tps2.setCompteBancaire_cible(compte11);
		        tps2.setMontantTransfert(5000.0);
		        tps2.setTypeFrais(FraisType.BEN);
		        tps2.setFrais(100.0);
		        tps2.setMontantFrais(100.0);
		        tps2.setMontantFinal(43000.0);
		        tps2.setNatureOperation("hospitalisation");
		        tps2.setEtatTransfert(EtatTransfert.ENVOYE);
		        tps2.setDatecre(LocalDateTime.of(2025, 7, 15, 11, 30));
		        tps2.setDateEnvoie(LocalDateTime.of(2025, 8, 1, 9, 0));

		        TransfertPermanent tps3 = new TransfertPermanent();
		        tps3.setRefTransfert("TSM003");
		        tps3.setDossierDelegue(dossierSoin);
		        tps3.setCompteBancaire_source(compte2);
		        tps3.setCompteBancaire_cible(compte11);
		        tp3.setMontantTransfert(3000.0);
		        tp3.setTypeFrais(FraisType.BEN);
		        tp3.setFrais(50.0);
		        tp3.setMontantFrais(50.0);
		        tp3.setMontantFinal(25800.0);
		        tp3.setNatureOperation("frais de consultation");
		        tp3.setEtatTransfert(EtatTransfert.ENVOYE);
		        tp3.setDatecre(LocalDateTime.of(2025, 8, 10, 14, 0));
		        tp3.setDateEnvoie(LocalDateTime.of(2025, 9, 1, 15, 0));


		        
		      
		        // Sauvegarde des dossiers
		       dossierDelegueRepository.saveAll(Arrays.asList(doss1, contrat1, empreint1, dossierFP, dossierInv, dossierScol, dossierSoin));
		       transfertpRepop.saveAll(Arrays.asList(tps,tps2,tps3,tp,tp2,tp3,tpm,tpm2,tpm3,tfp2,tfp1,tfp3,tep1,tep2,tes1,tes2,tcc2,tcc1));
		       

		        
		        
	    };
	}

    }
