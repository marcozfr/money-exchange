package com.example.exchange.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.exchange.config.exception.ApplicationException;

public class Util {
	
	public static Date parseDate(String date, String pattern) throws ApplicationException {
		Date exchangeDate = null;
		try {
			exchangeDate = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			throw new ApplicationException("Invalid date format");
		}
		return exchangeDate;
	}

}
