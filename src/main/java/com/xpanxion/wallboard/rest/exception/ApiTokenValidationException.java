package com.xpanxion.wallboard.rest.exception;

import org.springframework.security.core.AuthenticationException;

public class ApiTokenValidationException extends AuthenticationException {

	private static final long serialVersionUID = -2402471118006562682L;

	public ApiTokenValidationException(String msg) {
		super(msg);
	}
}
