package com.test.main.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSockerEventListener {
	private final SimpMessageSendingOperations messageTemplate;
	
	@EventListener
	public void handleWebSocketDisconnectEventListener(SessionDisconnectEvent event) {
		log.error("Disconnected : {}", event.getCloseStatus());
		StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String formattedMessage = messageFormatter(stompHeaderAccessor, "disconnected");
		messageTemplate.convertAndSend("/topic/hello", formattedMessage);
	}
	
	@EventListener
	public void handleWebSocketConnectEventListener(SessionConnectEvent event) {
		StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String formattedMessage = messageFormatter(stompHeaderAccessor, "connected");
		messageTemplate.convertAndSend("/topic/hello", formattedMessage);
		log.info(formattedMessage);
	}

	@EventListener
	public void handleWebSocketSubscribeEventListener(SessionSubscribeEvent event) {
		StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String formattedMessage = messageFormatter(stompHeaderAccessor, "subscribed");
		messageTemplate.convertAndSend("/topic/hello", formattedMessage);
		log.info(formattedMessage);		
	}

	
	private String messageFormatter(StompHeaderAccessor stompHeaderAccessor, String event) {
		String username = stompHeaderAccessor.getSessionAttributes() == null? "User" : (String) stompHeaderAccessor.getSessionAttributes().get("username");
		return String.format("%s %s.", username, event);
	}
	
}
