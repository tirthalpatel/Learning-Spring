package com.tirthal.learning;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

@EnableBinding(Processor.class)
public class GreetingProcessor {

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public String transformToUpperCase(String message) {		
		return message.toUpperCase();
	}
}
