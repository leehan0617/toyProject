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
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원가입 로직을 수행하는 메소드
	 */
	@RequestMapping(value="/user/join" , method=RequestMethod.POST)
	public int join(@RequestBody UserDto userDto) {
		return userService.joinUser(userDto);
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원 정보를 가져오는 메소드
	 */
	@RequestMapping(value="/user/{userId}" , method=RequestMethod.GET)
	public UserDto getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}

	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원정보를 수정하는 메소드
	 */
	@RequestMapping(value="/user/update/{userId}" , method=RequestMethod.POST)
	public int update(@PathVariable String userId , @RequestBody UserDto userDto) {
		return userService.update(userDto);
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원을 삭제하는 메소드
	 */
	@RequestMapping(value="user/delete/{userId}" , method=RequestMethod.DELETE)
	public int delete(@PathVariable String userId) {
		return userService.delete(userId);
	}
}
