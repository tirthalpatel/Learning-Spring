package com.tirthal.learning.annotations.outputgenerator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);
	
	public static void main(String arg[]) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/tirthal/learning/annotations/app-container.xml");
		
		log.debug("--------------------");
		OutputHelper outputHelper = (OutputHelper) ctx.getBean("output");
		outputHelper.generateOutput();
	}

}
