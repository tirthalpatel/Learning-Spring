package com.tirthal.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class GreetingConsumer {

	private static final Logger _log = LoggerFactory.getLogger(GreetingConsumer.class);

	// Sink: Identifies the contract for the message consumer by providing the destination from which the message is consumed
	
	// Receive message from Sink input channel using StreamListerner
	
	@StreamListener(target=Sink.INPUT)
	public void logMessage(String message) {
		_log.info("Received message: '{}'", message);
	}
	
	@StreamListener(target=Sink.INPUT)
	public void logMessageLength(String message) {
		_log.info("Received message - length: '{}' - '{}'", message, message.length());
	}
}