package com.projetPfe.implService;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
//import com.projetPfe.entities.Response;
import com.projetPfe.repositories.DossierDelegueRepository;


@Service
public class DossierDelegueServiceImpl implements IDossierDelegueService{
	@Autowired
	private DossierDelegueRepository dossierDelegueRepo;

	@Override
	public List<DossierDelegue> getAllDossierDelegues() {
		// TODO Auto-generated method stub
		return dossierDelegueRepo.findAll();}
	@Override
	public Optional<DossierDelegue> getDossierById(String id) {
        return dossierDelegueRepo.findById(id);
    }
	@Override
	public ResponseEntity<DossierDelegue> cloturerDossier(DossierDelegue d,String id) {
		if(dossierDelegueRepo.findById(id).isPresent()) {
			DossierDelegue dossier=dossierDelegueRepo.findById(id).get();
			if (dossier.getEtatDoss().equals(EtatDoss.Validé)) {
				dossier.setDatclo(d.getDatclo());
				dossier.setMotifclo(d.getMotifclo());
				dossier.setEtatDoss(EtatDoss.Clôturé);
				HttpHeaders headers = new HttpHeaders();
				headers.add("code",String.valueOf(HttpStatus.OK.value()));
				headers.add("libelle","SERVICE_OK"); return new ResponseEntity<>(dossierDelegueRepo.save(dossier),headers,HttpStatus.OK);
				//return ResponseEntity.status(HttpStatus.OK).headers(headers).body(dossier);
			}else {return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
		}
		return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
//	@Override
//	public ResponseEntity<Response<DossierDelegue>> clotureDossier(DossierDelegue d, String id) {
//		if(dossierDelegueRepo.findById(id).isPresent()) {
//			DossierDelegue dossier=dossierDelegueRepo.findById(id).get();
//			if (dossier.getEtatDoss().equals(EtatDoss.Validé)) {
//				dossier.setDatclo(d.getDatclo());
//				dossier.setMotifclo(d.getMotifclo());
//				dossier.setEtatDoss(EtatDoss.Clôturé);
//				HttpHeaders headers = new HttpHeaders();
//				headers.add("code",String.valueOf(HttpStatus.OK.value()));
//				headers.add("libelle","SERVICE_OK");
//				return ResponseEntity.ok(new Response<>(200, "SERVICE_OK", dossier));
//				
//			}else { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response<>(401, "UNAUTHORIZED", null));}
//		}
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response<>(500, "INTERNAL_SERVER_ERROR", null));
//	}

}
