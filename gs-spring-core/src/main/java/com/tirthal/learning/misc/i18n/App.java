package com.tirthal.learning.misc.i18n;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	private static final Logger log = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("com/tirthal/learning/misc/i18n/messageContext.xml");
		
		log.debug("-----------------------");
		String englishMsg = context.getMessage("name.question", null, Locale.ENGLISH);
		String frenchMsg = context.getMessage("name.question", null, Locale.FRENCH);
		String customMsg = context.getMessage("welcome.user", new Object[] { "Kaitrina Zaiff" }, Locale.FRENCH);

		log.debug(englishMsg + " - " + frenchMsg);
		log.debug(customMsg);
	}
}
