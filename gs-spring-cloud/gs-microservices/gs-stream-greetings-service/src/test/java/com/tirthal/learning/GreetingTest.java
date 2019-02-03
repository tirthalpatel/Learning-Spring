package com.tirthal.learning;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingTest {
	
	// Refer to instance of bean (Sink and Source) that Stream adds to container
	
	// Source (the contract for the message producer): Bindable interface with one output channel
	@Autowired
	private Source source;
	
	// Sink (the contract for the message consumer): Bindable interface with one input channel
	@Autowired
	private Sink sink;
	
	@Autowired
	private MessageCollector messageCollector;
	
	@Test
	public void test() throws InterruptedException {
		// Send message to Source output channel
		Message<String> message = new GenericMessage<>("hello world".toUpperCase());
		source.output().send(message);			
		
		// Receive message from Sink input channel
	    @SuppressWarnings("unchecked")
		Message<String> received = (Message<String>) messageCollector.forChannel(sink.input()).poll();
	    assertEquals("HELLO WORLD", received.getPayload());
	}	
}
