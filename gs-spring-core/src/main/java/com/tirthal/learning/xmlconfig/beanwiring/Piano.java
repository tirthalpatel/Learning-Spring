package com.tirthal.learning.xmlconfig.beanwiring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Piano implements Instrument {

	private static final Logger log = LoggerFactory.getLogger(Piano.class);
	
	public void play() {
		log.debug("PLINK PLINK PLINK");
	}

}
