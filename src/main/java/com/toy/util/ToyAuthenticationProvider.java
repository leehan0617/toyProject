package com.toy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ToyAuthenticationProvider implements AuthenticationProvider{
	
	private static final Logger logger = LoggerFactory.getLogger(ToyAuthenticationProvider.class);
	
	@Override
	public boolean supports(Class<?> authentication) {
		logger.info("ToyAuthenticationProvider - supports 메소드 접근");
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("ToyAuthenticationProvider - authenticate 메소드 접근");
		String id = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		logger.info("id:" + id + ", password :" + password);
		
		return null;
	}
}
