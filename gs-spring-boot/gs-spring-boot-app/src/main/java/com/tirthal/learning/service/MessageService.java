package com.tirthal.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirthal.learning.model.Message;
import com.tirthal.learning.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired(required=false)
	private MessageRepository messageRepository;	
	
	public List<Message> findAllMessage() {			
		return messageRepository.findAll();
	}

	public Message saveMessage(Message message) {				
		return messageRepository.saveAndFlush(message);
	}
	
	public boolean isMessageExist(Message message) {
		Long c = messageRepository.countByTitle(message.getTitle());
		if(c > 0) 
			return true;
		else
			return false;		
	}
	
	public Message findMessage(Integer id) {
		return messageRepository.findOne(id);
	}

	public void deleteMessage(Integer id) {
		messageRepository.delete(id);
	}
	
	public void deleteAllMessages() {		
		messageRepository.deleteAll();
	}
}
