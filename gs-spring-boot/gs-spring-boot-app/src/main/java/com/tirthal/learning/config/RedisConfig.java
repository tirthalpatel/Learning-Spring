package com.tirthal.learning.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.tirthal.learning.model.MessageReceiver;

@Configuration
public class RedisConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);
	
	@Autowired
	Environment env;

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		LOGGER.info(">>> Declaring the RedisMessageListenerContainer Bean...");
		
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic(env.getProperty("myapp.redis.topic.name")));

		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
		LOGGER.info(">>> Declaring the MessageListenerAdapter Bean...");
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	MessageReceiver receiver() {
		LOGGER.info(">>> Declaring the MessageReceiver Bean...");
		return new MessageReceiver();
	}

	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		LOGGER.info(">>> Declaring the StringRedisTemplate Bean...");
		return new StringRedisTemplate(connectionFactory);
	}
}
