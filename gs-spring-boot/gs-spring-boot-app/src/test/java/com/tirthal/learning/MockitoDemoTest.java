package com.tirthal.learning;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tirthal.learning.controller.MessageRestController;
import com.tirthal.learning.model.Message;
import com.tirthal.learning.service.MessageService;

@TestComponent
public class MockitoDemoTest {

	@InjectMocks
	private MessageRestController mockMsgController;

	@Mock
	private MessageService mockMsgService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetMessageById() {
		Message testMsg = new Message();
		testMsg.setId(1);
		when(mockMsgService.findMessage(1)).thenReturn(testMsg);

		ResponseEntity<Message> resultMsg = mockMsgController.getMessageById(1);
		assertEquals(new Integer(1), resultMsg.getBody().getId());
		assertEquals(HttpStatus.OK, resultMsg.getStatusCode());
	}

	@Test
	public void testGetMessageByIdForNull() {
		when(mockMsgService.findMessage(null)).thenReturn(null);
		
		ResponseEntity<Message> resultMsg = mockMsgController.getMessageById(null);
		assertNull(resultMsg.getBody());
		assertEquals(HttpStatus.NOT_FOUND, resultMsg.getStatusCode());
	}
}
