package com.omz.demo.client.exception;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Component
@ControllerAdvice
public class ClientLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String errorMsg;
		if (exception instanceof UsernameNotFoundException) {
			errorMsg = "아이디가 XXXX";
//		} else if (exception instanceof BadCredentialsException) {
//			errorMsg = "아이디 또는 비밀번호가 맞지 않습니다.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			errorMsg = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다.";
		} else if (exception instanceof UsernameNotFoundException) {
			errorMsg = "가입정보가 없습니다. 회원가입 후 로그인 해주세요";
		} else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			errorMsg = "인증 요청이 거부되었습니다.";
		} else {
			errorMsg = "알 수 없는 이유로 로그인에 실패하였습니다.";
		}

		errorMsg = URLEncoder.encode(errorMsg, "UTF-8");
		setDefaultFailureUrl("보여줄 에러메시지 : " + errorMsg);

		super.onAuthenticationFailure(request, response, exception);
	}

}
