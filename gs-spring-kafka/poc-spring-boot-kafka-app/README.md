# Spring Kafka POC

This is Spring Boot project, containing sample code of sending or receiving String or JSON messages using Spring Kafka and Maven.

Apache Kafka is supported by providing auto-configuration of the spring-kafka project in Spring Boot.

The Spring for Apache Kafka (spring-kafka) project applies core Spring concepts to the development of Kafka-based messaging solutions. It provides a "template" as a high-level abstraction for sending messages. It also provides support for Message-driven POJOs with @KafkaListener annotations and a "listener container". 

## Pre-requisites

- Java 8
- Kafka 0.10.2.0
- Maven 3.5

## Key Notes

Some important notes about this project as following.

### How to set up and run?

- [Download and install Apache Kafka](https://github.com/tirthalpatel/Learning-BigData/blob/master/gs-kafka/README.md)
- Import this project in STS and run using Boot Dashboard / Directly run using Maven command

### Versions Info

Spring Boot 1.5.2.RELEASE provides spring-kafka-1.1.3.RELEASE, so overrided to Spring Kafka latest GA version 1.2.0.RELEASE in pom.xml. 

Spring for Apache Kafka is based on the pure java kafka-clients jar. Spring Kafka 1.2.x is compatible with Kafka client version 0.10.2.x. 

- Spring Boot: 1.5.2.RELEASE
- Spring Kafka: 1.2.0.RELEASE 
- Apache Kafka: 0.10.2.0

### Code Highlights  

- `pom.xml`: see Spring Boot and Spring Kafka dependencies

- `application.properties`
	- Spring Boot supported Kafka configuration options like `spring.kafka.bootstrap-servers`, `spring.kafka.consumer.group-id`, `spring.kafka.consumer.auto-offset-reset`, etc.
	- Custom properties of Kafka topic names (`spring.kafka.poc.string.topic` to store string data & `spring.kafka.poc.json.topic` to store json data)

- `logback.xml`: log levels configuration

- How to exchange simple string messages with Kafka topic?
	- Refer `KafkaStringSenderReceiverConfig.java' (for creating default beans of kafkaTemplate and consumerFactory - this code is not required, if there is no custom configured beans like jsonKafkaTemplate or jsonConsumerFactory in the application code)
	- Refer `StringSender.java` (for writing string data to Kafka) and `StringReceiver.java` (for consuming string data from Kafka including in batch mode)
	- Refer `StringProducerConsumerTest.java` (Spring Kafka test case to unit test StringSender and StringReceiver)
	- Refer `StringMessageKafkaController.java` (Providing REST APIs for writing String message to Kafka topic using StringSender)

- How to exchange User objects data in JSON format with Kafka topic?
	- Refer 'User.java' (just a User POJO)
	- Refer `KafkaUserJsonSenderReceiverConfig.java' (custom configured beans like jsonKafkaTemplate or jsonConsumerFactory)
	- Refer `UserJsonSender.java` (for writing user data in JSON format to Kafka) and `UserJsonReceiver.java` (for consuming users data in JSON format from Kafka)
	- Refer `UserJsonProducerConsumerTest.java` (Spring Kafka test case to unit test UserJsonSender and UserJsonReceiver)
	- Refer `UserKafkaController.java` (Providing REST APIs for sending users data to Kafka topic using UserJsonSender)

### Try

- How to exchange simple string messages with Kafka topic?
	- Run `StringProducerConsumerTest as JUnit Test` (starts embedded Kafka server, executes test case and should see 'Received data=ConsumerRecord...' log in Console)
	- Start Zookeeper and Kafka servers, then run the 'poc-spring-boot-kafka-app' application as `Spring Boot Application` (also, try below steps by running more than one instances of application on different ports using -Dserver.port)
		- REST endpoint to submit any string message using `POST` method: `http://localhost:8080/kafka/messages` | `http://localhost:8080/kafka/messages?callback=true`
		- REST endpoint to verify KafkaListener received the message using `GET` method: `http://localhost:8080/kafka/messages`
		- Check application log messages
		- Check Kafka topic has those messages using command: `kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic spring.kafka.poc.messages.topic --from-beginning`

- How to exchange json messages with Kafka topic?
	- Run `UserJsonProducerConsumerTest as JUnit Test` (starts embedded Kafka server, executes test case and should see 'Received data=ConsumerRecord...' log in Console)
	- Start Zookeeper and Kafka servers, then run the 'poc-spring-boot-kafka-app' application as `Spring Boot Application` (also, try below steps by running more than one instances of application on different ports using -Dserver.port)
		- REST endpoint to submit users data in JSON format using `POST` method: `http://localhost:8080/kafka/users?firstname=tirthal&lastname=patel`
		- Check application log messages
		- Check Kafka topic has those messages in JSON format using command: `kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic spring.kafka.poc.users.topic --from-beginning`

## Also Refer

- [Spring Boot - Apache Kafka Support](http://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/htmlsingle/#boot-features-kafka)
- [Spring Kafka - Quick Tour](http://docs.spring.io/spring-kafka/docs/1.2.0.RELEASE/reference/html/_introduction.html#quick-tour)
- [Spring Kafka - Tutorials](https://www.codenotfound.com/spring-kafka/)