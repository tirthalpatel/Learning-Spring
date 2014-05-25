package com.tirthal.learning.xmlconfig.beanwiring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Saxophone implements Instrument {

	private static final Logger log = LoggerFactory.getLogger(Saxophone.class);
	
	public void play() {
		log.debug("TOOT TOOT TOOT");
	}

}
