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
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.tirthal.learning.model.User;

/**
 * Kafka configuration for User's JSON data producer and consumer
 * 
 * @author tirthalp
 */
@Configuration
@EnableKafka
public class KafkaUserJsonSenderReceiverConfig {

	@Autowired
	private Environment env;

	// -------------------------------------------------------
	// JSON Producer / Sender configuration
	// -------------------------------------------------------

	@Bean
	public ProducerFactory<String, User> jsonProducerFactory() {

		Map<String, Object> props = new HashMap<>();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.bootstrap-servers"));
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // JSON

		return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	public KafkaTemplate<String, User> jsonKafkaTemplate() {
		return new KafkaTemplate<>(jsonProducerFactory());
	}

	// -------------------------------------------------------
	// JSON Consumer / Receiver configuration
	// -------------------------------------------------------

	@Bean
	public ConsumerFactory<String, User> jsonConsumerFactory() {

		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.bootstrap-servers"));
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); // JSON

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, env.getProperty("spring.kafka.consumer.auto-offset-reset"));
		props.put(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("spring.kafka.consumer.group-id") + "_json");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(User.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> jsonKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(jsonConsumerFactory());

		return factory;
	}

}
