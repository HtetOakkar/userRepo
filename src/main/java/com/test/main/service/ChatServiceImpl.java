package com.test.main.service;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

	private final SimpMessageSendingOperations messagingTemplate;

	@Override
	public void sendMessage(StompHeaderAccessor headerAccessor, String message) {
		String username = headerAccessor.getSessionAttributes().get("username") == null? "User" : (String) headerAccessor.getSessionAttributes().get("username");
		
		String formattedMessage = String.format("%s : %s", username, message);
		messagingTemplate.convertAndSend("/topic/hello", formattedMessage);
	}

}
