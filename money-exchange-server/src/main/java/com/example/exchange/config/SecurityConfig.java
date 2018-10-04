package com.example.exchange.config;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.exchange.config.jwt.JwtAuthenticationFilter;
import com.example.exchange.config.jwt.JwtAuthorizationFilter;
import com.example.exchange.config.jwt.JwtConfig;
import com.example.exchange.service.impl.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    DataSource dataSource;
	
	@Autowired
    JwtConfig jwtConfig;
	
	@Autowired
    ObjectMapper objectMapper;
	
	@Autowired
	UsersService usersService;
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(usersService);
	}
	
	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOrigin("*");
//	    config.addExposedHeader(jwtConfig.getHeader());
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("HEAD");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("POST");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
	    	.csrf().disable()
	    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    		.and()
	    	.exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
	    		.and()
	    	.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtConfig, objectMapper))
	    	.addFilterAfter(new JwtAuthorizationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
	    	.authorizeRequests()
	    		.antMatchers(jwtConfig.getUri())
	    			.permitAll()  
	    		.antMatchers("/encode/**")
	    			.permitAll()
	    		.anyRequest()
	    			.authenticated()
	    	;
		
	}
	

}
