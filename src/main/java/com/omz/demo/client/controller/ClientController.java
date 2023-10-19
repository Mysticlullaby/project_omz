package com.omz.demo.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.service.ClientService;

@CrossOrigin("*")
@RestController
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private BCryptPasswordEncoder encodePassword;
	
	public ClientController() {
	}
	
	// 회원가입
	@PostMapping("/signup")
	public String signup(@RequestBody ClientDTO clientDTO) {
		System.out.println("password:" + clientDTO.getClientPass());
		clientDTO.setClientPass(encodePassword.encode(clientDTO.getClientPass()));
		clientService.signupProcess(clientDTO);
		return null;
	}

	// 회원가입 중복체크
	@GetMapping("/signup/{clientId}")
	public boolean idDuplication(@PathVariable(value = "clientId") String clientId) {
		System.out.println("중복체크 아이디 : " + clientId);

		if (clientService.existsByClientId(clientId)) {
			return false;
		} else {
			return true;
		}
	}
			
	// 회원정보 가져오기
	@GetMapping("/editinfo/{clientId}")
	public ClientDTO getClient(@PathVariable("clientId") String clientId) {
		return clientService.updateProcess(clientId);
	}

	// 회원정보 수정
	@PostMapping("/update")
	public void update(@RequestBody ClientDTO clientDTO) {
		clientDTO.setClientPass(encodePassword.encode(clientDTO.getClientPass()));
		clientService.updateProcess(clientDTO);
	}
	
	// 회원이름 불러오기
	@GetMapping("/getClientName/{clientId}")
	public ClientDTO getClientNameExcute(@PathVariable("clientId") String clientId) {
		ClientDTO dto = clientService.getClientNameProcess(clientId);
		return dto;
	}

	// 회원탈퇴 폼
	@GetMapping("/delete/{clientId}")
	public String deleteForm(@PathVariable String clientId) {
		return "/login";
	}
	
	// 회원탈퇴 처리
	@PostMapping("/delete")
	public Long deleteByClientId(@RequestBody ClientDTO clientDTO) {
		String clientId = clientDTO.getClientId();
		System.out.println("탈퇴 아이디 : " + clientId);
		System.out.println("탈퇴회원이름 : " + clientDTO.getClientName());
		return clientService.deleteProcess(clientId);
	}

}
