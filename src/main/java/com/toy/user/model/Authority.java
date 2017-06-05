package com.toy.user.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	private String authCode;
	
	@Override
	public String getAuthority() {
		return authCode;
	}
}
