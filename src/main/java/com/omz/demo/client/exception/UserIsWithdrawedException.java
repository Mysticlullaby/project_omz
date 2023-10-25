package com.omz.demo.client.exception;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.InternalAuthenticationServiceException;


public class UserIsWithdrawedException extends AuthenticationException {

	public UserIsWithdrawedException(String message) {
		super(message);
	}

}
