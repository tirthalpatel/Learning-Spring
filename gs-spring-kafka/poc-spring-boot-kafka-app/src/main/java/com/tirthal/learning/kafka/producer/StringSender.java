package com.tirthal.learning.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * This is a simple Kafka Producer for sending String messages to Kafka Topic, which internally uses default Spring Boot driven auto-configuration.
 * 
 * @author tirthalp
 */
@Component
public class StringSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringSender.class);
	
	@Value("${poc.kafka.topic.string.data}")
	private String topic;
	
	/*
	 * The setup and creation of the KafkaTemplate and Producer beans is automatically done by Spring Boot. The only
	 * thing left to do is autowiring the KafkaTemplate and using it in the send() methods.
	 */
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;	

	// Just send string data to topic asynchronously without callback code
	public void send(String key, String data) {
		LOGGER.info("Sending string data='{}' to topic='{}'", data, topic);

		// The KafkaTemplate provides asynchronous send methods returning a Future
		kafkaTemplate.send(topic, key, data);
		
		// Notes:
		// - A Kafka broker by default auto-creates a topic when it receives a request for an unknown topic
		// - If Kafka brokers are down or disconnected, would throw org.apache.kafka.common.errors.TimeoutException: Failed to update metadata after 60000 ms
	}		

	// How to write callback code, which sending string data to topic asynchronously?
	public void sendWithCallback(String key, String data) {
		LOGGER.info("Sending string data='{}' to topic='{}'", data, topic);
				
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, data);

		// Register a callback with the listener to receive the result of the send asynchronously
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				LOGGER.info("Sent string data='{}' with key='{}' and offset='{}'", data, key, result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOGGER.error("Unable to send string data='{}' with key='{}'", data, key, ex);
			}
		});
		// or, to block the sending thread to await the result, invoke the future's get() method
	}
}