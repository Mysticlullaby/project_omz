package com.omz.demo.client.service;

import com.omz.demo.client.dto.AuthInfo;
import com.omz.demo.client.dto.ClientDTO;

public interface ClientService {
	
	public AuthInfo signupProcess(ClientDTO dto);
	public AuthInfo loginProcess(ClientDTO dto);
	public ClientDTO updateProcess(String clientId);
	public AuthInfo updateProcess(ClientDTO dto);
	public boolean existsByClientId(String clientId);
}
