package com.tirthal.learning.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tirthal.learning.kafka.producer.UserJsonSender;
import com.tirthal.learning.model.User;

/**
 * Providing REST APIs for posting users to the '${poc.kafka.topic.json.data}' topic
 * 
 * @author tirthalp
 */
@RestController
public class UserKafkaController {

	@Autowired
	private UserJsonSender userSender;

	@RequestMapping(value = "/kafka/users", method = RequestMethod.POST)
	public ResponseEntity<String> sendUserJsonToKafkaStringTopic(
			@RequestParam(required = false, name = "firstname", defaultValue = "***") String fname,
			@RequestParam(required = false, name = "lastname", defaultValue = "***") String lname) {

		final String key = UUID.randomUUID().toString();

		userSender.send(key, new User(fname, lname));

		return new ResponseEntity<String>("Submitted message to the Kafka topic with key: " + key, HttpStatus.ACCEPTED);
	}
}
