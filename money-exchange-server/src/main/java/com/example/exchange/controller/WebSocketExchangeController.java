package com.example.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.exchange.dto.ExchangeResponseDTO;

@Controller
public class WebSocketExchangeController {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	
	@MessageMapping("/ws/getExchange")
	public void onReceiveMessage(String message) {
		this.template.convertAndSend("/exchange", new ExchangeResponseDTO());
	}
	

}
