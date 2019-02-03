# Demo module implementing Custom Event and Endpoint in Spring Cloud Bus

This is demo module to implement custom bus endpoints and events to be broadcasted via Spring Cloud Bus.

By Spring Cloud Bus, the currently implemented two actuator endpoints are" `\bus\refresh` (reloads each application’s configuration, just as if they had all been pinged on their /refresh endpoint) and `bus\env` (sends key/value pairs to update each node’s Spring Environment). See Spring Cloud Bus code [here](https://github.com/spring-cloud/spring-cloud-bus/tree/master/spring-cloud-bus/src/main/java/org/springframework/cloud/bus). 

Now let's say, we want to add our custom bus actuator endpoints such as: `\bus\print`, `\bus\print?what=date`, `\bus\print?what=time`, `\bus\print?what=datetime`. This event broadcasting should print date and/or time at each applications' nodes in the cluster. At the same time, it should support "destination' parameter as part of HTTP endpoint the way it is supported in "bus/refresh" and "bus/env" endpoints. Well, this code is implemented to do exactly this. 

This project doesn't run in isolation. Rather this is added as dependency module as below in spring cloud bus demo microservices (see 'gs-bus-hello-service' and 'gs-bus-bye-service').

	<dependency>
		<groupId>com.tirthal.learning</groupId>
		<artifactId>gs-bus-custom-events</artifactId>
		<version>1.0</version>
	</dependency>

## Pre-requisites

* [Parent README.md](../README.md)

## Key Notes

1. Created project using SPRING INITIALIZR. Added 'Spring Cloud Bus' dependency as optional in pom.xml file.
2. Firstly, implemented `PrintDateTimeRemoteApplicationEvent.java`, which extends `org.springframework.cloud.bus.event.RemoteApplicationEvent`. This is a custom event, which has 'whatToPrint' attribute with default value as 'datetime'. In similar way, more custom event can be implemented. 
3. Secondly, implemented `PrintDateTimeListener.java`, which implements `org.springframework.context.ApplicationListener<PrintDateTimeRemoteApplicationEvent>`. This is custom event listener for the 'PrintDateTimeRemoteApplicationEvent' event, which contains logic in 'onApplicationEvent' method and executes it when this event occurs. In similar way, more custom listeners can be implemented for corresponding custom events.
4. Thirdly, implemented `PrintDateTimeEndpoint.java`, which extends `org.springframework.cloud.bus.endpoint.AbstractBusEndpoint`. This has code to create custom bus actuator HTTP endpoints (e.g. '\bus-print?what=datetime'), which internally publishes 'PrintDateTimeRemoteApplicationEvent'.
5. Finally, implemented `SpringCloudBusConfiguration.java`. This has configuration for spring container. Specifically `@RemoteApplicationEventScan` annotation tells that in which package to look up for custom implemented events of type RemoteApplicationEvent. Also, producing beans of custom *Listener and *Endpoint implementations. 
6. Be aware, internally the events communication among nodes happen via `springCloudBus` (default value of `spring.cloud.bus.destination` property) spring cloud stream destination exists in `AMQP (RabbitMQ) or Kafka Message Broker`.