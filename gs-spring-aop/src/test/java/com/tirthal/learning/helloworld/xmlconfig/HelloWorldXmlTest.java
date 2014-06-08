package com.tirthal.learning.helloworld.xmlconfig;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// @RunWith(SpringJUnit4ClassRunner.class) - the makes the junit test class a spring bean, meaning that it can be injected with other spring managed beans
// @ContextConfiguration – this tells spring where the test application context is located
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/com/tirthal/learning/helloworld/xmlconfig/helloworld.xml")
public class HelloWorldXmlTest {

	@Autowired
	TracingAspect tracingAspect;
	@Autowired
	GreetingService greetingService;

	@Test
	public void adviceIsCalled() {
		assertFalse(tracingAspect.isEnteringCalled());
		assertFalse(tracingAspect.isExitedCalled());
		greetingService.sayHello();
		assertTrue(tracingAspect.isEnteringCalled());
		assertTrue(tracingAspect.isExitedCalled());
	}

	public TracingAspect getTracingAspect() {
		return tracingAspect;
	}

	public void setTracingAspect(TracingAspect tracingAspect) {
		this.tracingAspect = tracingAspect;
	}

	public GreetingService getGreetingService() {
		return greetingService;
	}

	public void setGreetingService(GreetingService greetingService) {
		this.greetingService = greetingService;
	}
}
