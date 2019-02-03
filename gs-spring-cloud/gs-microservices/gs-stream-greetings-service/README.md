# Demo Greetings Microservice to try Spring Cloud Stream

This is a simple greetings microservice to try Spring Cloud Stream concepts (Producer-Consumer / Publisher-Subscriber). 

Spring Cloud Stream is a framework for building highly scalable event-driven microservices connected with shared messaging systems. Spring Cloud Stream builds upon Spring Boot to create standalone, production-grade Spring applications and uses Spring Integration to provide connectivity to message brokers. It provides opinionated configuration of middleware from several vendors, introducing the concepts of persistent publish-subscribe semantics, consumer groups, and partitions.

In other words, Spring Cloud Stream offers an interface (abstraction layer) for developers that requires no knowledge of the underlying broker. That broker, either Apache Kafka or RabbitMQ, gets configured by Spring Cloud Stream. Communication to and from the broker is also done via the Stream library.

* Pulling the configuration information from Spring Cloud Config Server
* Registering to Spring Cloud Netflix Eureka Server for Service Discovery
* Building Event-driven Microservices with Spring Cloud Stream (using RabbitMQ or Kafka Binder)

## Pre-requisites

__Dependency__ : RabbitMQ (Alternatively Kafka is also supported by Spring Cloud Stream - just need to switch 'Cloud Stream Rabbit' with 'Cloud Stream Kafka' dependency in pom.xml file, if need to use Kafka message broker instead of RabbitMQ)

* [Parent README.md](../README.md)
* Start RabbitMQ server (e.g. for Windows: enable management plugin `rabbitmq-plugins enable rabbitmq_management` + start server `sbin\rabbitmq-server.bat`)
* Projects that need to be started before
	- [gs-config-server](../gs-config-server/README.md): For pulling the configuration information
	- [gs-discovery-eureka-server](../gs-discovery-eureka-server/README.md): For discovering service instances from Eureka server

## Key Notes

1. Created project using SPRING INITIALIZR. Selected 'Config Client', 'Eureka Discovery', 'Spring Web' and 'Cloud Stream Rabbit' dependencies.
2. Added property files in 'gs-config-repo' folder: 'gs-stream-greetings-service.properties'. Added 'bootstrap.properties' in project resources to locate config server.
3. Implemented 'GreetingController.java' and 'GreetingProducer.java': To enable POST message using REST endpoint, which should be sent to Spring Cloud Stream provided output channel ('Source.OUTPUT' - the contract for the message producer)
4. Implemented 'GreetingConsumer.java': To have multiple consumers of 'Sink.INPUT' (the contract for the message consumer) for logging message and its length
5. Configured required Spring Cloud Stream Binding configuration in 'src\main\resources\application.properties'
6. Implemented 'GreetingTest.java': To try Spring Cloud Stream Test support

Message Flow:

1. Producer: `/greetings?message=Hello World` -> `Source.OUTPUT`
2. Consumers: `Source.OUTPUT` -> `@StreamListener(SINK.INPUT)`

### Try

1. Access RabbitMQ Management UI (username: guest, password: guest): `http://localhost:15672/`
2. Run 'gs-stream-greetings-service' service (also try following steps when two or more instances of the service are running e.g. two instances on port 9001 and 9002)
3. POST messages, check app logs and optionally monitor message broker (exchanges/topics on RabbitMQ/Kafka): `http://localhost:9001/greetings?message=Hello World` | `http://localhost:9002/greetings?message=Hello World`
4. Comment "spring.cloud.stream.bindings.input.group" configuration in src\main\resources\application.properties and try step 2 and 3 again