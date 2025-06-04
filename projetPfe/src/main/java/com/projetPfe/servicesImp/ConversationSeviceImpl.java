package com.projetPfe.servicesImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetPfe.Iservices.IConversationService;
import com.projetPfe.entities.Employe;
import com.projetPfe.entities.SessionConversationnelle;
import com.projetPfe.repositories.ConversationRepository;
import com.projetPfe.repositories.EmployeRepository;

@Service
public class ConversationSeviceImpl implements IConversationService{
	@Autowired
	private ConversationRepository conversationRepo;
	@Autowired
	private EmployeRepository employeRepo;

	@Override
	public SessionConversationnelle getConversation(Long id) {
		Optional<SessionConversationnelle> conversation=conversationRepo.findById(id);
		if(conversation.isPresent()) {return conversation.get();}
		return null;
		
	}

	@Override
	public List<SessionConversationnelle> getAll() {
		return conversationRepo.findAll();
	}

	@Override
	public SessionConversationnelle addConversation(SessionConversationnelle conversation) {
		Optional<Employe> e= employeRepo.findById(conversation.getEmploye().getId());
		if(e.isPresent()) {return conversationRepo.save(conversation);}
		return null;
		
	}

	@Override
	public List<SessionConversationnelle> getCoversationsById(Long id) {
		List<SessionConversationnelle> conversations= new ArrayList<>();
		for (SessionConversationnelle c : conversationRepo.findAll()) {
			if(c.getEmploye().getId()==id) {conversations.add(c);}
		}
		return conversations;
	}

}
