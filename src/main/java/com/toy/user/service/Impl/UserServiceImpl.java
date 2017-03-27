package com.toy.user.service.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toy.user.model.UserDto;
import com.toy.user.service.UserService;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : user 에 관한 service 
 */
@Service
public class UserServiceImpl implements UserService , UserDetailsService{
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDto user = new UserDto();
		user.setUser_name("test");
		
		return null;
	}
}
