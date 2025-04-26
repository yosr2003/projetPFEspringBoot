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
public class SessionConversationnelle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_conversation ;
	private Instant  timestamp ;

	 	@ManyToOne
		@JoinColumn(name = "Employe_id")
		private Employe employe;
	 	@Lob
	    private String texteMessage;

	    private String intention;

		public SessionConversationnelle(Long id_conversation, Instant timestamp, Employe employe, String texteMessage,
				String intention) {
			super();
			this.id_conversation = id_conversation;
			this.timestamp = timestamp;
			this.employe = employe;
			this.texteMessage = texteMessage;
			this.intention = intention;
		}

		public SessionConversationnelle() {
			super();
		}

		public Long getId_conversation() {
			return id_conversation;
		}

		public void setId_conversation(Long id_conversation) {
			this.id_conversation = id_conversation;
		}

		public Instant getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Instant timestamp) {
			this.timestamp = timestamp;
		}

		public Employe getEmploye() {
			return employe;
		}

		public void setEmploye(Employe employe) {
			this.employe = employe;
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
	    
}
