package com.example.exchange.config.jwt;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

import com.example.exchange.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter   {
	
	private AuthenticationManager authenticationManager;
	
	private final JwtConfig jwtConfig;
	
	private final ObjectMapper objectMapper;
    
	public JwtAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig, ObjectMapper objectMapper) {
		this.authenticationManager = authManager;
		this.jwtConfig = jwtConfig;
		this.objectMapper = objectMapper;
		
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri()));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		if (CorsUtils.isPreFlightRequest(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return null;
        }
		
		try {
			
			com.example.exchange.model.User loginUser = objectMapper.readValue(
					request.getInputStream(), com.example.exchange.model.User.class);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					loginUser.getUsername(), loginUser.getPassword(), Collections.emptyList());
			
			return authenticationManager.authenticate(authToken);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject(auth.getName())	
			.claim("authorities", auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // ms
			.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
			.compact();
		
		User user = (User)auth.getPrincipal();
		UserDTO userDTO = createUserDTO(user, token);
		
		objectMapper.writeValue(response.getOutputStream(),userDTO);
		
	}

	private UserDTO createUserDTO(User user, String token) {
		UserDTO userDTO = new UserDTO();
		userDTO.setToken(token);
		userDTO.setUsername(user.getUsername());
		return userDTO;
	}
	
	
	
}