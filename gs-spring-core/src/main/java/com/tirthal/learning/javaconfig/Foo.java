package com.tirthal.learning.javaconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {

	private static final Logger log = LoggerFactory.getLogger(Foo.class);
	
	public void foo() {
		log.debug("calling foo...");
	}
}
