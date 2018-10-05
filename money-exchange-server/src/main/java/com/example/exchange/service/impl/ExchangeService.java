package com.example.exchange.service.impl;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchange.config.exception.ClientException;
import com.example.exchange.config.exception.ObjectNotFoundException;
import com.example.exchange.dto.ExchangeRequestDTO;
import com.example.exchange.dto.ExchangeResponseDTO;
import com.example.exchange.model.ExchangeRate;
import com.example.exchange.repositories.ExchangeRepository;
import com.example.exchange.util.Util;

/**
 * @author marcof
 *
 */
@Service
public class ExchangeService {
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	@PostConstruct
	public void init() {
		
	}
	
	/**
	 * Retrieves a exchange response with a exchange rate based on an input of type ExchangeRequestDTO
	 * @param request
	 * @return The ExchangeReponseDTO object
	 * @throws ObjectNotFoundException 
	 */
	public ExchangeResponseDTO getExchange(ExchangeRequestDTO request) throws ObjectNotFoundException, ClientException {
		ExchangeResponseDTO response = new ExchangeResponseDTO();
		Date exchangeDate = Util.parseDate(request.getExchangeDate(), "yyyy-MM-dd");
		ExchangeRate rate = exchangeRepository.getExchangeRate(exchangeDate, request.getOrigin(), request.getDestination());
		if(rate == null) {
			throw new ObjectNotFoundException("Exchange rate not found for given date and currencies");
		}
		response.setExchangeRate(rate.getExchangeRate());
		response.setLastUpdatedTime(rate.getExchangeDate());
		return response;
	}

}
