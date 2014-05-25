package com.tirthal.learning.xmlconfig.beanwiring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Sample music event app code to understand usage of XML based Spring bean wiring. The code is referrenced from Spring in Book action.
 * 
 * @author tirthalp
 * 
 */
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws PerformanceException {
		
		log.debug("--------------> LOADING SPRING APPLICATION CONTEXT");
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/tirthal/learning/xmlconfig/beanwiring/app-container.xml");

		// ----- Bean objects are not instantiated through Magic. Refer bean wiring in Spring configuration file (app-container.xml).
		
		log.debug("--------------> CREATING BEANS THROUGH FACTORY METHODS + INSTANTIATE AND DESTROY METHOD CALL");
		Stage stage = (Stage) ctx.getBean("stage");
		stage.readyToPerform();
		
		log.debug("--------------> INSTANTIATING BEAN OBJECT USING DEFAULT CONSTRUCTOR OF THE CLASS");
		Performer performer = (Performer) ctx.getBean("jugglerOne");
		performer.perform();
		
		log.debug("--------------> SETTING PRIMITIVE VALUE WITH CONSTRUCTOR");
		performer = (Performer) ctx.getBean("jugglerTwo");
		performer.perform();
		
		log.debug("--------------> INJECTING OBJECT REFERENCES WITH CONSTRUCTORS");
		performer = (Performer) ctx.getBean("poeticJuggler");
		performer.perform();
		
		log.debug("--------------> BEANS SCOPING - Prototype");
		log.debug("Always issue new tickets...");
		Ticket ticket = (Ticket) ctx.getBean("ticket");		
		log.debug("Ticket no = " + ticket.issueNewTicket());
		ticket = (Ticket) ctx.getBean("ticket");
		log.debug("Ticket no = " + ticket.issueNewTicket());
		ticket = (Ticket) ctx.getBean("ticket");
		log.debug("Ticket no = " + ticket.issueNewTicket());
		
		log.debug("--------------> INJECTING INTO BEAN PROPERTIES");
		Instrumentalist instrumentalist = (Instrumentalist) ctx.getBean("kenny");
		instrumentalist.perform();		
		
		instrumentalist = (Instrumentalist) ctx.getBean("benny-playing-piano");
		instrumentalist.perform();
		
		log.debug("--------------> Injecting inner beans (class)");
		instrumentalist = (Instrumentalist) ctx.getBean("benny-playing-saxophone");
		instrumentalist.perform();
		
		log.debug("--------------> Wiring properties with Spring’s p namespace");
		instrumentalist = (Instrumentalist) ctx.getBean("benny-playing-saxophone-alternate");
		instrumentalist.perform();
		
		log.debug("--------------> Wiring Collections");
		log.debug("--- Injecting list");
		OneManBand band = (OneManBand) ctx.getBean("hank-having-duplication");
		band.perform();
		log.debug("--- Injecting set");
		band = (OneManBand) ctx.getBean("hank-without-duplication");
		band.perform();
		log.debug("--- Injecting map");
		OneManBandMap bandMap = (OneManBandMap) ctx.getBean("hank-with-instruments-map");
		bandMap.perform();
		log.debug("--- Injecting properties");
		OneManBandProperties bandProps = (OneManBandProperties) ctx.getBean("hank-with-instruments-props");
		bandProps.perform();		
	}

}
