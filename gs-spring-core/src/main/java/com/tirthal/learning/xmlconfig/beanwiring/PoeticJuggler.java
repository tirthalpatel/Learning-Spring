package com.tirthal.learning.xmlconfig.beanwiring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoeticJuggler extends Juggler {

	private static final Logger log = LoggerFactory.getLogger(PoeticJuggler.class);
	
	private Poem poem;
	
	public PoeticJuggler(Poem poem)
	{
		super();
		this.poem = poem;
	}
	
	public PoeticJuggler(int beanBags, Poem poem)
	{
		super(beanBags);
		this.poem = poem;
	}
	
	public void perform() throws PerformanceException
	{
		super.perform();
		log.debug("While receiting...");
		poem.recite();
	}
}
