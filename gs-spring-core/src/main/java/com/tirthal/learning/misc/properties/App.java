package com.tirthal.learning.misc.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demo code of reading properties files in Spring
 * 
 * Step 1 ---> Configure properties file using XML or Java based config
 * 
 * XML configuration: <context:property-placeholder location="app.properties" />
 * 
 * Java configuration: @PropertySource("app.properties")
 * 
 * Step 2 ---> Inject property value using XML or Annotation
 * 
 * Can be injected into XML: <property name="someName" value="${somePropertyName}" />
 * 
 * Injected in Java using Annotation: @Value(${somePropertyName})
 * 
 * @author tirthalp
 * 
 */
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/tirthal/learning/misc/properties/appContext.xml");

		log.debug("------------");
		Data data = ctx.getBean("data", Data.class);

		log.debug("key.name.1 = " + data.getProp1());
		log.debug("key.name.2 = " + data.getProp2());
	}

}
