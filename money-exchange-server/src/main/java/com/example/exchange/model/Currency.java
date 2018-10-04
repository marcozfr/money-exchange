package com.example.exchange.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CURRENCY")
public class Currency {
	
	@Id 
	@Column(name="CURRENCY_CODE")
	private String currencyCode;
	
	@Column(name="CURRENCY_SYMBOL")
	private String currencySymbol;
	
	@Column(name="CURRENCY_NAME")
	private String currencyName;

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

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	
	

}
