package com.projetPfe.implService;

import java.util.Optional;
//import com.openpdf.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	        xml.append("<etatDeclarationInvestissement>\n");

	        // Identification de l'investisseur
	        xml.append("  <identificationInvestisseur>\n");
	        xml.append("    <codeDouane>").append("1553301R").append("</codeDouane>\n");
	        xml.append("    <raisonSociale>").append("TEST COMPANY").append("</raisonSociale>\n");
	        xml.append("    <adresse>").append("24 bis rue d'égalité, Sidi Amor, Manouba, Tunisie").append("</adresse>\n");
	        xml.append("  </identificationInvestisseur>\n");

	        // Chiffre d'affaires exportateurs
	        xml.append("  <chiffreAffairesExportateurs>")
	           .append("0.00")
	           .append("</chiffreAffairesExportateurs>\n");

	        // Chiffre d'affaires autres entreprises
	        xml.append("  <chiffreAffairesAutresEntreprises>")
	           .append("300000.00")
	           .append("</chiffreAffairesAutresEntreprises>\n");

	        // Montant transféré
	        xml.append("  <montantTransfere>\n");
	        xml.append("    <montantDevise>").append("300000.00").append("</montantDevise>\n");
	        xml.append("    <contreValeurDinar>").append("300000.00").append("</contreValeurDinar>\n");
	        xml.append("  </montantTransfere>\n");

	        // Renseignements investissement à l'étranger
	        xml.append("  <investissementEtranger>\n");
	        xml.append("    <forme>").append("Succursales, filiales ou prises de participation").append("</forme>\n");
	        xml.append("    <raisonSociale>").append("TEST").append("</raisonSociale>\n");
	        xml.append("    <lieuImplantation>\n");
	        xml.append("      <codePays>").append("FR").append("</codePays>\n");
	        xml.append("      <adresse>").append("30450 rue des joujou, Montpellier, 2054").append("</adresse>\n");
	        xml.append("    </lieuImplantation>\n");
	        xml.append("  </investissementEtranger>\n");

	        xml.append("</etatDeclarationInvestissement>");

	        // Stocker dans contenuTexte
	        etat.setContenuTexte(xml.toString());
	        etatDecRepo.save(etat);
	        return etat;
	        }


	@Override
	public String escapeXml(String input) {
		if (input == null) return "";
        return input
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&apos;");
	}
	
	@Override
    public byte[] genererPdf(int etatId) {
        Optional<EtatDeclarationBCT> etat = etatDecRepo.findById(etatId);
                //.orElseThrow(() -> new RuntimeException("État non trouvé"));
        if(etat.isPresent()) {
            String contenuXml = etat.get().getContenuTexte();

            // Créer un PDF en mémoire
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            PdfWriter writer = new PdfWriter(outputStream);
//            PdfDocument pdf = new PdfDocument(writer);
//            Document document = new Document(pdf);
//
//            // Titre du document
//            document.add(new Paragraph("État de Déclaration d'Investissement").setBold().setFontSize(18));
//
//            // Créer un tableau
//            PdfPTable table = new PdfPTable(2); // 2 colonnes
//
//            // Ajouter les en-têtes de colonnes
//            table.addCell("Données");
//            table.addCell("Valeurs");
//
//            // Extraire et ajouter des données depuis le contenu XML
//            // Note : Ce code peut être simplifié pour extraire des valeurs spécifiques directement du XML
//            table.addCell("Code douane");
//            table.addCell("1553301R");
//            table.addCell("Raison sociale");
//            table.addCell("TEST COMPANY");
//            table.addCell("Adresse");
//            table.addCell("24 bis rue d'égalité, Sidi Amor, Manouba, Tunisie");
//            table.addCell("Chiffre d'affaires exportateurs");
//            table.addCell("0.00");
//            table.addCell("Chiffre d'affaires autres entreprises");
//            table.addCell("300000.00");
//            table.addCell("Montant transféré (en devises)");
//            table.addCell("300000.00");
//            table.addCell("Contre-valeur en dinar");
//            table.addCell("300000.00");
//            table.addCell("Forme");
//            table.addCell("Succursales, filiales ou prises de participation");
//            table.addCell("Lieu d'implantation (Code pays)");
//            table.addCell("FR");
//            table.addCell("Adresse complète");
//            table.addCell("30450 rue des joujou, Montpellier, 2054");
//
//            // Ajouter le tableau au document
//            document.add(table);
//
//            // Fermer le document
//            document.close();
//            return outputStream.toByteArray();
//
        }
     return null;
    }

	  
}
