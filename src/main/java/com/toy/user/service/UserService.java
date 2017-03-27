package com.toy.user.service;

import com.toy.user.model.UserDto;

public interface UserService {

	public int joinUser(UserDto userDto);

	public UserDto getUser(String userId);

	public int update(UserDto userDto);

}
