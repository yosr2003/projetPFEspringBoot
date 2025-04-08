package com.projetPfe.implService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservice.IDossierDelegueService;
import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.EtatDoss;
import com.projetPfe.repositories.DossierDelegueRepository;

@Service
public class DossierDelegueServiceImpl implements IDossierDelegueService {

    @Autowired
    private DossierDelegueRepository dossierDelegueRepo;

    @Override
    public List<DossierDelegue> getAllDossierDelegues() {
        return dossierDelegueRepo.findAll();
    }

    @Override
    public Optional<DossierDelegue> getDossierById(String id) {
        return dossierDelegueRepo.findById(id);
    }

    @Override
    public ResponseEntity<DossierDelegue> cloturerDossier(DossierDelegue d, String id) {
        Optional<DossierDelegue> dossierOptional = dossierDelegueRepo.findById(id);
        if (dossierOptional.isPresent()) {
            DossierDelegue dossier = dossierOptional.get();
            if (dossier.getEtatDoss().equals(EtatDoss.Validé)) {
                dossier.setDatclo(d.getDatclo());
                dossier.setMotifclo(d.getMotifclo());
                dossier.setEtatDoss(EtatDoss.Clôturé);
                
                HttpHeaders headers = new HttpHeaders();
                headers.add("code", String.valueOf(HttpStatus.OK.value()));
                headers.add("libelle", "SERVICE_OK");

                return new ResponseEntity<>(dossierDelegueRepo.save(dossier), headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public DossierDelegue updateDossier(DossierDelegue dossier) {
        if (dossierDelegueRepo.existsById(dossier.getIdDossier())) {
            return dossierDelegueRepo.save(dossier);
        } else {
            throw new RuntimeException("Dossier non trouvé pour mise à jour");
        }
    }
}

