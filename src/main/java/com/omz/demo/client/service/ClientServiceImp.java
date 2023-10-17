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
	
	// 회원가입
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

	// 회원정보 가져오기
	@Override
	public ClientDTO updateProcess(String clientId) {
		ClientEntity clientEntity = clientRepository.findByClientId(clientId);
		return ClientDTO.toDto(clientEntity);
	}

	// 회원정보 수정
	@Override
	public AuthInfo updateProcess(ClientDTO dto) {
		ClientEntity entity = ClientDTO.toEntity(dto);
		clientRepository.save(entity);
		return new AuthInfo(dto.getClientId(), dto.getClientName(), dto.getClientPass());
	}

	// 회원이름 불러오기
	@Override
	public ClientDTO getClientNameProcess(String clientId) {
		ClientEntity clientEntity = clientRepository.findByClientId(clientId);
		return ClientDTO.toDto(clientEntity); // 결과값을 entity에 담아오는데 dto로 변환해서 가져오려고 함
	}

	// 회원탈퇴
	@Override
	public Long deleteProcess(String clientId) {
		return clientRepository.deleteByClientId(clientId);
	}
	
//	@Override
//	public void delete(ClientEntity clientEntity) {
//		clientRepository.delete(clientEntity);
//	}
	
//	@Override
//	public boolean deleteProcess(ClientEntity clientEntity) throws Exception {
//		clientRepository.delete(clientEntity);
//		boolean res = true;
//		return res;
//	}	
	
//	@Override
//	public AuthInfo loginProcess(ClientDTO dto) {
//		ClientEntity clientEntity = clientRepository.findByClientId(dto.getClientId());
//		ClientDTO clientDTO = ClientDTO.toDto(clientEntity);
//		
////		System.out.println("아이디" + dto.getClientId());
//		
//		if(clientEntity == null) {
//			System.out.println("회원이 아닙니다.");
//			throw new WrongIdPasswordException();
//		}
//		
//		if(!clientDTO.matchPassword(dto.getClientPass())) {
//			System.out.println("비밀번호가 다릅니다.");
//			throw new WrongIdPasswordException();
//		}
//		return new AuthInfo(clientEntity.getClientId(), clientEntity.getClientPass());
//	}
	

}
