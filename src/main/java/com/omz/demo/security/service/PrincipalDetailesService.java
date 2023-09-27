package com.omz.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.client.repository.ClientRepository;

// 시큐리티가 /login 주소 요청이 오면 중간에서 낚아채서(intercept) 로그인을 진행시킴
// 로그인 진행이 완료되면 여러 session 중에서 security 만의 독자적인 session을 만들어줌(Security ContextHolder)
// 해당 세션에 들어갈 수 있는 오브젝트 타입은 Authentication 타입 객체임 (Authentication 객체 안에 user 정보가 있어야 함)
// user 오브젝트 타입은 userDetails 타입 객체로 넣도록 클래스가 지정되어 있음
// Security Session에 세션정보를 저장해주는데 들어갈 수 있는 객체는 Authentication에 User 정보가 들어가는게 UserDetails 타입임
// Security Session에 있는 세션 정보를 꺼내면 Authentication 객체가 나오고 Authentication에서 UserDetails 객체를 꺼내면 UserObject에 접근가능
// 그렇기 떄문에 해당 객체를 꺼내려면 UserDetails 상속받고 PrincipalDetailesService가 UserDetails와 같은 타입이 되면서 Authentication에 넣을 수 있게 됨
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
		System.out.println("client:"+clientEntity.getClientId());		
		
		if(clientEntity == null) {
			throw new UsernameNotFoundException(clientId);
		} // 컴파일러에서 해당 조건문이 참, 거짓으로 실행되지 않아 dead code로 분류됨
		
		return new PrincipalDetails(ClientDTO.toDto(clientEntity));
	}
	
}
