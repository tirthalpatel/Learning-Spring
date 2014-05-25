package com.tirthal.learning.helloworld.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sample implementation class of greetings service interface
 * 
 * @author tirthalp
 */
public class GreetingServiceImpl implements GreetingService {

	private static final Logger log = LoggerFactory.getLogger(GreetingServiceImpl.class);
	
	@Override
	public void sayHello(Message msg) {
		// Logic or even this may propagate call to DAO layer
		
		log.debug("Saying Hello - title is '" + msg.getTitle() +"' & description is '" + msg.getDescription() + "'");		
	}

}
