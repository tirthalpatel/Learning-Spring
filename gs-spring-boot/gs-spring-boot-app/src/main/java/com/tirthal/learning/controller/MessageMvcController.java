package com.tirthal.learning.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tirthal.learning.model.Message;
import com.tirthal.learning.service.MessageService;



@Controller
@RequestMapping("/messages.html")
public class MessageMvcController {

	private static final Logger log = LoggerFactory.getLogger(MessageMvcController.class);	
	
	@Autowired(required=false)
	private MessageService messageService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String readAllMessages(Model model, Message message) {
		
		List<Message> allMessages = messageService.findAllMessage();
		if (allMessages != null) {
			model.addAttribute("messages", allMessages);			
		}
		
		return "messageList";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addToMessagesList(@Valid Message message, BindingResult bindingResult, Errors errors) {
		
		if (bindingResult.hasErrors()) {						
			return "messageList";
        }
		
		if(messageService.isMessageExist(message)) { // Don't allow to duplicate title in messages
			log.info("Unable to create. A message with title '" + message.getTitle() + "' already exist");		
			errors.rejectValue("title", "title.duplicate", "A message with title '" + message.getTitle() + "' already exist. Try again.");
			
			return "messageList";
		}
		
		messageService.saveMessage(message);
		
		return "redirect:/messages.html";
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public String deleteMessage(@RequestParam("id") Integer id) {				
		
		messageService.deleteMessage(id);
		
		return "redirect:/messages.html";
	}	
}
