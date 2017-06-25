package com.tirthal.learning.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.tirthal.learning.model.User;

/**
 * This is a simple Kafka Producer for sending User as json messages to Kafka Topic
 * 
 * @author tirthalp
 */
@Component
public class UserJsonSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserJsonSender.class);

	@Value("${poc.kafka.topic.json.data}")
	private String topic;
	
	private KafkaTemplate<String, User> jsonKafkaTemplate;
	
	@Autowired
	public UserJsonSender(KafkaTemplate<String, User> jsonKafkaTemplate) {
		this.jsonKafkaTemplate = jsonKafkaTemplate;
	}

	public void send(String key, User value) {
		LOGGER.info("Sending json data='{}' to topic='{}'", value, topic);

		jsonKafkaTemplate.send(topic, key, value);
	}
}
