package com.example.exchange.config.jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtAuthorizationFilter extends  OncePerRequestFilter {
    
	private final JwtConfig jwtConfig;
	
	public JwtAuthorizationFilter(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		String header = request.getHeader(jwtConfig.getHeader());
		
		if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
			chain.doFilter(request, response); 
			return;
		}
		
		String token = header.replace(jwtConfig.getPrefix(), "");
		
		try {
			
			//validation
			Claims claims = Jwts.parser()
					.setSigningKey(jwtConfig.getSecret().getBytes())
					.parseClaimsJws(token)
					.getBody();
			
			String username = claims.getSubject();
			if(username != null) {
				List<String> authorities = (List<String>) claims.get("authorities");
				
				 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
								 username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
				 
				 SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
		}
		
		chain.doFilter(request, response);
	}

}
