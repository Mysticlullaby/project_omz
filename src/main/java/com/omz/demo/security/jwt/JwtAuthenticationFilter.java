package com.omz.demo.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.client.repository.ClientRepository;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	private ClientRepository repository;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ClientRepository repository) {
		super(authenticationManager);
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("인가가 필요한 주소 요청이 실행되는 메소드 : doFilterInternal()");

		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader : " + jwtHeader);

		if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		String jwtToken = request.getHeader("Authorization").replace("Bearer", "");
		String username = JWT.require(Algorithm.HMAC512("mySecurityCos")).build().verify(jwtToken).getClaim("clientId").asString();
		System.out.println("username : " + username);
		
		if(username != null) {
			ClientEntity clientEntity = repository.findByClientId(username);
			ClientDTO dto = ClientDTO.toDto(clientEntity);
			
		}
	}

}
