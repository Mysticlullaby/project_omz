package com.omz.demo.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.client.repository.ClientRepository;
import com.omz.demo.security.service.PrincipalDetails;

public class JwtAutorizationFilter extends BasicAuthenticationFilter{
	private ClientRepository userRepository;
	
	public JwtAutorizationFilter(AuthenticationManager authManager, ClientRepository userRepository) {
		super(authManager);
		this.userRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	throws IOException, ServletException{
		System.out.println("인가가 필요한 주소 요청이 실행되는 메소드 : doFilterInternal()" );
		
		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader : " + jwtHeader);
		
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		String jwtToken = request.getHeader("Authorization").replace("Bearer", "");
		String userName = JWT.require(Algorithm.HMAC512("mySecurityCos")).build().verify(jwtToken).getClaim("clientId").asString();
		System.out.println("username : " + userName);
		
		if(userName != null) {
			ClientEntity clientEntity = userRepository.findByClientId(userName);
			ClientDTO user = ClientDTO.toDto(clientEntity);
			PrincipalDetails principalDetails = new PrincipalDetails(user);
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);			
		}
		chain.doFilter(request, response);		
	}
	
}
