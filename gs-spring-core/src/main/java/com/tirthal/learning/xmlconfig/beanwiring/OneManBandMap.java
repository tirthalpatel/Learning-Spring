package com.tirthal.learning.xmlconfig.beanwiring;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneManBandMap implements Performer {

	private static final Logger log = LoggerFactory.getLogger(OneManBandMap.class);

	public void perform() throws PerformanceException {
		for(String key : instruments.keySet()) {
			log.debug(key + " : ");
			Instrument instrument = instruments.get(key);
			instrument.play();
		}
	}

	private Map<String, Instrument> instruments;

	public void setInstruments(Map<String, Instrument> instruments) {
		this.instruments = instruments;
	}
}
