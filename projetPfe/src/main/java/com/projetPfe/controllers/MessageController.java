package com.projetPfe.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetPfe.Iservices.IMessageService;
import com.projetPfe.entities.Message;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/messages")
public class MessageController {
	@Autowired
	private IMessageService messageService;
	
	
	@GetMapping
	 public List<Message> getAll() {
	        return messageService.getAll();
	    }
	
	@GetMapping("/{id}")
	 public List<Message> getByConversation(@PathVariable Long id) {
	        return messageService.getByConversation(id);
	    }
	
	@PostMapping
	 public Message addMessage(@RequestBody Message m){
		 return messageService.addMessage(m);
	 }
	 

}
