package com.tirthal.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GsSpringBootGradleApplication {

	private static final Logger log = LoggerFactory.getLogger(GsSpringBootGradleApplication.class);
	
	public static void main(String[] args) {
		
		log.info("Awesome! Spring Boot application starts...");
		
		SpringApplication.run(GsSpringBootGradleApplication.class, args);
	}
}
