package com.example.exchange.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.example.exchange.config.exception.ClientException;
import com.example.exchange.util.Util;

public class UtilTest {
	
	@Test(expected=ClientException.class)
	public void invalidDateTest() throws ClientException {
		Util.parseDate("xzxcz", "yyyy-MM-dd");
	}
	
	@Test
	public void validDateTest() throws ClientException {
		Date date = Util.parseDate("2018-09-20", "yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		assertThat(cal.get(Calendar.YEAR)).isEqualTo(2018);
		assertThat(cal.get(Calendar.MONTH)).isEqualTo(8);
		assertThat(cal.get(Calendar.DAY_OF_MONTH)).isEqualTo(20);
	}
	
	@Test(expected=ClientException.class)
	public void invalidFormatTest() throws ClientException {
		Date date = Util.parseDate("2018-09-20", "yyxx");
	}

}
