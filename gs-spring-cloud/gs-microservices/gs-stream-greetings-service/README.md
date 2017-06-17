# Demo Greetings Microservice to try Spring Cloud Stream

This is a simple greetings microservice to try Spring Cloud Stream concepts. 

* Pulling the configuration information from Spring Cloud Config Server
* Registering to Spring Cloud Netflix Eureka Server for Service Discovery

## Pre-requisites

__Dependency__ : RabbitMQ (Alternatively Kafka is also supported by Spring Cloud Stream - just need to switch 'Cloud Stream Rabbit' with 'Cloud Stream Kafka' dependency in pom.xml file, if need to use Kafka message broker instead of RabbitMQ)

* [Parent README.md](../README.md)
* Start RabbitMQ server 
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server

## Key Notes

1. Created project using SPRING INITIALIZR. Selected 'Config Client', 'Eureka Discovery', 'Spring Web' and 'Cloud Stream Rabbit' dependencies.
2. Added property files in 'gs-config-repo' folder: 'gs-stream-greetings-service.properties'. Added 'bootstrap.properties' in project resources to locate config server.
3. Implemented 'GreetingController.java' and 'GreetingProducer.java': To enable POST message using REST endpoint, which should be sent to Spring Cloud Stream provided input channel
4. Implemented 'GreetingProcessor.java': To consume original string in 'Sink.INPUT', transform to upper case using Spring Cloud Stream Processor and sent to Spring Cloud Stream provided output channel
5. Implemented 'GreetingConsumer.java': To have multiple consumers of 'Source.OUTPUT' for logging message and its length
6. Configured required Spring Cloud Stream Binding configuration in 'src\main\resources\application.properties'
7. Implemented 'GreetingTest.java': To try Spring Cloud Stream Test support

Message Flow:

1. `/greetings?message=Hello World` -> `Sink.INPUT`
2. `Sink.INPUT` -> `Processor's channels (Processor.INPUT - Processor.OUTPUT)` -> `Source.OUTPUT`
3. `Source.OUTPUT` -> `@StreamListener(Source.OUTPUT) consumers`

### Try

1. Run 'gs-stream-greetings-service' service (also try following steps when two or more instances of the service are running)
2. POST messages, check app logs and optionally monitor message broker (exchanges/topics on RabbitMQ/Kafka): `http://localhost:9002/greetings?message=Hello World`
3. Uncomment spring cloud stream binding configuration in src\main\resources\application.properties and try step 2 again