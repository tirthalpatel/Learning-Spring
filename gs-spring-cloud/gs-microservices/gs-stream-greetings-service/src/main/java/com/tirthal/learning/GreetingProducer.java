package com.tirthal.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class GreetingProducer {

	@Autowired
	private Sink sink;

	public boolean publish(String message) {
		return sink.input().send(MessageBuilder.withPayload(message).build());
	}
}
