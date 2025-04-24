package com.projetPfe.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;


@Entity
public class Swift {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idSwift;
	private String typemsg;
	private LocalDateTime datgen;
	@Lob // Indique que c'est un Large Object (long texte ou binaire)
	@Column(name = "txtmsg", columnDefinition = "TEXT")
	private String txtmsg;
	private String format;
	@Lob
    private byte[] pdfgen;
	
	public Swift() {
		super();
	}

	@OneToOne
	@JoinColumn(name = "ref_tranfert")
	private Transfert transfert;

	   
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Long getIdSwift() {
		return idSwift;
	}
	public void setIdSwift(Long idSwift) {
		this.idSwift = idSwift;
	}
	public String getTypemsg() {
		return typemsg;
	}
	public void setTypemsg(String typemsg) {
		this.typemsg = typemsg;
	}
	public LocalDateTime getDatgen() {
		return datgen;
	}
	public void setDatgen(LocalDateTime datgen) {
		this.datgen = datgen;
	}
	public String getTxtmsg() {
		return txtmsg;
	}
	public void setTxtmsg(String txtmsg) {
		this.txtmsg = txtmsg;
	}
	public Transfert getTransfert() {
		return transfert;
	}
	public void setTransfert(Transfert transfert) {
		this.transfert = transfert;
	}
	public byte[] getPdfgen() {
		return pdfgen;
	}
	public void setPdfgen(byte[] pdfgen) {
		this.pdfgen = pdfgen;
	}




}


