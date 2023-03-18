package com.challenge.videos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration{

	
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	http
            .httpBasic()
            .and()
            .authorizeRequests()
    		.antMatchers(HttpMethod.GET, "/videos/free").permitAll()
			.antMatchers(HttpMethod.DELETE, "/categorias/*").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/videos/*").hasRole("ADMIN")
			 .anyRequest().authenticated()
             .and()
             .csrf().disable();
	        return http.build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	}

