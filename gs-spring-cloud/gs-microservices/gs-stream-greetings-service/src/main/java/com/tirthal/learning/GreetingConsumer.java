package com.tirthal.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding(Source.class)
public class GreetingConsumer {

	private static final Logger _log = LoggerFactory.getLogger(GreetingConsumer.class);

	@StreamListener(Source.OUTPUT)
	public void logMessage(String message) {
		_log.info("Received message: '{}'", message);
	}
	
	@StreamListener(Source.OUTPUT)
	public void logMessageLength(String message) {
		_log.info("Received message - length: '{}' - '{}'", message, message.length());
	}
}