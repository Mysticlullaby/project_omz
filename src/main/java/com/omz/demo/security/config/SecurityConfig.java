package com.omz.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.omz.demo.client.repository.ClientRepository;

public class SecurityConfig {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}

}
