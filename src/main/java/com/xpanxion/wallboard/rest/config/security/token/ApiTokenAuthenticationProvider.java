package com.xpanxion.wallboard.rest.config.security.token;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.xpanxion.wallboard.rest.dto.system.ApiToken;
import com.xpanxion.wallboard.rest.exception.ApiTokenValidationException;

@Component
public class ApiTokenAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private ApiTokenService apiTokenService;

	@Value("${app.api.security.authentication.header.validationpattern}")
	private String appApiAuthenticationHeaderValidationpattern;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		final TokenAuthentication tokenAuth = (TokenAuthentication) authentication;
		final String token = authentication.getName();
		
		if (null == token) {
			throw new ApiTokenValidationException("No token was found in the request.");
		}
		
		if (!this.isValidToken(token)) {
			throw new ApiTokenValidationException("Invalid token format for '" + token + "'.");
		}
		
		final ApiToken apiToken = this.apiTokenService.getToken(token);
		
    	if (null == apiToken) {
			throw new ApiTokenValidationException("Token '" + token + "' was not found in the system.");
		}
		
		if (!apiToken.getEnabled()) {
			throw new ApiTokenValidationException("Token '" + token + "' is disabled.");
		}
		
		if (apiToken.getLocked()) {
			throw new ApiTokenValidationException("Token '" + token + "' is locked.");
		}
		
		tokenAuth.setApiToken(apiToken);
		
		return tokenAuth;
	
	}
	
	public boolean isValidToken(String token) {
		final Pattern pattern = Pattern.compile(this.appApiAuthenticationHeaderValidationpattern);
		return pattern.matcher(token).matches();
	}
	

	@Override
	public boolean supports(Class<?> authentication) {
		return TokenAuthentication.class.isAssignableFrom(authentication);
	}

}
