package com.omz.demo.client.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.omz.demo.client.dto.AuthInfo;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.client.exception.WrongIdPasswordException;
import com.omz.demo.client.repository.ClientRepository;
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
	
//	@GetMapping("/delete/{clientId}")
//	public String deleteById(@PathVariable String clientId) {
//		return null;
//		
//	}
	
	
	
//	@GetMapping("/idcheck/{clientId}")
//	public ClientEntity idCheckExecute(@PathVariable("clientId") String clientId, ClientDTO dto) {
//		System.out.println("idcheck 메소드가 호출되었습니다. 체크해야 할 아이디는 : " + clientId);
//		
//		ClientEntity clientEntity = clientService.loginProcess(dto.getClientId());
//		ClientDTO clientDTO = ClientDTO.toDto(clientEntity);
//
//		if (clientId == null) {
//			System.out.println("회원이 아닙니다.");
//			throw new WrongIdPasswordException();
//		}
//		return clientService.loginProcess(clientId);
//	}
//
	
//	@GetMapping("/stranger")
//	public String stranger() {
//		return "stranger";
//	}
//	
//	@GetMapping("/member")
//	public String member() {
//		return "member";
//	}
//	
//	@GetMapping("/admin")
//	public String admin() {
//		return "admin";
//	}
//	
//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}
	
	@GetMapping("/callName/{clientId}")
	public ClientDTO callNameExcute(@PathVariable ("clientId") String clientId) {
		ClientDTO dto = clientService.callNameProcess(clientId);
		return dto;
	}
	


}
