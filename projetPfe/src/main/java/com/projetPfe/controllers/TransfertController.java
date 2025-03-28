package com.projetPfe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservice.ITansfertService;
import com.projetPfe.entities.Transfert;


@RestController
@RequestMapping("/transferts")
public class TransfertController {
	@Autowired
	private ITansfertService transfertService;
	
	@GetMapping
	public List<Transfert> getAllTransfert() {
		return transfertService.getAllTransferts();	}
	
	@GetMapping("/alerteTransfertAttente")
	public List<Transfert> AlerteTransfertAttente() {
		return transfertService.AlerteTransfertAttente();	}
	
	

}