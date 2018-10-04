package com.example.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
public class EnvController {
	
	@Autowired
	PasswordEncoder encoder;
	
	/**
	 * Encodes a given string with a Password encoder
	 * @param token
	 * @return the encoded string
	 */
	@RequestMapping(value="/encode/{token}",method=RequestMethod.GET)
    public String encrypt(@PathVariable("token") CharSequence token) {
		return encoder.encode(token);
    }

}
