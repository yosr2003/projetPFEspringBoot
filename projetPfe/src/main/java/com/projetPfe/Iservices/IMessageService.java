package com.projetPfe.Iservices;

import java.util.List;

import com.projetPfe.entities.Message;

public interface IMessageService {
	public Message addMessage(Message m);
	public List<Message> getAll();
	public List<Message> getByConversation(Long id);
	

}
