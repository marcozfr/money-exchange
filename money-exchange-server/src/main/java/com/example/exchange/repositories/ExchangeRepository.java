package com.example.exchange.repositories;

import java.util.Date;
import java.util.List;

import com.example.exchange.model.Currency;
import com.example.exchange.model.ExchangeRate;

/**
 * @author marcof
 *
 */
public interface ExchangeRepository {

	/**
	 * Retrieves the last exchangeRate object from database of a given date with a
	 * currency origin and destination
	 * 
	 * @param exchangeDate
	 * @param originCurrency
	 * @param destinationCurrency
	 * @return The exchangeRate entity
	 */
	public ExchangeRate getExchangeRate(Date exchangeDate, String originCurrency, String destinationCurrency);

}
