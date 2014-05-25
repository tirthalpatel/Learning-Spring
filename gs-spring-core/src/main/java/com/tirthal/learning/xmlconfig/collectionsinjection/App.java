package com.tirthal.learning.xmlconfig.collectionsinjection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Sample code to demo XML-based approach for Collections injection into Spring Bean properties. Refer
 * "applicationContext.xml" file for Spring configuration.
 * 
 * @author tirthalp
 * 
 */
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/tirthal/learning/xmlconfig/collectionsinjection/applicationContext.xml");

		log.debug("----------------------------------------------------");
		
		// OPTION-1: Collections injection using the Spring API factory Classes
		CollectionContainer hello1 = (CollectionContainer) ctx.getBean("helloWorldBean1");

		log.debug("List: " + hello1.getList());

		log.debug("Set : " + hello1.getSet());

		log.debug("Map : " + hello1.getMap());

		// OPTION-2: Collections injection using Spring's <util:*>
		CollectionContainer hello2 = (CollectionContainer) ctx.getBean("helloWorldBean2");

		log.debug("List: " + hello2.getList());

		log.debug("Set : " + hello2.getSet());

		log.debug("Map : " + hello2.getMap());
	}

}
