package com.example.exchange.dto;

import com.example.exchange.model.Currency;

public class CurrencyDTO {
	
	private String currencySymbol;
	private String currencyCode;
	private String currencyName;
	
	public CurrencyDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CurrencyDTO(Currency currency) {
		currencySymbol = currency.getCurrencySymbol();
		currencyCode = currency.getCurrencyCode();
		currencyName = currency.getCurrencyName();
	}
	
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	
	

}
