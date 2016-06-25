package com.tirthal.learning;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebIntegrationTest
public class MessageControllerWebIntegrationTest {

	@Autowired
	Environment env;
	
	@Test
	public void getAllMessages() throws JsonProcessingException, IOException {
		RestTemplate restTemplate = new TestRestTemplate();
		
		// Did you run test with "-Dspring.profiles.active=test" for using application-test.properties?
		ResponseEntity<String> response = restTemplate.getForEntity(env.getProperty("app.rest.endpoint.base")+"messages", String.class);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode responseJson = objMapper.readTree(response.getBody());
		
		assertThat(responseJson.isMissingNode(), is(false));
		assertThat(responseJson.size(), greaterThanOrEqualTo(3));
	}
	
}
