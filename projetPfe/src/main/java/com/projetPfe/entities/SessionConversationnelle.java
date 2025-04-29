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


	 	@ManyToOne
		@JoinColumn(name = "Employe_id")
		private Employe employe;
	 	private Instant  dateCreation ;
	 	private String statut;


		public SessionConversationnelle(Long id_conversation, Instant timestamp, Employe employe, String texteMessage,
				String intention) {
			super();
			this.id_conversation = id_conversation;
			this.employe = employe;
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



		public Instant getDateCreation() {
			return dateCreation;
		}

		public void setDateCreation(Instant dateCreation) {
			this.dateCreation = dateCreation;
		}

		public String getStatut() {
			return statut;
		}

		public void setStatut(String statut) {
			this.statut = statut;
		}

		public Employe getEmploye() {
			return employe;
		}

		public void setEmploye(Employe employe) {
			this.employe = employe;
		}


	    
}
