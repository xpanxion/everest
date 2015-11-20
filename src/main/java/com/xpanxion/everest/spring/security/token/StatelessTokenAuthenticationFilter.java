package com.xpanxion.everest.spring.security.token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
public class StatelessTokenAuthenticationFilter extends GenericFilterBean {

	@Value("${app.api.security.authentication.header.name}")
	private String appApiAuthenticationHeadername; 
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest) {
			final String token = this.retrieveToken((HttpServletRequest) request);
			SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(token));
		}
		
		chain.doFilter(request, response);
	}
	
	private String retrieveToken(HttpServletRequest request) {
		return request.getHeader(this.appApiAuthenticationHeadername);
	}
}
