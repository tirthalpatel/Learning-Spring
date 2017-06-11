package com.tirthal.learning.bus.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;

/**
 * @author tirthalp
 */
public class PrintDateTimeListener implements ApplicationListener<PrintDateTimeRemoteApplicationEvent> {

	private static Log log = LogFactory.getLog(PrintDateTimeListener.class);

	@Override
	public void onApplicationEvent(PrintDateTimeRemoteApplicationEvent event) {

		final String whatToPrint = event.getWhatToPrint().trim().toLowerCase();

		// Custom logic to do something, when this event occurs
		if (whatToPrint.equals("time"))
			log.info("*** Received remote print request. Current time is: " + LocalTime.now());
		else if (whatToPrint.equals("date"))
			log.info("*** Received remote print request. Current date is: " + LocalDate.now());
		else if (whatToPrint.equals("datetime"))
			log.info("*** Received remote print request. Current date and time is: " + LocalDateTime.now());
		else
			log.info("*** Received remote print request. However '"+whatToPrint+"' is unsupported input for parameter.");
	}
}
