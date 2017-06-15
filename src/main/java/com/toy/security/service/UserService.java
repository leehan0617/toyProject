package com.toy.security.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.toy.security.model.CustomUser;

public interface UserService extends UserDetailsService {
	Collection<GrantedAuthority> getAuthorities(String username);

	public CustomUser join(CustomUser user);

	public CustomUser getUser(String username);

	public int update(CustomUser user);

	public int delete(String username);
}
