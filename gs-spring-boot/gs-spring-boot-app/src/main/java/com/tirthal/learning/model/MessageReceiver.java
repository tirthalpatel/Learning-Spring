package com.tirthal.learning.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

	public void receiveMessage(String message) {
		LOGGER.info(">>> Redis message receiver <" + message + ">");
	}
}
