package com.example.exchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchange.dto.CurrencyDTO;
import com.example.exchange.service.impl.CurrencyService;

@RestController
public class CurrencyController {
	
	@Autowired
	CurrencyService currencyService;
	
	/**
	 * Retrieves a list of all currencies
	 * @return The list of currencies
	 */
	@RequestMapping(value="/currencies",method=RequestMethod.GET)
    public List<CurrencyDTO> getCurrenciesList() {
		return currencyService.getCurrencies();
    }

}
