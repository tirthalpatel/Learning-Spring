package com.tirthal.learning.xmlconfig.beanwiring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Instrumentalist implements Performer {

	private static final Logger log = LoggerFactory.getLogger(Instrumentalist.class);

	private String song;
	private Instrument instrument;
	
	public void setSong(String song) {
		this.song = song;
	}

	public String getSong() {
		return song;
	}

	public String screamSong() {
		return song;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	
	public void perform() throws PerformanceException {
		log.debug("Playing " + song + " : ");
		instrument.play();
	}

}
