package com.tirthal.learning.kafka.consumer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.tirthal.learning.model.User;

/**
 * This is a simple Kafka Consumer for receiving User as json messages from Kafka Topic
 * 
 * @author tirthalp
 */
@Component
public class UserJsonReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserJsonReceiver.class);

	private Map<String, User> messages = new ConcurrentHashMap<>();

	public Map<String, User> getMessages() {
		return messages;
	}
	
	@KafkaListener(id = "json-listener", topics = "${poc.kafka.topic.json.data}", containerFactory = "jsonKafkaListenerContainerFactory")
	public void receive(ConsumerRecord<String, User> record) {
		LOGGER.info("Received json data='{}' from topic='{}'", record.toString(), record.topic());

		// ... implement business logic here ...

		messages.put(record.key().toString(), record.value());
	}
}
