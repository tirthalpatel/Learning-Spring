package com.tirthal.learning;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tirthal.learning.model.Message;
import com.tirthal.learning.service.MessageService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebAppConfiguration
/*
 * Added @WebAppConfiguration just to fix below error of Springfox Swagger (see https://github.com/springfox/springfox/issues/654)
 * org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'documentationPluginsBootstrapper' defined in URL
 */
public class MessageServiceIntegrationTest {

	@Autowired
	private MessageService messageService;
	
	@Test
	public void testFindAllMessages() {
		List<Message> messages = messageService.findAllMessage();
		assertThat(messages.size(), is(greaterThanOrEqualTo(3)));
	}
	
}