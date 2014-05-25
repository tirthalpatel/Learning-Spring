package com.tirthal.learning.xmlconfig.autowire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/tirthal/learning/xmlconfig/autowire/app-container.xml");

		log.debug("-------------------------------");
		User user = ctx.getBean("user1", User.class);
		log.debug(user.toString() + " - address is autowired: " + user.getAddress());
	}
}
