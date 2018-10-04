package com.example.exchange.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchange.dto.ExchangeRequestDTO;
import com.example.exchange.dto.ExchangeResponseDTO;
import com.example.exchange.model.ExchangeRate;
import com.example.exchange.repositories.ExchangeRepository;
import com.example.exchange.service.ExchangeStrategy;

@Service("euro")
public class EuroExchangeStrategy implements ExchangeStrategy {
	
	@Autowired
	ExchangeRepository exchangeRepository;

	@Override
	public ExchangeResponseDTO exchange(ExchangeRequestDTO request) {
		ExchangeResponseDTO response = new ExchangeResponseDTO();
		Date exchangeDate = null;
		try {
			exchangeDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getExchangeDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ExchangeRate rate = exchangeRepository.getExchangeRate(exchangeDate, request.getOrigin(), request.getDestination());
		response.setExchangeRate(rate.getExchangeRate());
		response.setLastUpdatedTime(rate.getExchangeDate());
		return response;
		
	}

}
