package com.toy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toy.user.model.UserDto;
import com.toy.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/join" , method=RequestMethod.POST)
	public int join(@RequestBody UserDto userDto) {
		return userService.joinUser(userDto);
	}
	
	@RequestMapping(value="/user/{userId}" , method=RequestMethod.GET)
	public UserDto getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}

	@RequestMapping(value="/{userId}/update" , method=RequestMethod.POST)
	public int update(@PathVariable String userId , @RequestBody UserDto userDto) {
		return userService.update(userDto);
	}
}
