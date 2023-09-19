package com.omz.demo.client.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public List<ClientDTO> listProcess(){
		List<ClientDTO> dtoList = new ArrayList<>();
		List<ClientEntity> entityList = clientRepository.findAll();
		
		for(ClientEntity entity : entityList) {
			dtoList.add(ClientDTO.toDto(entity));
		}
		return dtoList;
	}
	
	//signup
	public AuthInfo signupProcess(ClientDTO dto) {
		ClientEntity entity = ClientDTO.toEntity(dto);
		clientRepository.save(entity);
		return new AuthInfo(dto.getClientId(), dto.getClientPass(), dto.getClientName());
	}
	
	
//	//login
//	public String loginProcess(ClientDTO dto) {
//		ClientEntity clientEntity = clientRepository.findByClientId(dto.getClientId());
//	}
	
	

}
