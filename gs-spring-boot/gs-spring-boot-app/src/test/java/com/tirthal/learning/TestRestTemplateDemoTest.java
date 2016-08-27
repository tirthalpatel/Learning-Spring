package com.tirthal.learning;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestRestTemplateDemoTest {
	
	@Autowired
	ConfigurableEnvironment context;
	
	@Autowired
	private TestRestTemplate restTemplate;	
	
	@Test
	public void testAllMessages() throws JsonProcessingException, IOException {											
		
		ResponseEntity<String> getMsg = this.restTemplate.getForEntity("/messages", String.class);
		
		assertThat(getMsg.getStatusCode(), equalTo(HttpStatus.OK));
		
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode responseJson = objMapper.readTree(getMsg.getBody());				
		
		assertThat(responseJson.toString().contains("Spring Boot"), is(true));
		assertThat(responseJson.isMissingNode(), is(false));
		assertThat(responseJson.size(), greaterThanOrEqualTo(3));							
	}	
}
