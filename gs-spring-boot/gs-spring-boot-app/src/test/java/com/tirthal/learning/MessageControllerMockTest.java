package com.tirthal.learning;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tirthal.learning.controller.MessageController;
import com.tirthal.learning.model.Message;
import com.tirthal.learning.service.MessageService;

public class MessageControllerMockTest {

	@InjectMocks
	private MessageController mockMsgController;

	@Mock
	private MessageService mockMsgService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetMessageByValidId() {
		Message testMsg = new Message();
		testMsg.setId(1);
		when(mockMsgService.findMessage(1)).thenReturn(testMsg);

		ResponseEntity<Message> resultMsg = mockMsgController.getMessageById(1);
		assertEquals(new Integer(1), resultMsg.getBody().getId());
		assertEquals(HttpStatus.OK, resultMsg.getStatusCode());
	}

	@Test
	public void testGetMessageByInvalidId() {
		when(mockMsgService.findMessage(1)).thenReturn(null);
		
		ResponseEntity<Message> resultMsg = mockMsgController.getMessageById(1);
		assertNull(resultMsg.getBody());
		assertEquals(HttpStatus.NOT_FOUND, resultMsg.getStatusCode());
	}
}
