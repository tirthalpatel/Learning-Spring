package com.tirthal.learning.xmlconfig.beanwiring;

import java.util.Collection;

public class OneManBand implements Performer {

	public void perform() throws PerformanceException {
		for(Instrument instrument : instruments) {
			instrument.play();
		}
	}

	private Collection<Instrument> instruments;

	public void setInstruments(Collection<Instrument> instruments) {
		this.instruments = instruments;
	}
}
