package com.tirthal.learning.bus.endpoint;

import org.springframework.cloud.bus.endpoint.AbstractBusEndpoint;
import org.springframework.cloud.bus.endpoint.BusEndpoint;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tirthal.learning.bus.event.PrintDateTimeRemoteApplicationEvent;

/**
 * @author tirthalp
 */
@ManagedResource
public class PrintDateTimeEndpoint extends AbstractBusEndpoint {

	public PrintDateTimeEndpoint(ApplicationEventPublisher context, String id, BusEndpoint delegate) {
		super(context, id, delegate);
	}

	@RequestMapping(value = "print", method = RequestMethod.POST)
	@ResponseBody
	@ManagedOperation
	public void printDateTime(@RequestParam(value = "destination", required = false) String destination,
			@RequestParam(value = "what", required = false) String whatToPrint) {

		publish(new PrintDateTimeRemoteApplicationEvent(this, getInstanceId(), destination, whatToPrint));
	}

}