package com.projetPfe.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_message ;
	private Instant  timestamp ;
 	@Lob
    private String texteMessage;

    private String intention;
    @ManyToOne
	@JoinColumn(name = "Conversation_id")
	private SessionConversationnelle conversation;
	public Message() {
		super();
	}
	public Long getId_message() {
		return id_message;
	}
	public void setId_message(Long id_message) {
		this.id_message = id_message;
	}
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public String getTexteMessage() {
		return texteMessage;
	}
	public void setTexteMessage(String texteMessage) {
		this.texteMessage = texteMessage;
	}
	public String getIntention() {
		return intention;
	}
	public void setIntention(String intention) {
		this.intention = intention;
	}
	public SessionConversationnelle getConversation() {
		return conversation;
	}
	public void setConversation(SessionConversationnelle conversation) {
		this.conversation = conversation;
	}
    
}
