package com.projetPfe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservices.IConversationService;
import com.projetPfe.entities.Message;
import com.projetPfe.entities.SessionConversationnelle;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/conversations")
public class ConversationController {
	@Autowired
	private IConversationService conversationService;
	
	 @GetMapping("/{id}")
	 public SessionConversationnelle getDossierById(@PathVariable Long id) {
	        return conversationService.getConversation(id);
	    }
	 
	 @GetMapping
	 public List<SessionConversationnelle>  getAll() {
	        return conversationService.getAll();
	    }
	 
	 @GetMapping("/Employe/{id}")
	 public List<SessionConversationnelle>  getConversationsById(@PathVariable Long id) {
	        return conversationService.getCoversationsById(id);
	    }
	 
	 @PostMapping
	 public SessionConversationnelle addConversation(@RequestBody SessionConversationnelle c){
		 return conversationService.addConversation(c);
	 }
	 

}
