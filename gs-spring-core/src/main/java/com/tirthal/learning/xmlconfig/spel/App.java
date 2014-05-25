package com.tirthal.learning.xmlconfig.spel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/tirthal/learning/xmlconfig/spel/app-container.xml");

		log.debug("----------------------------------------------------");
		Catalog catalog = ctx.getBean("catalog2014", Catalog.class);
		
		log.debug(catalog.toString());
	}

}
