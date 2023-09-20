package com.omz.demo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.omz.demo.client.dto.AuthInfo;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.service.ClientService;

// http://localhost:3000/client/signup

@CrossOrigin("*")
@RestController
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	public ClientController() {
	}
	
	//회원가입   
	@PostMapping("/client/signup")
	public String signup(@RequestBody ClientDTO clientDTO) {
		clientDTO.setClientPass(clientDTO.getClientPass());
		AuthInfo authInfo = clientService.signupProcess(clientDTO);
		return null;
	}
	
	

}
