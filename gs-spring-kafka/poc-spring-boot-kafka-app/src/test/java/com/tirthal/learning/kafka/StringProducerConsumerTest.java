package com.tirthal.learning.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

import com.tirthal.learning.kafka.consumer.StringReceiver;
import com.tirthal.learning.kafka.producer.StringSender;

/**
 * A simple Spring Kafka Test Case - starting embedded Kafka broker + sending message to topic using
 * {@link StringSender} + Verifying that a message was successfully received by {@link StringReceiver}
 * 
 * <br/>
 * <br/>
 * The spring-kafka-test JAR which is added in pom.xml - provides a number of useful utilities to assist with Kafka
 * related testing in the application. These include: an embedded Kafka server, some static methods to setup
 * consumers/producers and utility methods to fetch results
 * 
 * @author tirthalp`
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringProducerConsumerTest {

	@Autowired
	private StringSender sender;

	@Autowired
	private StringReceiver receiver;

	// This JUnit @ClassRule starts embedded Kafka broker for the test case
	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true);

	// Inform to use embedded Kafka brokers in test cases instead of local/remote Kafka brokers set-up
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
	}

	@Test
	public void test01_sendStringMessageToKafkaTopic() throws Exception {
		// Use the StringSender to send a message to the topic on the Kafka bus
		sender.send(UUID.randomUUID().toString(), "Hello World!"); // without callback
		sender.sendWithCallback(UUID.randomUUID().toString(), "Hello World, plz callback!"); // with callback
	}

	@Test
	public void test02_receiveStringMessageFromKafkaTopic() throws InterruptedException {
		Thread.sleep(10 * 1000);

		// Use the StringReceiver to verify that a message was successfully received
		assertThat(receiver.getMessages().size()).isEqualTo(2);
		assertThat(receiver.getMessages().values().contains("Hello World, plz callback!")).isTrue();
	}
}