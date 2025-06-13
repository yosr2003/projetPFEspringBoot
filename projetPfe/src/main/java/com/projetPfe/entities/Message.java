package com.projetPfe.entities;

import java.time.Instant;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_message ;
	private Instant  timestamp ;
 	@Lob
    private String texteMessage;
 	@Lob
 	private String texteReponse;
    private String intention;
    
    @ElementCollection
    @CollectionTable(
        name = "message_entites",
        joinColumns = @JoinColumn(name = "message_id")
    )
    @MapKeyColumn(name = "entite_cle")
    @Column(name = "entite_valeur")
    private Map<String, String> entites;

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
//	public SessionConversationnelle getConversation() {
//		return conversation;
//	}
	public Long getConversation() {
	return conversation.getId_conversation();
    }
	public void setConversation(SessionConversationnelle conversation) {
		this.conversation = conversation;
	}
	public String getTexteReponse() {
		return texteReponse;
	}
	public void setTexteReponse(String texteReponse) {
		this.texteReponse = texteReponse;
	}
	public Map<String, String> getEntites() {
		return entites;
	}
	public void setEntites(Map<String, String> entites) {
		this.entites = entites;
	}
    
}
