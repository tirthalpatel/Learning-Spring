package com.tirthal.learning.helloworld.annotationdriven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("greetingService")
public class GreetingServiceImpl implements GreetingService {

	Logger log = LoggerFactory.getLogger(GreetingServiceImpl.class);

	@Override
	public void sayHello() {
		log.trace("************ Saying Hello - Executing business logic");
	}
}
