package com.tirthal.learning.bus;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tirthal.learning.bus.endpoint.PrintDateTimeEndpoint;
import com.tirthal.learning.bus.event.PrintDateTimeListener;

/**
 * @author tirthalp
 */
@Configuration
@ConditionalOnProperty(value = "spring.cloud.bus.enabled", matchIfMissing = true)
@RemoteApplicationEventScan("com.tirthal.learning.bus.event")
public class SpringCloudBusConfiguration {

	@Bean
	@ConditionalOnProperty(value = "com.tirthal.learning.bus.print.datetime.enabled", matchIfMissing = true)
	public PrintDateTimeListener printDateTimeListener() {
		return new PrintDateTimeListener();
	}

	@Bean
	@ConditionalOnProperty(value = "management.endpoint.com.tirthal.learning.bus.print.datetime.enabled", matchIfMissing = true)
	public PrintDateTimeEndpoint printDateTimeBusEndpoint(ApplicationContext context) {
		return new PrintDateTimeEndpoint(context, context.getId());
	}
}
