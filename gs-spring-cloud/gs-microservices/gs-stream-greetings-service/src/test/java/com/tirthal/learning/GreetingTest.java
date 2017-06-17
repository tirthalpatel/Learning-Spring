package com.tirthal.learning;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingTest {
	
	@Autowired
	private Sink sink;
	
	@Autowired
	private Processor processor;
	
	@Autowired
	private MessageCollector messageCollector;
	
	@Test
	public void test() throws InterruptedException {
		// Send message to Sink input channel
		Message<String> message = new GenericMessage<>("hello world");
		sink.input().send(message);
		
		// GreetingProcessor receives message from Sink input, transforms message to upper case and sends to Source output
		
		// Receive message from Source output channel
	    @SuppressWarnings("unchecked")
		Message<String> received = (Message<String>) messageCollector.forChannel(processor.output()).take();
	    assertEquals("HELLO WORLD", received.getPayload());
	}	
}
