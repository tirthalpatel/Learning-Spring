package com.tirthal.learning.kafka.consumer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * This is a simple Kafka Consumer for receiving String messages from Kafka Topic, which internally uses default Spring
 * Boot driven auto-configuration.
 * 
 * @author tirthalp
 */
@Component
public class StringReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringReceiver.class);

	private Map<String, String> messages = new ConcurrentHashMap<>();

	public Map<String, String> getMessages() {
		return messages;
	}

	/*
	 * When the Apache Kafka infrastructure is present, any bean can be annotated with @KafkaListener to create a
	 * listener endpoint. If no KafkaListenerContainerFactory has been defined, a default one is configured
	 * automatically by Spring Boot with keys defined in spring.kafka.listener.*. Here, the topic name is specified
	 * using the ${kafka.topic.string.data} placeholder for which the value will be automatically fetched from the
	 * application.properties file.
	 */
	@KafkaListener(id = "string-listener", topics = "${poc.kafka.topic.string.data}")
	public void defaultReceive(ConsumerRecord<?, ?> record) {
		LOGGER.info("Received string data='{}' from topic='{}'", record.toString(), record.topic());

		// ... implement business logic here ...

		messages.put(record.key().toString(), record.value().toString());
	}

	// Receive the messages in batch mode for bulk messages
	@KafkaListener(id = "string-batch-listener", topics = "${poc.kafka.topic.string.data}", containerFactory = "stringBatchKafkaListenerContainerFactory")
	public void batchReceive(List<ConsumerRecord<String, String>> data) {

		LOGGER.info("Start of batch received");
		for (int i = 0; i < data.size(); i++) {

			final ConsumerRecord<String, String> record = data.get(i);

			LOGGER.info("In Batch - Received string data='{}' from topic='{}' with partition-offset='{}'", record,
					record.topic(), record.partition() + "-" + record.offset());
		}
		LOGGER.info("End of batch received");
	}
}
