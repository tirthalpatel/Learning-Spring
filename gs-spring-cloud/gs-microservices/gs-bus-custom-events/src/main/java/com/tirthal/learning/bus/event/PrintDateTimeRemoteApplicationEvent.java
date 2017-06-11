package com.tirthal.learning.bus.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * @author tirthalp
 */
@SuppressWarnings("serial")
public class PrintDateTimeRemoteApplicationEvent extends RemoteApplicationEvent {

	private String whatToPrint = "datetime";

	@SuppressWarnings("unused")
	private PrintDateTimeRemoteApplicationEvent() {
		// for serializers
	}

	public PrintDateTimeRemoteApplicationEvent(Object source, String originService, String destinationService,
			String whatToPrint) {
		super(source, originService, destinationService);
		
		// By default, assign to print 'datetime'
		if(whatToPrint != null && whatToPrint.trim().length() > 0)
			this.whatToPrint = whatToPrint;
		else
			this.whatToPrint = "datetime";
	}

	public String getWhatToPrint() {
		return whatToPrint;
	}
}
