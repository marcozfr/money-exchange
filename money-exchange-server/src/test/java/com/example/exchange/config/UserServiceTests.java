package com.example.exchange.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.exchange.repositories.UserRepository;
import com.example.exchange.service.impl.UsersService;

@RunWith(SpringRunner.class)
public class UserServiceTests {
	
	@MockBean
    private UserRepository userRepository;
	
	@Autowired
	private UsersService userService;
	
	@TestConfiguration
    static class UserServiceTestsConfig {
  
        @Bean
        public UsersService userService() {
            return new UsersService();
        }
    }
	
	@Before
	public void setUp() throws ParseException {
	 
	    Mockito.when(userRepository.findUserByDetails("doesnotexist"))
	      .thenReturn(null);
	    
	    com.example.exchange.model.User user = new com.example.exchange.model.User();
	    user.setUsername("user1");
	    user.setPassword("supersecret");
	    user.setFirstName("User");
	    user.setLastName("User");
	    Mockito.when(userRepository.findUserByDetails("user1"))
	      .thenReturn(user);
	}

	@Test
	public void contextLoads() {
		
	}
	
	/**
	 * Test if an exception is raised when an username does not exist in database
	 */
	@Test(expected=UsernameNotFoundException.class)
	public void throwExceptionOnInexistentUserTest() {
		userService.loadUserByUsername("doesnotexist");
	}
	
	/**
	 * Check if a retrieved user has the required authorities
	 */
	@Test
	public void checkAuthoritiesOnExistingUserTest() {
		UserDetails user = userService.loadUserByUsername("user1");
		assertThat(user.getAuthorities()).hasOnlyOneElementSatisfying(a->{
			assertThat(a.getAuthority()).isEqualTo("user");
		});
	}
	
	
}
