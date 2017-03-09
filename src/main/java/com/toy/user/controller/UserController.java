package com.toy.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.user.model.UserDto;

@RestController
public class UserController {
	
	@RequestMapping("/user/{userId}")
	public UserDto message(@PathVariable String userId) {
		UserDto user = new UserDto("hanbin" , userId);
		
		return user;
	}
}
