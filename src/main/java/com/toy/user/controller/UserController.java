package com.toy.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toy.user.model.UserDto;

@RestController
public class UserController {
	
	/**
	 * 작성일 : 2017. 3. 27.
	 * 작성자 : 이한빈
	 * 설  명 : 정보수정페이지 이동 메소드
	 */
	@RequestMapping(value="/user/{userId}" , method=RequestMethod.GET)
	public UserDto userUpdatePage(@PathVariable String userId) {
		UserDto user = null;
		
		return user;
	}
	
	@RequestMapping(value="/user/{userId}" , method=RequestMethod.POST)
	public UserDto userUpdate(@PathVariable String userId) {
		UserDto user = null;
		
		return user;
	}
}
