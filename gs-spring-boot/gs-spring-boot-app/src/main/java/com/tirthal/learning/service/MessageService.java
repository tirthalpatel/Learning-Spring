package com.tirthal.learning.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.tirthal.learning.model.Message;
import com.tirthal.learning.repository.MessageRepository;

@Service
public class MessageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
	 
	@Autowired(required=false)
	private MessageRepository messageRepository;	
	
	@Autowired(required=false)
	StringRedisTemplate redisTemplate;
	
	@Autowired
	Environment env;
	
	public List<Message> findAllMessage() {			
		return messageRepository.findAll();
	}

	public Message saveMessage(Message message) {		
		
		Message msg = messageRepository.saveAndFlush(message);
		
		publishMessageToRedisChannel(msg);
		
		return msg;
	}
	
	private void publishMessageToRedisChannel(Message message) {						
		try {
			redisTemplate.convertAndSend(env.getProperty("myapp.redis.topic.name"), message.toString());
			LOGGER.info(">>> Message <" + message + "> sent to Redis topic <" + env.getProperty("myapp.redis.topic.name") + ">");
		} catch(Exception e) {
			LOGGER.error("Oops, failure occurred while sending message <" + message + "> to Redis topic <" + env.getProperty("myapp.redis.topic.name") + ">");
		}		
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
