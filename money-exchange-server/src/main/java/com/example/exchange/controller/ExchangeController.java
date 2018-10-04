package com.example.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@Autowired
	PasswordEncoder encoder;
	
	@RequestMapping(value="/encode/{token}",method=RequestMethod.GET)
    public String encrypt(@PathVariable("token") CharSequence token) {
		return encoder.encode(token);
    }
	
	/**
	 * Gets the latest exchange rate of a given date 
	 * @param exchangeRequest
	 * @return
	 */
	@RequestMapping(value="/exchange",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExchangeResponseDTO getExchangeRate(@RequestBody ExchangeRequestDTO exchangeRequest) {
		return exchangeService.getStrategy("euro").exchange(exchangeRequest);
    }

}
