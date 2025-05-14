package com.projetPfe.Iservices;

import java.util.List;

import com.projetPfe.entities.SessionConversationnelle;

public interface IConversationService {
	public SessionConversationnelle getConversation(Long id);
	public List<SessionConversationnelle> getAll();
	public SessionConversationnelle addConversation(SessionConversationnelle conversation);

}
