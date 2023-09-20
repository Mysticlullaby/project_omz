package com.omz.demo.client.service;

import com.omz.demo.client.dto.AuthInfo;
import com.omz.demo.client.dto.ClientDTO;

public interface ClientService {
	
	public AuthInfo signupProcess(ClientDTO dto);
	
}
