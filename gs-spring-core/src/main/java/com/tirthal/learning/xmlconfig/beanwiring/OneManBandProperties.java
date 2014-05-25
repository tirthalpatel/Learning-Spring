package com.tirthal.learning.xmlconfig.beanwiring;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneManBandProperties implements Performer {

	private static final Logger log = LoggerFactory.getLogger(OneManBandProperties.class);
	
	public void perform() throws PerformanceException {
		for(Object key : instruments.keySet()) {			
			String instrumentSound = instruments.getProperty((String) key);
			log.debug(key + " : " + instrumentSound);
		}
	}

	private Properties instruments;

	public void setInstruments(Properties instruments) {
		this.instruments = instruments;
	}
}
