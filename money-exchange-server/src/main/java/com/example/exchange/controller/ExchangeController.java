package com.example.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchange.config.exception.ApplicationException;
import com.example.exchange.dto.ExchangeRequestDTO;
import com.example.exchange.dto.ExchangeResponseDTO;
import com.example.exchange.service.impl.ExchangeService;

/**
 * @author marcof
 *
 */
@RestController
public class ExchangeController {
	
	@Autowired
	ExchangeService exchangeService;
	
	/**
	 * Gets the latest exchange rate of a given date 
	 * @param exchangeRequest
	 * @return
	 * @throws ApplicationException 
	 */
	@RequestMapping(value="/exchange",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExchangeResponseDTO getExchangeRate(@RequestBody ExchangeRequestDTO exchangeRequest) throws ApplicationException {
		return exchangeService.getExchange(exchangeRequest);
    }
	
}
