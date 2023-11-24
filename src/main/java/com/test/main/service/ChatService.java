package com.test.main.service;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public interface ChatService {
	public void sendMessage(StompHeaderAccessor headerAccessor, String message);
}
