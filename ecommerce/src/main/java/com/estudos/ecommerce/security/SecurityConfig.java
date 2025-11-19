package com.estudos.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final UserDetailsService userDetailsService;
	private final AuthFilter authFilter;
	
	public SecurityConfig(UserDetailsService userDetailsService, AuthFilter authFilter) {
		this.userDetailsService = userDetailsService;
		this.authFilter = authFilter;
		
	}
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
			.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	@Bean
	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	 AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		
		return new ProviderManager(authProvider);
	}

}
