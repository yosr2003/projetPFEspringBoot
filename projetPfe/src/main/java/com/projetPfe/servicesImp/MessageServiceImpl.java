package com.projetPfe.servicesImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservices.IMessageService;
import com.projetPfe.entities.Message;
import com.projetPfe.entities.SessionConversationnelle;
import com.projetPfe.repositories.ConversationRepository;
import com.projetPfe.repositories.MessageRepository;
@Service
public class MessageServiceImpl implements IMessageService{
	@Autowired
	private MessageRepository messageRepo;
	@Autowired
	private ConversationRepository convrsRepo;

	@Override
	public Message addMessage(Message m,Long idConversation) {
		Optional<SessionConversationnelle> conversation=convrsRepo.findById(idConversation); 
		if(conversation.isPresent()) {
	    m.setConversation(conversation.get());
		return messageRepo.save(m);
		}
		return null;
		
	}

	@Override
	public List<Message> getAll() {
		return messageRepo.findAll();
	}

	@Override
	public List<Message> getByConversation(Long id) {
		Optional<SessionConversationnelle> conversation=convrsRepo.findById(id); 
		if(conversation.isPresent()) {
			List<Message> messages=messageRepo.findAll();
			return messages.stream()
			            .filter(m -> m.getConversation().equals(id))
			            .collect(Collectors.toList());
		}
		
		return null;
	}

}
