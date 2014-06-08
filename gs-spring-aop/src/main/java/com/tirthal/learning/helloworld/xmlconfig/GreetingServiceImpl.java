package com.tirthal.learning.helloworld.xmlconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingServiceImpl implements GreetingService {

	Logger log = LoggerFactory.getLogger(GreetingServiceImpl.class);

	@Override
	public void sayHello() {
		log.trace("************ Saying Hello - Executing business logic");
	}
}
