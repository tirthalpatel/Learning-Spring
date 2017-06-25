package com.tirthal.learning.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tirthal.learning.kafka.consumer.StringReceiver;
import com.tirthal.learning.kafka.producer.StringSender;

/**
 * Providing REST APIs for writing String message to '${poc.kafka.topic.string.data}' topic
 * 
 * @author tirthalp
 */
@RestController
public class StringMessageKafkaController {

	@Autowired
	private StringSender stringSender;

	@Autowired
	private StringReceiver stringReceiver;

	@RequestMapping(value = "/kafka/messages", method = RequestMethod.POST)
	public ResponseEntity<String> sendMessageToKafkaStringTopic(@RequestBody String message,
			@RequestParam(required = false, name = "callback", defaultValue = "false") Boolean cb) {

		final String key = UUID.randomUUID().toString();
		if (!cb)
			stringSender.send(key, message);
		else
			stringSender.sendWithCallback(key, message);

		/*
		 * Fire-and-Forget strategy - b'cas controller flow is not blocked for getting status of operation from send()
		 * 
		 * Not reliable response - always returns ACCEPTED (202) status, even if, it fails to send message to Kafka
		 * topic when Kafka broker is down
		 * 
		 * ACCEPTED: Request has been accepted for processing, but the processing has not been completed. The request
		 * might or might not eventually be acted upon, as it might be disallowed when processing actually takes place
		 */

		return new ResponseEntity<String>("Submitted message to the Kafka topic with key: " + key, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/kafka/messages", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> getMessagesFromKafkaStringTopic() {

		return new ResponseEntity<Map<String, String>>(stringReceiver.getMessages(), HttpStatus.FOUND);
	}
}
