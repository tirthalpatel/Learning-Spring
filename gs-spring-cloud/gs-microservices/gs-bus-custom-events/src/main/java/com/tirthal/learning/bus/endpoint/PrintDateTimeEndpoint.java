package com.tirthal.learning.bus.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.cloud.bus.endpoint.AbstractBusEndpoint;
import org.springframework.context.ApplicationEventPublisher;

import com.tirthal.learning.bus.event.PrintDateTimeRemoteApplicationEvent;

/**
 * @author tirthalp
 */
@Endpoint(id = "bus-print")
public class PrintDateTimeEndpoint extends AbstractBusEndpoint {

	public PrintDateTimeEndpoint(ApplicationEventPublisher context, String id) {
		super(context, id);
	}

	@WriteOperation
	public void printDateTimeWithDestination(String what, @Selector String destination) {
		publish(new PrintDateTimeRemoteApplicationEvent(this, getInstanceId(), destination, what));
	}
	
	@WriteOperation
	public void printDateTime(String what) {
		publish(new PrintDateTimeRemoteApplicationEvent(this, getInstanceId(), null, what));
	}
}