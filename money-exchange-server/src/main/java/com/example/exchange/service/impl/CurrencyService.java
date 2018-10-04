package com.example.exchange.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchange.dto.CurrencyDTO;
import com.example.exchange.model.Currency;
import com.example.exchange.repositories.CurrencyRepository;

@Service
public class CurrencyService {
	
	@Autowired
	CurrencyRepository currencyRepository;
	
	public List<CurrencyDTO> getCurrencies(){
		List<Currency> currencies = currencyRepository.getCurrencies();
		return currencies.stream().map(CurrencyDTO::new).collect(Collectors.toList());
	}

}
