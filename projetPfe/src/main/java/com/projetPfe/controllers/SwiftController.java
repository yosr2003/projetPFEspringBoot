package com.projetPfe.controllers;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.entities.DossierDelegue;
import com.projetPfe.entities.DossierScolarité;
import com.projetPfe.servicesImp.DossierDelegueService;
import com.projetPfe.servicesImp.DossierScolariteServiceImp;
import com.projetPfe.servicesImp.SwiftServiceImp;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/Swift")
public class SwiftController {
	@Autowired
	private SwiftServiceImp SwiftServiceImp;
	@Autowired
	private DossierScolariteServiceImp dossierScolariteServiceImp;
	

	
	 
}
