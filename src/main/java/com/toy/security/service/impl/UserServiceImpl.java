package com.toy.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toy.security.dao.SecurityDao;
import com.toy.security.model.CustomUser;
import com.toy.security.service.UserService;

/**
 * 작성일 : 2017. 6. 9.
 * 작성자 : 이한빈
 * 설  명 : 실제 로그인이 수행되는 서비스
 */
@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private SecurityDao securityDao;
	
	/**
	 * 작성일 : 2017. 6. 9.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인할때 계정정보를 확인하는 메소드 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("UserDetailsServiceImpl loadUserByUsername 접근");
		
		CustomUser user = securityDao.getUser(username);
		user.setAuthorities(this.getAuthorities(username));
		return user;
	}
	
	/**
	 * 작성일 : 2017. 6. 9.
	 * 작성자 : 이한빈
	 * 설  명 : 사용자 id에 대한 권한 정보를 세팅해주는 메소드
	 */
	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<String> stringAuthorities = securityDao.getAuthorities(username);
		List<GrantedAuthority> authorities = new ArrayList<> ();
		
		for(String element : stringAuthorities) {
			logger.info("auth : {} " , element);
			authorities.add(new SimpleGrantedAuthority(element));
		}
		
		return authorities;
	}
	
}
