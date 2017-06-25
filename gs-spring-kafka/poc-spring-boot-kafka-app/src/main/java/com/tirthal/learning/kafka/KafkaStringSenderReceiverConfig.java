package com.tirthal.learning.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * By default, Spring Boot provides auto-configured kafkaTemplate and consumerFactory beans for String message.
 * 
 * This code is only required to explicitly create beans of kafkaTemplate and consumerFactory, if there is custom
 * implemented beans like jsonKafkaTemplate or jsonConsumerFactory in the application code.
 * 
 * @author tirthalp
 */
@Configuration
@EnableKafka
public class KafkaStringSenderReceiverConfig {

	@Autowired
	private Environment env;

	// -------------------------------------------------------
	// Default String Producer / Sender configuration
	// -------------------------------------------------------

	/*
	 * Default kafkaTemplate bean - used in 'StringSender' and expected by Spring Boot for Kafka auto-configuration
	 * b'cas another custom jsonKafkaTemplate bean has been defined in KafkaUserJsonSenderReceiverConfig.java
	 */

	@Bean
	public Map<String, Object> producerConfig() {
		Map<String, Object> props = new HashMap<>();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.bootstrap-servers"));
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return props;
	}

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	// -------------------------------------------------------
	// Default String Consumer / Receiver configuration
	// -------------------------------------------------------

	/*
	 * Default cosumerFactory bean - used in 'StringReceiver' and expected by Spring Boot for Kafka auto-configuration.
	 * B'cas another custom stringBatchConsumerFactory has been defined for stringBatchKafkaListenerContainerFactory()
	 * and custom jsonConsumerFactory bean has been defined in KafkaUserJsonSenderReceiverConfig.java
	 */

	@Bean
	public Map<String, Object> consumerConfig() {
		Map<String, Object> props = new HashMap<>();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.bootstrap-servers"));
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, env.getProperty("spring.kafka.consumer.auto-offset-reset"));

		return props;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = consumerConfig();

		props.put(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("spring.kafka.consumer.group-id"));
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, "poc-spring-boot-kafka-app-default-cf");

		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

	// -------------------------------------------------------
	// Custom Beans configuration for 'StringBatchReceiver'
	// -------------------------------------------------------

	@Bean
	public ConsumerFactory<String, String> stringBatchConsumerFactory() {
		Map<String, Object> props = consumerConfig();

		props.put(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("spring.kafka.consumer.group-id") + "_string_batch");
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, "poc-spring-boot-kafka-app-json-batch-cf");
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5"); // maximum records per batch receive

		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> stringBatchKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(stringBatchConsumerFactory());
		factory.setBatchListener(true); // enable batch listeners

		return factory;
	}
}
