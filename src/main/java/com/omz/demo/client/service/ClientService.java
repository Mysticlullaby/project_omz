package com.omz.demo.client.service;

import org.springframework.stereotype.Component;

import com.omz.demo.client.dto.AuthInfo;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;

@Component
public interface ClientService {
	
	public AuthInfo signupProcess(ClientDTO dto);
	public boolean existsByClientId(String clientId);
	public ClientDTO updateProcess(String clientId);
	public AuthInfo updateProcess(ClientDTO dto);	
	public ClientDTO getClientNameProcess(String clientId); //clientId를 넘기면 dto를 가져온다
	public Long deleteProcess(String clientId);
//	public void delete(ClientEntity clientEntity);
//	boolean deleteProcess(ClientEntity clientEntity) throws Exception;
	
//	public AuthInfo loginProcess(ClientDTO dto);
}
