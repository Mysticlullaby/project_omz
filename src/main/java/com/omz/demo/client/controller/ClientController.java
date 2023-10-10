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
		System.out.println(clientId);

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
	
//	//회원가입 유효성
//		@PostMapping("/signup")
//		public String signup(@RequestBody @Valid ClientDTO clientDTO, Errors errors, Model model) {
//			
//			if(errors.hasErrors()) {
//				model.addAttribute("clientDTO : " + clientDTO);
//				Map<String, String> validateMap = new HashMap<>();
//				
//				for(FieldError error : errors.getFieldErrors()) {
//					String validKeyName = "valid : " + error.getField();
//					validateMap.put(validKeyName, error.getDefaultMessage());
//				}
//				
//				for(String key : validateMap.keySet()) {
//					model.addAttribute(key, validateMap.get(key));
//				}
//				return "/signup";			
//			}		
//			clientDTO.setClientPass(encodePassword.encode(clientDTO.getClientPass()));
//			clientService.signupProcess(clientDTO);
//			return null;
//		}

}
