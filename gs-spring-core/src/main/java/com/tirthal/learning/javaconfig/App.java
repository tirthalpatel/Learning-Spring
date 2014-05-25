package com.tirthal.learning.javaconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Demo of java based Spring configuration. Instead of XML, bean wiring is achieved in AppConfig.java file.
 * 
 * @author tirthalp
 * 
 */
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		log.debug("-----------");
		Bar b = ctx.getBean(Bar.class);
		b.bar();
		b.getF().foo();
	}

}
