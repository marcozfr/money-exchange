package com.example.exchange.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.exchange.config.exception.ClientException;

/**
 * Utilities class
 * 
 * @author marcof
 *
 */
public class Util {
	
	/**
	 * Retrieves a Date object from a String formatted date and a given format.
	 * @param date String date to be parsed
	 * @param pattern Date format
	 * @return The Date object
	 * @throws ClientException If the pattern of the date could not be parsed
	 */
	public static Date parseDate(String date, String pattern) throws ClientException {
		Date exchangeDate = null;
		try {
			exchangeDate = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			throw new ClientException("Invalid date format");
		} catch (IllegalArgumentException e) {
			throw new ClientException("Invalid format");
		}
		return exchangeDate;
	}

}
