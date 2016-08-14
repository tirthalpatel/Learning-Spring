package com.tirthal.learning;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class XxngRestController {
	
	@Autowired
	RestTemplate primaryRT;
	
	@Autowired
	@LoadBalanced
	RestTemplate loadBalancedRT;
	
	@Autowired
	Environment env;
	
	@RequestMapping(method=RequestMethod.GET, value="/xxng")
	@HystrixCommand(fallbackMethod = "fallbackXingXongGame")
	public ResponseEntity<String> playXingXongGame(@RequestParam(value="lb", required=false, defaultValue="true") Boolean lb) {
		
		// Any random character from A to Z + "ing"		
	    String input = generateRandomChar() + "ing";
	    
	    // Consume Xing Xong service
	    String output="";
	    
	    if(lb==true) // via Eureka for service discovery and using Ribbon for client side load balancing
	    	output= loadBalancedRT.getForObject(env.getProperty("xing.xong.service.eureka.registered.url")+input, String.class);
	    else		 // direct service endpoint - what if this service endpoint is down?
	    	output= primaryRT.getForObject(env.getProperty("xing.xong.service.direct.url")+input, String.class);
				
		// Won or Lost?
		if("Xong".equalsIgnoreCase(output)) {
			return new ResponseEntity<String>(input+" "+output+" : Awesome, you won. Play again.", HttpStatus.OK);			
		} else {
			return new ResponseEntity<String>(input+" "+output+" : Oops, you lost. Try again.", HttpStatus.OK);
		}				
	}
	
	// Method signature and return type must match with original method, i.e. with playXingXongGame
	public ResponseEntity<String> fallbackXingXongGame(@RequestParam(value="lb", required=false, defaultValue="true") Boolean lb) {
		
		// Ideally this can return some meaningful default response, if applicable for given use case!
		
		return new ResponseEntity<String>("Unfortunately, service is unavailable for the time being! Please try after some time.", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	private char generateRandomChar() {		
		return ENGLISH_ALPHABET.charAt(new Random().nextInt(ENGLISH_ALPHABET.length()));	    
	}	
	private static final String ENGLISH_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
}
