package com.tirthal.learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "Welcome to the Spring Boot application!";
	}
	
}
