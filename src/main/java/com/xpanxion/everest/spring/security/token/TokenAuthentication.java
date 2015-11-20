package com.xpanxion.everest.spring.security.token;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.xpanxion.everest.dto.system.ApiToken;

public class TokenAuthentication implements Authentication {

	private static final long serialVersionUID = -1275805666692301591L;

	private ApiToken apiToken;
	
	private final String rawToken;
	
	public TokenAuthentication(final String rawToken) {
		this.rawToken = rawToken;
	}
	
	@Override
	public String getName() {
		return this.rawToken;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (null == this.apiToken) {
			return new ArrayList<>();
		}
		return this.apiToken.getRoles();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.apiToken;
	}

	@Override
	public boolean isAuthenticated() {
		return null != this.apiToken;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (!isAuthenticated) {
			this.apiToken = null;
		} else {
			throw new IllegalArgumentException("Manually setting the authenticated status is not supported.");
		}
	}

	public ApiToken getApiToken() {
		return apiToken;
	}

	public void setApiToken(ApiToken apiToken) {
		this.apiToken = apiToken;
	}

	public String getRawToken() {
		return rawToken;
	}
}
