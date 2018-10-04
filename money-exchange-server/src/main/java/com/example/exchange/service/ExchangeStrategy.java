package com.example.exchange.service;

import com.example.exchange.dto.ExchangeRequestDTO;
import com.example.exchange.dto.ExchangeResponseDTO;

public interface ExchangeStrategy {
	
	/**
	 * Retrieves an {@link ExchangeResponseDTO} object with specific format
	 * @param request
	 * @return
	 */
	public ExchangeResponseDTO exchange(ExchangeRequestDTO request);

}
