package com.tirthal.learning.javaconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bar {

	private static final Logger log = LoggerFactory.getLogger(Bar.class);

	Foo f;

	public Bar(Foo f) {
		this.f = f;
	}

	public Foo getF() {
		return f;
	}

	public void setF(Foo f) {
		this.f = f;
	}

	public void bar() {
		log.debug("calling bar...");
	}
}
