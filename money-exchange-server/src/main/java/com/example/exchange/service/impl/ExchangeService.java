package com.example.exchange.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.exchange.service.ExchangeStrategy;

@Component
public class ExchangeService {
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@PostConstruct
	public void init() {
		
	}
	
	public ExchangeStrategy getStrategy(String strategyName){
		return applicationContext.getBean(strategyName, ExchangeStrategy.class);
	}

}
