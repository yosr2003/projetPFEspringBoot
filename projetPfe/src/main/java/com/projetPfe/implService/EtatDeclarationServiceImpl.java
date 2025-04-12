package com.projetPfe.implService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import com.openpdf.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.projetPfe.repositories.EtatDeclarationRepository;

@Service
public class EtatDeclarationServiceImpl implements IEtatDeclarationService{
	@Autowired
	private EtatDeclarationRepository etatDecRepo;
	
	
	@Override
    public EtatDeclarationBCT genererContenuXml() {
		 EtatDeclarationBCT etat= new EtatDeclarationBCT();
		 StringBuilder xml = new StringBuilder();
	        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	        xml.append("<etatDeclaration>\n");
	        xml.append("<ligne>\n");

//	        // Identification de l'investisseur
//	        xml.append("  <identificationInvestisseur>\n");
//	        xml.append("    <codeDouane>").append("1553301R").append("</codeDouane>\n");
//	        xml.append("    <raisonSociale>").append("TEST COMPANY").append("</raisonSociale>\n");
//	        xml.append("    <adresse>").append("24 bis rue d'égalité, Sidi Amor, Manouba, Tunisie").append("</adresse>\n");
//	        xml.append("  </identificationInvestisseur>\n");
//
//	        // Chiffre d'affaires exportateurs
//	        xml.append("  <chiffreAffairesExportateurs>")
//	           .append("0.00")
//	           .append("</chiffreAffairesExportateurs>\n");
//
//	        // Chiffre d'affaires autres entreprises
//	        xml.append("  <chiffreAffairesAutresEntreprises>")
//	           .append("300000.00")
//	           .append("</chiffreAffairesAutresEntreprises>\n");
//
//	        // Montant transféré
//	        xml.append("  <montantTransfere>\n");
//	        xml.append("    <montantDevise>").append("300000.00").append("</montantDevise>\n");
//	        xml.append("    <contreValeurDinar>").append("300000.00").append("</contreValeurDinar>\n");
//	        xml.append("  </montantTransfere>\n");
//
//	        // Renseignements investissement à l'étranger
//	        xml.append("  <investissementEtranger>\n");
//	        xml.append("    <forme>").append("Succursales, filiales ou prises de participation").append("</forme>\n");
//	        xml.append("    <raisonSociale>").append("TEST").append("</raisonSociale>\n");
//	        xml.append("    <lieuImplantation>\n");
//	        xml.append("      <codePays>").append("FR").append("</codePays>\n");
//	        xml.append("      <adresse>").append("30450 rue des joujou, Montpellier, 2054").append("</adresse>\n");
//	        xml.append("    </lieuImplantation>\n");
//	        xml.append("  </investissementEtranger>\n");
	        
	        // Identification de l'investisseur
	        
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


	
	private String escapeXml(String input) {
		if (input == null) return "";
        return input
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&apos;");
	}
	
	@Override
    public byte[] genererEtatDeclaration(int etatId) {
//        Optional<EtatDeclarationBCT> etat = etatDecRepo.findById(etatId);
//        if(etat.isPresent()) {
//            String contenuXml = etat.get().getContenuTexte();
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            Document document = new Document();
//            PdfPTable table = new PdfPTable(5);
//
//            try {
//				PdfWriter.getInstance(document, outputStream);
//			} catch (DocumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            document.open();
//
//            // Convertir chaque ligne du XML en paragraphe lisible
//            for (String ligne : contenuXml.split("\n")) {
//                try {
//					document.add(new Paragraph(ligne.trim()));
//				} catch (DocumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//
//            document.close();
//            return outputStream.toByteArray();
//     }
     return null;
    }
	@Override
	public byte[] genererPdfDepuisXml(int etatId){
		 Optional<EtatDeclarationBCT> etat = etatDecRepo.findById(etatId);
       if(etat.isPresent()) {
           String contenuXml = etat.get().getContenuTexte();
	        // Parser le XML
	        Document xmlDoc = null;
			try {
				xmlDoc = DocumentBuilderFactory.newInstance()
				        .newDocumentBuilder()
				        .parse(new ByteArrayInputStream(contenuXml.getBytes()));
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        Element root = xmlDoc.getDocumentElement();

	        // Extraction des champs
	        String codeDouane = getTextContent(root, "codeDouane");
	        String raisonSociale = getTextContent(root, "raisonSociale");
	        String adresse = getTextContent(root, "identificationInvestisseur/adresse");

	        String contreValeurExport = getTextContent(root, "contreValeurDinarExportateurs");
	        String chiffreAffaireAutres = getTextContent(root, "chiffreAffaireDinarAutres");

	        String montantDevise = getTextContent(root, "montantTransfere/montantDevise");
	        String montantDinar = getTextContent(root, "montantTransfere/contreValeurDinar");

	        String forme = getTextContent(root, "forme");
	        String raisonSocialeEtranger = getTextContent(root, "investissementEtranger/raisonSociale");
	        String codePays = getTextContent(root, "codePays");
	        String adresseImplantation = getTextContent(root, "investissementEtranger/lieuImplantation/adresse");

	        // Création PDF
	        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        try {
				PdfWriter.getInstance(document, outputStream);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        document.open();

	        PdfPTable table = new PdfPTable(5); // 5 colonnes

	        // Titres des colonnes
	        table.addCell("Identification de l’investisseur");
	        table.addCell("C.V. Dinar - Exportateurs");
	        table.addCell("C.A. Dinar - Autres");
	        table.addCell("Montant transféré");
	        table.addCell("Investissement à l’étranger");

	        // Contenu d'une ligne
	        table.addCell(codeDouane + "\n" + raisonSociale + "\n" + adresse);
	        table.addCell(contreValeurExport);
	        table.addCell(chiffreAffaireAutres);
	        table.addCell(montantDevise + "\n" + montantDinar);
	        table.addCell(forme + "\n" + raisonSocialeEtranger + "\n" + codePays + "\n" + adresseImplantation);

	        try {
				document.add(new Paragraph("État des transferts pour investissements à l'étranger", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
				document.add(new Paragraph("\n"));
		        document.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        document.close();

	        return outputStream.toByteArray();}
       return null;
	    }
	
	private String getTextContent(Element root, String path) {
        try {
            String[] parts = path.split("/");
            Element current = root;
            for (String part : parts) {
                current = (Element) current.getElementsByTagName(part).item(0);
            }
            return current.getTextContent();
        } catch (Exception e) {
            return "";
        }
    }



	@Override
	public byte[] test(int etatId) throws Exception {
		Optional<EtatDeclarationBCT> etat = etatDecRepo.findById(etatId);
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

        pdfDoc.add(new Paragraph("ETAT TRIMESTRIEL DES TRANSFERTS FFECTUES A TITRE D’INVESTISSEMENT A L’ETRANGER"));
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
	
	// Method to add header row to the table
	private void addHeaderRow(PdfPTable table) {
        // Ligne 1 (en-têtes générales avec fusion de colonnes)
        PdfPCell cell1 = new PdfPCell(new Phrase("Colonne1"));
        cell1.setColspan(3);
        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase("Colonne2"));
        cell2.setRowspan(3);
        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Phrase("Colonne3"));
        cell3.setRowspan(3);
        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell(new Phrase("Colonne4"));
        cell4.setColspan(2);
        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell4);

        PdfPCell cell5 = new PdfPCell(new Phrase("Colonne5"));
        cell5.setColspan(4);
        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(cell5);
        //ligne 2
        PdfPCell sc11 = new PdfPCell(new Phrase("SC1.1"));
        sc11.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc11);
        PdfPCell sc12 = new PdfPCell(new Phrase("SC1.2"));
        sc12.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc12);
        PdfPCell sc13 = new PdfPCell(new Phrase("SC1.3"));
        sc13.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc13);

        PdfPCell sc41 = new PdfPCell(new Phrase("SC4.1"));
        sc41.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc41);
        PdfPCell sc42 = new PdfPCell(new Phrase("SC4.2"));
        sc42.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc42);
        
        PdfPCell sc51 = new PdfPCell(new Phrase("SC5.1"));
        sc51.setRowspan(2); // étend sur ligne 2 et 3
        table.addCell(sc51);

        PdfPCell sc52 = new PdfPCell(new Phrase("SC5.2"));
        sc52.setRowspan(2);
        table.addCell(sc52);
        PdfPCell sc53 = new PdfPCell(new Phrase("SC5.3"));
        sc53.setColspan(2); // va être divisé en 2 colonnes ligne 3
        sc53.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(sc53);
        // Ligne 3
    
        table.addCell("5.3.1");
        table.addCell("5.3.1");
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


	

	  
}
