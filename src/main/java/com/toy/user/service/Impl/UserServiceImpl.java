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
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : user에 관한 정보를 가져오는 메소드
	 */
	@Override
	public UserDto loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserDto user = userDao.getUser(userId);
		List<UserAuthority> authorities = userDao.getAuthorities(userId);
		user.setAuthorities(authorities);
		
		return user;
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : user 에 관한 권한 및 인증을 하는 메소드
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		UserDto user;
		Collection<? extends GrantedAuthority> authorities;
		
		try {
			user = this.loadUserByUsername(userId);
			String bcryptPassword = bcryptEncoder.encode(password);
			
			System.out.println(bcryptEncoder.matches(password, bcryptPassword));
			
			if(!bcryptEncoder.matches(password ,user.getPassword())) {
				throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
			};
			
			authorities = user.getAuthorities();
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
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원가입을 실행하는 메소드
	 */
	@Override
	public int joinUser(UserDto userDto) {
		userDto.setPassword(bcryptEncoder.encode(userDto.getPassword()));
		return userDao.joinUser(userDto);
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : user 정보를 가져오는 메소드
	 */
	@Override
	public UserDto getUser(String userId) {
		return userDao.getUser(userId);
	}

	@Override
	public int update(UserDto userDto) {
		userDto.setPassword(bcryptEncoder.encode(userDto.getPassword()));
		return userDao.update(userDto);
	}
}
