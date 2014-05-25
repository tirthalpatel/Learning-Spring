package com.tirthal.learning.helloworld.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demo client application consuming greeting service - with and without Spring.
 * 
 * @author tirthalp
 * 
 */
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {				
		
		/**
		 * ================ OPTION 1 - Without using Spring DI (Dependency Injection) =====================
		 * 
		 * Implementation class of GreetingService is tightly coupled in the calling client
		 */
		GreetingService greeting = new GreetingServiceImpl();
		
		Message msg1 = new Message("Simple Demo", "This is not Spring Hello World demo");
		greeting.sayHello(msg1);
		
		log.debug("----------------");
		
		/**
		 * ================ OPTION 2 - Using Spring DI (Dependency Injection) =====================		  
		 */
		
		// ----------> Step 1 - Get Spring Application Context by loading "helloworld-app-container.xml" file located in the classpath
		/**
		 * Ideally we should place "helloworld-app-container.xml" file in resources folder and in that case it can be accessed like,
		 * ApplicationContext appContext = new ClassPathXmlApplicationContext("helloworld-app-container.xml");
		 */
		ApplicationContext appContext = new ClassPathXmlApplicationContext("com/tirthal/learning/helloworld/greeting/app-container.xml");		
		
		log.debug("------------------");
		
		// ----------> Step 2 - Get a bean instances using Spring DI and execute service method = loosely coupled design

		// Values assigned to message can be controlled in xml file without changing code here
		Message msg2 = appContext.getBean("message", Message.class);
		
		// GreetingService implementation class can be changed in xml file without impacting below client code
		GreetingService greeting1 = (GreetingService) appContext.getBean("greetingService"); 
		greeting1.sayHello(msg2);		
		
		/**
		 * -------------- Let's try Spring bean scope - singleton vs. prototype	
		 */		
		GreetingService greeting2 = (GreetingService) appContext.getBean("greetingService"); 
		GreetingService greeting3 = (GreetingService) appContext.getBean("greetingService"); 					
		if(greeting2 == greeting3) {
			log.debug(greeting2 + " and " + greeting3 + " are the same bean instances"); // singleton scope
		} else {
			log.debug(greeting2 + " and " + greeting3 + " are the different bean instances");
		}
		
		GreetingService greeting4 = (GreetingService) appContext.getBean("greetingServicePrototype"); 		
		GreetingService greeting5 = (GreetingService) appContext.getBean("greetingServicePrototype"); 				
		if(greeting4 == greeting5) {
			log.debug(greeting4 + " and " + greeting5 + " are the same bean instances");
		} else {
			log.debug(greeting4 + " and " + greeting5 + " are the different bean instances"); // prototype scope
		}
	}

}
