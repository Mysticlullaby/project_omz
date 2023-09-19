package com.omz.demo.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public ClientController() {
	}
	
	@GetMapping("/client/list")
	public List<ClientDTO> listExecute(){
		return clientService.listProcess();
	}
	
	//회원가입
	@PostMapping("/client/signup")
	public String signup(@RequestBody ClientDTO clientDTO) {
		clientDTO.setClientPass()
		
		return null;
	}
	
	

}
