package com.tirthal.learning;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.tirthal.learning.controller.HomeController;


public class HomeControllerTest { 

	@Test
    public void testHomeResult() {
		HomeController hc = new HomeController();
		String result = hc.home();
		
		// Assert using JUnit
		assertEquals(result, "Welcome to the Spring Boot application!");
		
		// Assert using Hemcrest - yeah, code is more readable
		assertThat(result, is("Welcome to the Spring Boot application!"));
    }
}
