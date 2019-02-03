package com.tirthal.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(Source.class)
public class GreetingProducer {

	// Source: Identifies the contract for the message producer by providing the destination to which the produced message is sent
	@Autowired
	private Source source;

	public boolean publish(String message) {
		
		// Send message to Source output channel
		return source.output().send(MessageBuilder.withPayload(message.toUpperCase()).build());
	}
}
