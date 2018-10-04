package com.example.exchange.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ExchangeResponseDTO {
	
	public BigDecimal exchangeRate;
	public Date lastUpdatedTime;
	
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
	

}
