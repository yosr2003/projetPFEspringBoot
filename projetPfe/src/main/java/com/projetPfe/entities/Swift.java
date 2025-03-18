package com.projetPfe.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Swift {
	@Id
	private String idSwift;
	private String module="TRF";
	private String typemsg;
	private String seq97;
	private LocalDate datgen;
	private String message;
	
	public Swift() {
		super();
	}
	
	
	///getters et setters
	public String getIdSwift() {
		return idSwift;
	}
	public void setIdSwift(String idSwift) {
		this.idSwift = idSwift;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getTypemsg() {
		return typemsg;
	}
	public void setTypemsg(String typemsg) {
		this.typemsg = typemsg;
	}
	public String getSeq97() {
		return seq97;
	}
	public void setSeq97(String seq97) {
		this.seq97 = seq97;
	}
	public LocalDate getDatgen() {
		return datgen;
	}
	public void setDatgen(LocalDate datgen) {
		this.datgen = datgen;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	

}
