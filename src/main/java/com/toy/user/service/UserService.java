package com.toy.user.service;

import org.springframework.security.access.prepost.PostAuthorize;

import com.toy.user.model.UserDto;

public interface UserService {

	public int joinUser(UserDto userDto);
	
	public UserDto getUser(String userId);

	@PostAuthorize("isAuthenticated() and returnObject.user_id == principal.username")
	public int update(UserDto userDto);

	@PostAuthorize("isAuthenticated() and returnObject.user_id == principal.username")
	public int delete(String userId);

}
