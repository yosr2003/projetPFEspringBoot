package com.projetPfe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.projetPfe.entities.TauxChange;
import com.projetPfe.implService.DossierDelegueServiceImpl;
import com.projetPfe.implService.TransfertServiceImpl;
import com.projetPfe.repositories.DossierDelegueRepository;
import com.projetPfe.repositories.TauxChangeRepository;
import com.projetPfe.repositories.TransfertRepository;

public class TransfertTest {
	@InjectMocks
    private TransfertServiceImpl transfertService;

    @Mock
    private TransfertRepository transfertRepo;
    @Mock
    private TauxChangeRepository tauxchangeRepo;
	 @Test
	    void testCalculerFrais_TNDSource_BEN() {
	        TauxChange taux = new TauxChange();
	        taux.setDevise("EUR");
	        taux.setCoursVente(3.2);

	        Mockito.when(tauxchangeRepo.findByDevise("EUR")).thenReturn(Optional.of(taux));

	        Optional<Object> result = transfertService.calculerFrais(100.0, "EUR", "TND", "BEN", 20.0);

	        assertTrue(result.isPresent());
	        Map<String, Object> res = (Map<String, Object>) result.get();
	        assertEquals(100.0, res.get("montantInitial"));
	        assertEquals(320.0, res.get("montantConverti"));
	        assertEquals(300.0, res.get("montantFinal"));
	        assertEquals(3.2, res.get("TauxChange"));
	    }


}
