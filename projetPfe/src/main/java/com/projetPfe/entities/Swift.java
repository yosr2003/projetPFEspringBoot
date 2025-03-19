package com.projetPfe.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Swift {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSwift;
	private String module="TRF";
	private String typemsg;
	private String seq97;
	private LocalDateTime datgen;
	private String message;
	private String format;
	
	
	public Swift() {
		super();
	}
	/* 
	@OneToOne
	@JoinColumn(name = "idTransfert")
	private Transfert transfert;

*/

	
	
	///getters et setters
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public int getIdSwift() {
		return idSwift;
	}
	public void setIdSwift(int idSwift) {
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
	public LocalDateTime getDatgen() {
		return datgen;
	}
	public void setDatgen(LocalDateTime datgen) {
		this.datgen = datgen;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	///getters et setters
	

}
