package com.omz.demo.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.client.dto.AuthInfo;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.client.exception.WrongIdPasswordException;
import com.omz.demo.client.repository.ClientRepository;

@Service
@Transactional
public class ClientServiceImp implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	public ClientServiceImp() {
	}
	
	public AuthInfo signupProcess(ClientDTO dto) {
		ClientEntity entity = ClientDTO.toEntity(dto);
		clientRepository.save(entity);
		return new AuthInfo(dto.getClientId(), dto.getClientPass());
	}
	
	// 회원가입 중복확인
	@Override
	public boolean existsByClientId(String clientId) {
		return clientRepository.existsByClientId(clientId);
	}

	@Override
	public AuthInfo loginProcess(ClientDTO dto) {
		ClientEntity clientEntity = clientRepository.findByClientId(dto.getClientId());
		ClientDTO clientDTO = ClientDTO.toDto(clientEntity);
		
//		System.out.println("아이디" + dto.getClientId());
		
		if(clientEntity == null) {
			System.out.println("회원이 아닙니다.");
			throw new WrongIdPasswordException();
		}
		
		if(!clientDTO.matchPassword(dto.getClientPass())) {
			System.out.println("비밀번호가 다릅니다.");
			throw new WrongIdPasswordException();
		}
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
	public ClientEntity loginProcess(String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
