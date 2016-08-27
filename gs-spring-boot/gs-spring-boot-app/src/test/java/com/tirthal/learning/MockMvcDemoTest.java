package com.tirthal.learning;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tirthal.learning.controller.MessageRestController;
import com.tirthal.learning.model.Message;
import com.tirthal.learning.service.MessageService;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageRestController.class)
public class MockMvcDemoTest {

	@Autowired
	private MockMvc mvc;
	 
	@MockBean
	private MessageService mockMsgService;		
	
	@Test
	public void testGetMessageById() throws Exception {				
		
		given(this.mockMsgService.findMessage(1)).willReturn(new Message(1, "Hello World", "Hello World by Spring Boot Demo App"));
				
		this.mvc.perform(get("/messages/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Spring Boot")));							
	}

}
