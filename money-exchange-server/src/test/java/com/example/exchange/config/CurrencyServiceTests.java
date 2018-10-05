package com.example.exchange.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.exchange.config.exception.ApplicationException;
import com.example.exchange.dto.CurrencyDTO;
import com.example.exchange.model.Currency;
import com.example.exchange.repositories.CurrencyRepository;
import com.example.exchange.service.impl.CurrencyService;

@RunWith(SpringRunner.class)
public class CurrencyServiceTests {
	
	@MockBean
    private CurrencyRepository currencyRepository;
	
	@Autowired
	private CurrencyService currencyService;
	
	@TestConfiguration
    static class CurrencyServiceTestsConfig {
		
        @Bean
        public CurrencyService currencyService() {
            return new CurrencyService();
        }
        
    }
	
	@Before
	public void setUp() throws ParseException {

	}

	@Test
	public void contextLoads() {
		
	}
	
	/**
	 * Tests if list is empty
	 * @throws ApplicationException 
	 */
	@Test
	public void shouldReturnEmptyTest() throws ApplicationException {
		Mockito.when(currencyRepository.getCurrencies())
	      .thenReturn(new ArrayList<Currency>());
		
		List<CurrencyDTO> dtos = currencyService.getCurrencies();
		
		assertThat(dtos).isEmpty();
	}
	
}
