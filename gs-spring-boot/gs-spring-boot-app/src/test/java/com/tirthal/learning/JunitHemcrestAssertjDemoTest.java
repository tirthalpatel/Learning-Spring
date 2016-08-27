package com.tirthal.learning;

import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import static org.hamcrest.Matchers.*;

import com.tirthal.learning.controller.HomeRestController;

@TestComponent
public class JunitHemcrestAssertjDemoTest { 

	String result = null;
	
	@Before
	public void setup() {
		HomeRestController hc = new HomeRestController();
		result = hc.home();
	}
	
	@Test
    public void testHomeResultUsingJunit() {		
		
		// Assert using JUnit
		assertEquals(result, "Welcome to the Spring Boot application!");
    }
	
	@Test
    public void testHomeResultUsingHemcrest() {		
		
		// Assert using Hemcrest - yeah, code is more readable
		org.hamcrest.MatcherAssert.assertThat(result, is("Welcome to the Spring Boot application!"));				
    }
	
	@Test
    public void testHomeResultUsingAssertJ() {		
		
		// Assert using AssertJ - a fluent assertion library
		org.assertj.core.api.Assertions.assertThat(result).startsWith("Welcome")
														  .endsWith("application!")
														  .isEqualTo("Welcome to the Spring Boot application!");
    }
}
