package com.omz.demo.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.client.dto.AuthInfo;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.client.repository.ClientRepository;

@Service
@Transactional
public class ClientServiceImp implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	public ClientServiceImp() {
	}
	
	//signup
		public AuthInfo signupProcess(ClientDTO dto) {
			ClientEntity entity = ClientDTO.toEntity(dto);
//			System.out.println(entity.getClientId() + ", " + entity.getClientName());
			clientRepository.save(entity);
			return new AuthInfo(dto.getClientId(), dto.getClientPass());
		}

	//login
	public AuthInfo loginProcess(ClientDTO dto) {
		ClientEntity clientEntity = clientRepository.findByClientId(dto.getClientId());
		ClientDTO clientDTO = ClientDTO.toDto(clientEntity);
		System.out.println(dto.getClientId() + ", " + dto.getClientName());
		return new AuthInfo(clientEntity.getClientId(), clientEntity.getClientPass());
	}
	
	@Override
	public ClientDTO updateProcess(String clinetId) {
		ClientEntity clientEntity = clientRepository.findByClientId(clinetId);		
		return ClientDTO.toDto(clientEntity);
	}
	
	@Override
	public AuthInfo updateProcess(ClientDTO dto) {
		ClientEntity entity = ClientDTO.toEntity(dto);
		clientRepository.save(entity);
		return new AuthInfo(dto.getClientId(), dto.getClientPass());
	}
	
	
	
	

}
