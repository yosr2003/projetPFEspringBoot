package com.projetPfe.implService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import com.openpdf.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

//import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetPfe.Iservice.IEtatDeclarationService;
import com.projetPfe.entities.EtatDeclarationBCT;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.entities.Participant;
import com.projetPfe.entities.PersonneMorale;
import com.projetPfe.entities.PersonnePhysique;
import com.projetPfe.entities.Transfert;
import com.projetPfe.repositories.EtatDeclarationRepository;
import com.projetPfe.repositories.TransfertRepository;

@Service
public class EtatDeclarationServiceImpl implements IEtatDeclarationService{
	@Autowired
	private EtatDeclarationRepository etatDecRepo;
	@Autowired
	private TransfertRepository transfertRepo;
	
	private StringBuilder genererContenuXml2(String trimestre,List<Transfert> transferts) {
		 
		if (!transferts.isEmpty()) {

		   // EtatDeclarationBCT etat = new EtatDeclarationBCT();
		    StringBuilder xml = new StringBuilder();
		    xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		    xml.append("<etatDeclaration>\n");

		    for (Transfert transfert : transferts) {
		        xml.append("<ligne>\n");

		        Participant emmeteur = transfert.getCompteBancaire_source().getParticipant();
		        if (emmeteur instanceof PersonneMorale) {
		            xml.append("    <colonne1>").append(((PersonneMorale) emmeteur).getCodeDouane()).append("</colonne1>\n");
		            xml.append("    <colonne2>").append(((PersonneMorale) emmeteur).getRaisonSociale()).append("</colonne2>\n");
		        } else if(emmeteur instanceof PersonnePhysique){
		            xml.append("    <colonne1>").append(((PersonnePhysique) emmeteur).getCin()).append("</colonne1>\n");
		            xml.append("    <colonne2>").append(((PersonnePhysique) emmeteur).getNom()).append("</colonne2>\n");
		        }

	            xml.append("    <colonne3>").append(emmeteur.getAdresse()).append("</colonne3>\n");

		        xml.append("    <colonne6>").append(transfert.getMontantFinal()).append("</colonne6>\n");
		        xml.append("    <colonne7>").append(transfert.getMontantTransfert()).append("</colonne7>\n");
		        

	            xml.append("    <colonne8>").append(transfert.getNatureJuridique()).append("</colonne8>\n");

		        Participant beneficiaire = transfert.getCompteBancaire_cible().getParticipant();
		        if (beneficiaire instanceof PersonneMorale) {
		            xml.append("    <colonne9>").append(((PersonneMorale) beneficiaire).getRaisonSociale()).append("</colonne9>\n");
		        } else if(beneficiaire instanceof PersonnePhysique){
		            xml.append("    <colonne9>").append(((PersonnePhysique) beneficiaire).getNom()).append("</colonne9>\n");
		        }

	            xml.append("    <colonne10>").append(transfert.getCompteBancaire_cible().getCodePays()).append("</colonne10>\n");
	            xml.append("    <colonne11>").append(beneficiaire.getAdresse()).append("</colonne11>\n");
		        xml.append("</ligne>\n");
		    }

		    xml.append("</etatDeclaration>");

		   // etat.setContenuTexte(xml.toString());
		    //etatDecRepo.save(etat);

		    return xml;}
		return null;
	}
	@Override
    public EtatDeclarationBCT genererContenuXml() {
		EtatDeclarationBCT etat = new EtatDeclarationBCT();
		 StringBuilder xml = new StringBuilder();
	        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	        xml.append("<etatDeclaration>\n");
	        xml.append("<ligne>\n");
	  
	        xml.append("    <colonne1>").append("").append("</colonne1>\n");
	        xml.append("    <colonne2>").append("").append("</colonne2>\n");
	        xml.append("    <colonne3>").append("").append("</colonne3>\n");
	        
	        
	        // Chiffre d'affaires exportateurs
	        xml.append("  <colonne4>")
	           .append("0.00")
	           .append("</colonne4>\n");

	        // Chiffre d'affaires autres entreprises
	        xml.append("  <colonne5>")
	           .append("300000.00")
	           .append("</colonne5>\n");

	        // Montant transféré
	        
	        xml.append("    <colonne6>").append("").append("</colonne6>\n");
	        xml.append("    <colonne7>").append("").append("</colonne7>\n");
	       

	        
	        
	        xml.append("    <colonne8>").append("").append("</colonne8>\n");
	        xml.append("    <colonne9>").append("").append("</colonne9>\n");
	        
	        xml.append("      <colonne10>").append("").append("</colonne10>\n");
	        xml.append("      <colonne11>").append("").append("</colonne11>\n");
	        
	        
	        xml.append("</ligne>");
	        xml.append("<ligne>");
	        xml.append("    <colonne1>").append("1553301R").append("</colonne1>\n");
	        xml.append("    <colonne2>").append("TEST COMPANY").append("</colonne2>\n");
	        xml.append("    <colonne3>").append("24 bis rue d'égalité, Sidi Amor, Manouba, Tunisie").append("</colonne3>\n");
	        

	        // Chiffre d'affaires exportateurs
	        xml.append("  <colonne4>")
	           .append("0.00")
	           .append("</colonne4>\n");

	        // Chiffre d'affaires autres entreprises
	        xml.append("  <colonne5>")
	           .append("300000.00")
	           .append("</colonne5>\n");

	        // Montant transféré
	        
	        xml.append("    <colonne6>").append("300000.00").append("</colonne6>\n");
	        xml.append("    <colonne7>").append("300000.00").append("</colonne7>\n");
	       

	        // Renseignements investissement à l'étranger
	        
	        xml.append("    <colonne8>").append("Succursales, filiales ou prises de participation").append("</colonne8>\n");
	        xml.append("    <colonne9>").append("TEST").append("</colonne9>\n");
	        
	        xml.append("      <colonne10>").append("FR").append("</colonne10>\n");
	        xml.append("      <colonne11>").append("30450 rue des joujou, Montpellier, 2054").append("</colonne11>\n");
	        
	        
	        xml.append("</ligne>");
	        xml.append("</etatDeclaration>");

	        // Stocker dans contenuTexte
	        etat.setContenuTexte(xml.toString());
	        etatDecRepo.save(etat);
	        return etat;
	        }


	


	@Override
	public ResponseEntity<?> test(String trimestre,String typeDeclaration) throws Exception {
		List<EtatDeclarationBCT> decalarations=etatDecRepo.findAll();
		EtatDeclarationBCT etat = new EtatDeclarationBCT(trimestre,typeDeclaration,LocalDate.now().getYear());
		if(decalarations.contains(etat)) {return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("il y'a deja un etat de declaration pour ce type de transferts ce trimestre");}
		List<Transfert> transferts = filtreTransfertsParTrimestre(trimestre,typeDeclaration);
		StringBuilder xml = genererContenuXml2(trimestre,transferts);
	       if(xml!=null) {
	           //String contenuXml = etat.getContenuTexte();
	           String contenuXml = xml.toString();

        org.w3c.dom.Document xmlDoc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new ByteArrayInputStream(contenuXml.getBytes()));

        // Générer PDF en mémoire
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        com.itextpdf.text.Document pdfDoc = new com.itextpdf.text.Document();
        PdfWriter.getInstance(pdfDoc, outputStream);
        pdfDoc.open();

        pdfDoc.add(new Paragraph("ETAT DE DECLARATION TRIMESTRIEL DES TRANSFERTS DE TYPE "+typeDeclaration));
        pdfDoc.add(Chunk.NEWLINE);
      

        PdfPTable table = new PdfPTable(9); // 3 + 1 + 1 + 2 + 4 = 11 colonnes
        table.setWidthPercentage(100);
        boolean Personnemorale = transferts.stream()
        	    .map(t -> t.getCompteBancaire_source().getParticipant())
        	    .allMatch(p -> p instanceof PersonneMorale);
        addHeaderRow2(table,Personnemorale);

        for (int i = 0; i < xmlDoc.getElementsByTagName("ligne").getLength(); i++) {
            Node ligne = xmlDoc.getElementsByTagName("ligne").item(i);
            if (ligne.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) ligne;
                addDataRow2(table, elem);
            }
        
        }
        pdfDoc.add(table);
        pdfDoc.close();
        etat.setContenuPdf(outputStream.toByteArray());
        etat.setTransferts(transferts);
        etatDecRepo.save(etat);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=etat_investissement.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(outputStream.toByteArray());}return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("il n'ya pas de transferts a mettre dans le rapport");
    }
	// Method to add header row to the table
		private void addHeaderRow2(PdfPTable table,boolean isPersonnemorale) {
			Font smallFont = new Font(Font.FontFamily.HELVETICA, 10);
	        // Ligne 1 (en-têtes générales avec fusion de colonnes)
	        PdfPCell cell1 = new PdfPCell(new Phrase("Identification de l’émmeteur"));
	        cell1.setColspan(3);
	        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
	        table.addCell(cell1);

	        PdfPCell cell4 = new PdfPCell(new Phrase("Montant transféré"));
	        cell4.setColspan(2);
	        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
	        table.addCell(cell4);

	        PdfPCell cell5 = new PdfPCell(new Phrase("Renseignement concernant le transfert à l’étranger"));
	        cell5.setColspan(4);
	        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
	        table.addCell(cell5);
	        //ligne 2
	        PdfPCell sc11 = new PdfPCell(new Phrase(isPersonnemorale ? "Code douane" : "CIN",smallFont));
	        sc11.setRowspan(2); // étend sur ligne 2 et 3
	        table.addCell(sc11);
	        PdfPCell sc12 = new PdfPCell(new Phrase(isPersonnemorale ? "Raison Sociale" : "Nom",smallFont));
	        sc12.setRowspan(2); // étend sur ligne 2 et 3
	        table.addCell(sc12);
	        PdfPCell sc13 = new PdfPCell(new Phrase("Adresse",smallFont));
	        sc13.setRowspan(2); // étend sur ligne 2 et 3
	        table.addCell(sc13);

	        PdfPCell sc41 = new PdfPCell(new Phrase("Montant en devises",smallFont));
	        sc41.setRowspan(2); // étend sur ligne 2 et 3
	        table.addCell(sc41);
	        PdfPCell sc42 = new PdfPCell(new Phrase("Contre-valeur en dinar",smallFont));
	        sc42.setRowspan(2); // étend sur ligne 2 et 3
	        table.addCell(sc42);
	        
	        PdfPCell sc51 = new PdfPCell(new Phrase("Forme",smallFont));
	        sc51.setRowspan(2); 
	        table.addCell(sc51);

	        PdfPCell sc52 = new PdfPCell(new Phrase(isPersonnemorale ? "Raison Sociale" : "Nom",smallFont));
	        sc52.setRowspan(2);
	        table.addCell(sc52);
	        PdfPCell sc53 = new PdfPCell(new Phrase("Lieu d’implantation"));
	        sc53.setColspan(2); // va être divisé en 2 colonnes ligne 3
	        table.addCell(sc53);
	        // Ligne 3
	        PdfPCell cc1 = new PdfPCell(new Phrase("Code Pays", smallFont));
	        table.addCell(cc1);

	        PdfPCell cc2 = new PdfPCell(new Phrase("Adresse", smallFont));
	        table.addCell(cc2);
	        
		}
	// Method to add header row to the table
	private void addHeaderRow(PdfPTable table) {
		Font smallestFont = new Font(Font.FontFamily.HELVETICA, 8);
		Font smallFont = new Font(Font.FontFamily.HELVETICA, 10);
        // Ligne 1 (en-têtes générales avec fusion de colonnes)
        PdfPCell cell1 = new PdfPCell(new Phrase("Identification de l’investisseur"));
        cell1.setColspan(3);
        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase("Contre-valeur en dinar du chiffre d’affaires en devises de l’exercice précédent pour les entreprises exportatrices",smallestFont));
        cell2.setRowspan(3);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Phrase("Chiffre d’affaires en dinars de l’exercice précédent pour lesautres entreprises",smallestFont));
        cell3.setRowspan(3);
        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell(new Phrase("Montant transféré"));
        cell4.setColspan(2);
        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell4);

        PdfPCell cell5 = new PdfPCell(new Phrase("Renseignement concernant l’investissement à l’étranger"));
        cell5.setColspan(4);
        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell5);
        //ligne 2
        PdfPCell sc11 = new PdfPCell(new Phrase("Code en Douane",smallFont));
        sc11.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc11);
        PdfPCell sc12 = new PdfPCell(new Phrase("Raison Sociale",smallFont));
        sc12.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc12);
        PdfPCell sc13 = new PdfPCell(new Phrase("Adresse",smallFont));
        sc13.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc13);

        PdfPCell sc41 = new PdfPCell(new Phrase("Montant en devises",smallFont));
        sc41.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc41);
        PdfPCell sc42 = new PdfPCell(new Phrase("Contre-valeur en dinar",smallFont));
        sc42.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc42);
        
        PdfPCell sc51 = new PdfPCell(new Phrase("Forme",smallFont));
        sc51.setRowspan(2); 
        table.addCell(sc51);

        PdfPCell sc52 = new PdfPCell(new Phrase("Raison Sociale",smallFont));
        sc52.setRowspan(2);
        table.addCell(sc52);
        PdfPCell sc53 = new PdfPCell(new Phrase("Lieu d’implantation"));
        sc53.setColspan(2); // va être divisé en 2 colonnes ligne 3
        table.addCell(sc53);
        // Ligne 3
        PdfPCell cc1 = new PdfPCell(new Phrase("Code Pays", smallFont));
        table.addCell(cc1);

        PdfPCell cc2 = new PdfPCell(new Phrase("Adresse", smallFont));
        table.addCell(cc2);
        
	}
	private void addDataRow2(PdfPTable table, Element elem) {
		table.addCell(elem.getElementsByTagName("colonne1").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne2").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne3").item(0).getTextContent());
       
        
        table.addCell(elem.getElementsByTagName("colonne6").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne7").item(0).getTextContent());
       

        table.addCell(elem.getElementsByTagName("colonne8").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne9").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne10").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne11").item(0).getTextContent());
	}
	
	private void addDataRow(PdfPTable table, Element elem) {
		table.addCell(elem.getElementsByTagName("colonne1").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne2").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne3").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne4").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne5").item(0).getTextContent());
        
        table.addCell(elem.getElementsByTagName("colonne6").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne7").item(0).getTextContent());
       

        table.addCell(elem.getElementsByTagName("colonne8").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne9").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne10").item(0).getTextContent());
        table.addCell(elem.getElementsByTagName("colonne11").item(0).getTextContent());
	}

	private List<Transfert> filtreTransfertsParTrimestre(String trimestre,String typeDeclaration) {
	    LocalDate startDate, endDate;
	    int currentYear = LocalDate.now().getYear();

	    switch (trimestre.toUpperCase()) {
	        case "T1":
	            startDate = LocalDate.of(currentYear, 1, 1);
	            endDate = LocalDate.of(currentYear, 3, 31);
	            break;
	        case "T2":
	            startDate = LocalDate.of(currentYear, 4, 1);
	            endDate = LocalDate.of(currentYear, 6, 30);
	            break;
	        case "T3":
	            startDate = LocalDate.of(currentYear, 7, 1);
	            endDate = LocalDate.of(currentYear, 9, 30);
	            break;
	        case "T4":
	            startDate = LocalDate.of(currentYear, 10, 1);
	            endDate = LocalDate.of(currentYear, 12, 31);
	            break;
	        default:
	            throw new IllegalArgumentException("Trimestre invalide: " + trimestre);
	    }

	    return transfertRepo.findAll().stream()
	        .filter(t -> {
	        	if(t.getDateEnvoie()!=null) {
	            LocalDate dateEnvoie = t.getDateEnvoie().toLocalDate();
	            return !dateEnvoie.isBefore(startDate) && !dateEnvoie.isAfter(endDate);} return false;
	        })
	        //.filter(t -> t.getEtat() == EtatDoss.ENOVYE)
	        .filter(t -> {
	            if (t.getDossierDelegue() != null) {
	                return typeDeclaration.equals(t.getDossierDelegue().getType().toString());
	               
	            } else {
	                return typeDeclaration.equals(t.getTypeTransfert().toString());
	            }
	        })
	        .collect(Collectors.toList());
	}
	@Override
	public byte[] genererEtatDeclaration(String typeDeclaration, String trimestre,int id) throws Exception {
		Optional<EtatDeclarationBCT> etat = etatDecRepo.findById(id);
	       if(etat.isPresent()) {
	           String contenuXml = etat.get().getContenuTexte();

     org.w3c.dom.Document xmlDoc = DocumentBuilderFactory.newInstance()
             .newDocumentBuilder()
             .parse(new ByteArrayInputStream(contenuXml.getBytes()));

     // Générer PDF en mémoire
     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
     com.itextpdf.text.Document pdfDoc = new com.itextpdf.text.Document();
     PdfWriter.getInstance(pdfDoc, outputStream);
     pdfDoc.open();

     pdfDoc.add(new Paragraph("ETAT TRIMESTRIEL DES TRANSFERTS EFFECTUES A TITRE D’INVESTISSEMENT A L’ETRANGER"));
     pdfDoc.add(Chunk.NEWLINE);
   

     PdfPTable table = new PdfPTable(11); // 3 + 1 + 1 + 2 + 4 = 11 colonnes
     table.setWidthPercentage(100);
     addHeaderRow(table);

     for (int i = 0; i < xmlDoc.getElementsByTagName("ligne").getLength(); i++) {
         Node ligne = xmlDoc.getElementsByTagName("ligne").item(i);
         if (ligne.getNodeType() == Node.ELEMENT_NODE) {
             Element elem = (Element) ligne;
             addDataRow(table, elem);
         }
     
     }
     pdfDoc.add(table);
     pdfDoc.close();
	       
     return outputStream.toByteArray();}return null;
	}


	

	  
}
