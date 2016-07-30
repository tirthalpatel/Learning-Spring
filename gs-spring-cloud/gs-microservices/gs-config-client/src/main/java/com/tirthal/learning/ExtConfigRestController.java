package com.tirthal.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ExtConfigRestController {

	@Autowired
	private GsDemoSettings gsDemoSettings;
	
	@RequestMapping(method = RequestMethod.GET, value = "/password")
	public String readSecretPassword() {
		return gsDemoSettings.getSecretPassword();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/message")
	public String readFromProperty() {
		return this.value;
	}

	private final String value;

	@Autowired
	public ExtConfigRestController(@Value("${message}") String value) {
		this.value = value;
	}
}