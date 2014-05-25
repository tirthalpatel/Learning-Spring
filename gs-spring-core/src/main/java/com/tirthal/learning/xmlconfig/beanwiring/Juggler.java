package com.tirthal.learning.xmlconfig.beanwiring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Juggler implements Performer {
	
	private static final Logger log = LoggerFactory.getLogger(Juggler.class);
	
	private int beanBags = 3;
	
	public Juggler() {
		
	}
	
	public Juggler(int beanBags) {
		this.beanBags = beanBags;
	}			
	
	public void perform() throws PerformanceException {
		log.debug("Juggling '" + this.beanBags + "' BeanBags");		
	}

}
