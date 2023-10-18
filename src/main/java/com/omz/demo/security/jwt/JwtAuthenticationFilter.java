package com.omz.demo.security.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.security.service.PrincipalDetails;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter => login 요청 처리를 시작함");
		
		try {
			ObjectMapper om = new ObjectMapper();
			ClientDTO user = om.readValue(request.getInputStream(), ClientDTO.class);
			System.out.printf("clientId : %s, clientPass : %s\n", user.getClientId(), user.getClientPass());
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getClientId(), user.getClientPass());
			
			Authentication authentication = authManager.authenticate(authToken);
			
			System.out.println("authentication : " + authentication.getPrincipal());
			
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.printf("로그인 인증완료 : %s %s\n", principalDetails.getUsername(), principalDetails.getPassword());
			
			return authentication;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		System.out.println("successfulAuthentication 실행됨");
		
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		String jwtToken = JWT.create().withSubject("mycors")
				.withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 60 * 1L)))
				.withClaim("clientPass", principalDetails.getClientDTO().getClientPass())
				.withClaim("authRole", principalDetails.getClientDTO().getAuthRole())
				.withClaim("clientId", principalDetails.getClientDTO().getClientId())
				.sign(Algorithm.HMAC512("mySecurityCos"));
		
		System.out.println("jwtToken : " + jwtToken);
		
		response.addHeader("Authorization", "Bearer" + jwtToken);
		
		// localstorage에 담아주는 곳
		final Map<String, Object> body = new HashMap<String, Object>();
		body.put("clientId", principalDetails.getClientDTO().getClientId());
		body.put("clientName", principalDetails.getClientDTO().getClientName());
//		body.put("mbti", principalDetails.getClientDTO().getMbti());
		body.put("authRole", principalDetails.getClientDTO().getAuthRole());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println(failed);
		System.out.println("unsuccessfulAuthentication 실행됨");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("code", HttpStatus.UNAUTHORIZED.value());
		body.put("error", failed.getMessage());
		
		new ObjectMapper().writeValue(response.getOutputStream(), body);
	}
	
}
