package com.test.main.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import com.test.main.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WebSocketController {
	
	private final ChatService chatService;
	
	@MessageMapping("/hello")
	public void greeting(@Payload String message, StompHeaderAccessor headerAccessor) {
		chatService.sendMessage(headerAccessor, message);
			
	}
}
