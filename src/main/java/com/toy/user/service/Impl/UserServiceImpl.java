package com.toy.user.service.Impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.toy.user.model.UserAuthority;
import com.toy.user.model.UserDto;
import com.toy.user.service.UserService;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : user 에 관한 service 
 */
@Service(value="userServiceImpl")
public class UserServiceImpl implements UserService , UserDetailsService , AuthenticationProvider{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDto loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserDto user = userDao.getUser(userId);
		List<UserAuthority> authorities = userDao.getAuthorities(userId);
		user.setAuthorities(authorities);
		
		return user;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		UserDto user;
		Collection<? extends GrantedAuthority> authorities;
		
		try {
			user = this.loadUserByUsername(userId);
			String bcryptPassword = bcryptEncoder.encode(password);
			
			System.out.println("password : " + password);
			System.out.println("bcryptPassword : " + bcryptPassword);
			
			if(!bcryptEncoder.matches(password ,user.getPassword())) {
				throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
			};
			
			System.out.println("test0" + user.getAuthorities());
			System.out.println("test0" + user.getAuthorities().get(0));
			System.out.println("test00" + user.getAuthorities().size());
			System.out.println("test6.5");
			System.out.println("test1" + user.getAuthorities().get(0).getAuthority());
			user.getAuthorities().get(0).getAuthority(); // string
			System.out.println("check8");
			System.out.println("test2 : " + user.getAuthorities());
			System.out.println("check9");
			authorities = user.getAuthorities();
			System.out.println("check10");
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return new UsernamePasswordAuthenticationToken(user , password , authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
}
