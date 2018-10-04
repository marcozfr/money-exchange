package com.example.exchange.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.exchange.config.exception.ApplicationException;
import com.example.exchange.dto.ExchangeRequestDTO;
import com.example.exchange.dto.ExchangeResponseDTO;
import com.example.exchange.model.ExchangeRate;
import com.example.exchange.repositories.ExchangeRepository;
import com.example.exchange.service.impl.ExchangeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeServiceTests {
	
	@MockBean
    private ExchangeRepository exchangeRepository;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@TestConfiguration
    static class MoneyExchangeApplicationTestsConfig {
  
        @Bean
        public ExchangeService exchangeService() {
            return new ExchangeService();
        }
    }
	
	@Before
	public void setUp() throws ParseException {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Date noExchangeDate = formatter.parse("2022-10-02");
	    Date hasExchangeDate = formatter.parse("2018-10-02");
	 
	    Mockito.when(exchangeRepository.getExchangeRate(noExchangeDate, "USD", "EUR"))
	      .thenReturn(null);
	    
	    Mockito.when(exchangeRepository.getExchangeRate(ArgumentMatchers.eq(noExchangeDate), 
	    		ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
	      .thenReturn(null);
	    
	    ExchangeRate rate = new ExchangeRate();
	    rate.setExchangeDate(hasExchangeDate);
	    rate.setExchangeId(3L);
	    rate.setExchangeRate(BigDecimal.valueOf(1.223));
	    Mockito.when(exchangeRepository.getExchangeRate(hasExchangeDate, "USD", "EUR"))
	      .thenReturn(rate);
	}

	@Test
	public void contextLoads() {
		
	}
	
	/**
	 * Test exchange rate retrieval for valid date
	 * @throws ApplicationException 
	 */
	@Test
	public void hasExchangeDateTest() throws ApplicationException {
		ExchangeRequestDTO request = new ExchangeRequestDTO();
		request.setExchangeDate("2018-10-02");
		request.setOrigin("USD");
		request.setDestination("EUR");
		ExchangeResponseDTO response = exchangeService.getExchange(request);
		
		assertThat(response.getExchangeRate()).isEqualTo(BigDecimal.valueOf(1.223));
	}
	
	/**
	 * Test exchange rate retrieval for invalid date
	 * @throws ApplicationException 
	 */
	@Test(expected=ApplicationException.class)
	public void hasNoExchangeDateTest() throws ApplicationException {
		ExchangeRequestDTO request = new ExchangeRequestDTO();
		request.setExchangeDate("2022-10-02");
		request.setOrigin("USD");
		request.setDestination("EUR");
		ExchangeResponseDTO response = exchangeService.getExchange(request);
	}
	
	/**
	 * Test exchange rate retrieval for invalid origin and destinations
	 * @throws ApplicationException 
	 */
	@Test(expected=ApplicationException.class)
	public void hasNoExchangeRateForOriginAndDestinationTest() throws ApplicationException {
		ExchangeRequestDTO request = new ExchangeRequestDTO();
		request.setExchangeDate("2023-10-02");
		request.setOrigin("USD");
		request.setDestination("EUR");
		ExchangeResponseDTO response = exchangeService.getExchange(request);
	}
	
	/**
	 * Test when input date is not a valid formatted date.
	 * @throws ApplicationException 
	 */
	@Test(expected=ApplicationException.class)
	public void invalidDateFormatTest() throws ApplicationException {
		ExchangeRequestDTO request = new ExchangeRequestDTO();
		request.setExchangeDate("2023-10-XX");
		request.setOrigin("PEN");
		request.setDestination("EUR");
		ExchangeResponseDTO response = exchangeService.getExchange(request);
	}
	
}
