package com.tirthal.learning.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tirthal.learning.model.Message;
import com.tirthal.learning.service.MessageService;

@RestController
public class MessageController {

	private static final Logger log = LoggerFactory.getLogger(MessageController.class);	
	
	@Autowired
	private MessageService messageService;
		
	@RequestMapping(value="/messages", method=RequestMethod.GET)
	@ApiOperation(value="Retrieve All Messages")
	public ResponseEntity<List<Message>> getAllMessages() {
		
		List<Message> messages = messageService.findAllMessage();
		
		if(messages.isEmpty()) {
			return new ResponseEntity<List<Message>>(HttpStatus.NO_CONTENT);
		}		
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}
	
	@RequestMapping(value="/messages/{id}", method=RequestMethod.GET)
	@ApiOperation(value="Retrieve an Existing Message")
	public ResponseEntity<Message> getMessageById(@PathVariable Integer id) {
		
		Message existingMessage = messageService.findMessage(id);
		
		if(existingMessage==null) {
			log.info("Unable to find. A message with id '" + id + "' doesn't exist");
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Message>(existingMessage, HttpStatus.OK);
	}	
	
	@RequestMapping(value="/messages", method=RequestMethod.POST)
	@ApiOperation(value="Create New Message")	
	public ResponseEntity<Message> createMessage(@RequestBody Message message, UriComponentsBuilder ucBuilder) {
		
		if(messageService.isMessageExist(message)) { // Don't allow to duplicate title in messages
			log.info("Unable to create. A message with title '" + message.getTitle() + "' already exist");
			return new ResponseEntity<Message>(HttpStatus.CONFLICT);
		}
		
		messageService.saveMessage(message);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/messages/{id}").buildAndExpand(message.getId()).toUri());
        
		return new ResponseEntity<Message>(message, headers, HttpStatus.CREATED);
	}		
	
	@RequestMapping(value="/messages/{id}", method=RequestMethod.PUT)
	@ApiOperation(value="Update Existing Message")	
	public ResponseEntity<Message> updateMessage(@PathVariable Integer id, @RequestBody Message message) {
		
		if(messageService.isMessageExist(message)) { // Don't allow to duplicate title in messages
			log.info("Unable to update. A message with title '" + message.getTitle() + "' already exist");
			return new ResponseEntity<Message>(HttpStatus.CONFLICT);
		}
		
		Message existingMessage = messageService.findMessage(id);
		
		if(existingMessage==null) {
			log.info("Unable to update. A message with id '" + id + "' doesn't exist");
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND); 
		}
				
		BeanUtils.copyProperties(message, existingMessage, "id");
		messageService.saveMessage(existingMessage);
		
		return new ResponseEntity<Message>(existingMessage, HttpStatus.OK);
	}
	
	@RequestMapping(value="/messages/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="Delete Existing Message")	
	public ResponseEntity<Void> deleteMessage(@PathVariable Integer id) {
		
		Message existingMessage = messageService.findMessage(id);
		
		if(existingMessage==null) {
			log.info("Unable to delete. A message with id '" + id + "' doesn't exist");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); 
		}
		
		messageService.deleteMessage(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
	}
	
	@RequestMapping(value = "/messages", method = RequestMethod.DELETE)
	@ApiOperation(value="Delete All Messages")	
    public ResponseEntity<Void> deleteAllMessages() {    
		 
		 	messageService.deleteAllMessages();
		 	
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
