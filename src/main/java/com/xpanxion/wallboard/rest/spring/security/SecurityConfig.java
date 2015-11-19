package com.xpanxion.wallboard.rest.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.xpanxion.wallboard.rest.spring.security.token.ApiTokenAuthenticationProvider;
import com.xpanxion.wallboard.rest.spring.security.token.StatelessTokenAuthenticationFilter;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
	
	@Bean
	public StatelessTokenAuthenticationFilter tokenAuthenticationFilter() {
		return new StatelessTokenAuthenticationFilter();
	}
	
	@Configuration
	public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
		@Autowired
		private ApiTokenAuthenticationProvider apiTokenAuthenticationProvider;
		
		@Autowired
		private StatelessTokenAuthenticationFilter tokenAuthenticationFilter;
		
		@Value("${management.security.role}")
		private String managementSecurityRole;
		
		@Value("${app.api.security.allowedroles}")
		private String apiSecurityAllowedRoles;
		
		@Autowired
		protected void configureGlobal(AuthenticationManagerBuilder auth) {
			auth.authenticationProvider(this.apiTokenAuthenticationProvider);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.antMatchers("/api/**").hasAnyRole(this.apiSecurityAllowedRoles)
					.antMatchers("/manage/**").hasAnyRole(this.managementSecurityRole)
					.anyRequest().permitAll()
					.and()
				.anonymous().disable()
				.csrf().disable()
				.logout().disable()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
				.addFilterBefore(this.tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		}
		
	}
	
}
