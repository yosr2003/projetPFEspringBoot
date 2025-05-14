package com.projetPfe.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;

@Entity
public class Employe {
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String nom;

		private String prenom;
	
		@Email
		private String email;

		private String MotDePasse;
		
		@Enumerated(EnumType.STRING)
		private ERole Role;
		
		@ManyToMany
		@JoinTable(
		        name = "utilisateur_transferts",
		        joinColumns = @JoinColumn(name = "utilisateur_id"),
		        inverseJoinColumns = @JoinColumn(name = "transfert_id")
		    )
		private List<Transfert> transferts;
		
		@OneToMany(mappedBy = "employe")
		private List<SessionConversationnelle> conversations;
		
		
		public Employe() {
			super();
		}

		
		

//		public Long getIdUtilisateur() {
//			return id;
//		}

	

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMotDePasse() {
			return MotDePasse;
		}

		public void setMotDePasse(String motDePasse) {
			MotDePasse = motDePasse;
		}

		public ERole getRole() {
			return Role;
		}

		public void setRole(ERole role) {
			Role = role;
		}

		public List<Transfert> getTransferts() {
			return transferts;
		}

		public void setTransferts(List<Transfert> transferts) {
			this.transferts = transferts;
		}

		public void setConversations(List<SessionConversationnelle> conversations) {
			this.conversations = conversations;
		}

		public Long getId() {
			return id;
		}



		
	

		 
	
		
}
