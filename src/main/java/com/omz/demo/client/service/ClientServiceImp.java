package com.omz.demo.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.client.dto.AuthInfo;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.client.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class ClientServiceImp implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	public ClientServiceImp() {
	}
	
	// signup
	public AuthInfo signupProcess(ClientDTO dto) {
		ClientEntity entity = ClientDTO.toEntity(dto);
		clientRepository.save(entity);
		return new AuthInfo(dto.getClientId(), dto.getClientPass());
	}

	// login
	public AuthInfo loginProcess(ClientDTO dto) {
		ClientEntity clientEntity = clientRepository.findByClientId(dto.getClientId());
//		ClientDTO clientDTO = ClientDTO.toDto(clientEntity);
		return new AuthInfo(clientEntity.getClientId(), clientEntity.getClientPass());
	}

	@Override
	public ClientDTO updateProcess(String clientId) {
		ClientEntity clientEntity = clientRepository.findByClientId(clientId);
		return ClientDTO.toDto(clientEntity);
	}

	@Override
	public AuthInfo updateProcess(ClientDTO dto) {
		ClientEntity entity = ClientDTO.toEntity(dto);
		clientRepository.save(entity);
		return new AuthInfo(dto.getClientId(), dto.getClientName(), dto.getClientPass());
	}

	@Override
	public AuthInfo deleteProcess(ClientDTO dto) {
		ClientEntity entity = ClientDTO.toEntity(dto);
		clientRepository.delete(entity);
		return null;
	}
	

}
