package com.omz.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.client.repository.ClientRepository;

@Service
public class PrincipalDetailesService implements UserDetailsService{

	@Autowired
	private ClientRepository clientRepository;
	
	public PrincipalDetailesService() {
	}

	// 사용자가 존재하지 않는 경우 예외처리
	@Override
	public UserDetails loadUserByUsername(String clientId) throws UsernameNotFoundException {
		ClientEntity clientEntity = clientRepository.findByClientId(clientId);
//		System.out.println("아이디 : "+clientEntity.getClientId());		

		if(clientEntity == null) {
			throw new UsernameNotFoundException(clientId);
		} // 컴파일러에서 해당 조건문이 참, 거짓으로 실행되지 않아 dead code로 분류됨
		
		return new PrincipalDetails(ClientDTO.toDto(clientEntity));
	}
	
}
