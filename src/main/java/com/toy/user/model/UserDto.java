package com.toy.user.model;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : User 에 관한 테이블
 */
public class UserDto implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private String user_id;
	private String password;
	private List<UserAuthority> authorities;
	private boolean accountNonExpired = true; 
	private boolean accountNonLocked = true; 
	private boolean credentialsNonExpired = true; 
	private int enabled;
	
	@Override
	public String getUsername() {
		return user_id;
	}
	
	public void setUserName(String user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public List<UserAuthority> getAuthorities() {
		return this.authorities;
	}
	
	public void setAuthorities(List<UserAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}
	
	@Override
	public boolean isEnabled() {
		return (this.enabled == 1 ? true : false);
	}
	
}
