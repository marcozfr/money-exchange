package com.example.exchange.repositories;

import java.util.List;

import com.example.exchange.model.Currency;

/**
 * @author marcof
 *
 */
public interface CurrencyRepository {
	
	/**
	 * Retrieves all currencies
	 * @return List of currencies
	 */
	public List<Currency> getCurrencies();

}
