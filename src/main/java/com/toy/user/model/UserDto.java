package com.toy.user.model;

import java.time.LocalDate;
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
	private List<Authority> authorities;
	private boolean accountNonExpired = true; 
	private boolean accountNonLocked = true; 
	private boolean credentialsNonExpired = true; 
	private int enabled;
	
	private String user_name;
	private String email;
	private String depart_code;
	private LocalDate reg_date;
	private LocalDate mod_date;
	
	public UserDto() {
		// default 생성자
	}
	
	public UserDto(String id , String password) {
		this.user_id = id;
		this.password = password;
	}
	
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
	public List<Authority> getAuthorities() {
		return this.authorities;
	}
	
	public void setAuthorities(List<Authority> authorities) {
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
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepart_code() {
		return depart_code;
	}

	public void setDepart_code(String depart_code) {
		this.depart_code = depart_code;
	}

	public LocalDate getReg_date() {
		return reg_date;
	}

	public void setReg_date(LocalDate reg_date) {
		this.reg_date = reg_date;
	}

	public LocalDate getMod_date() {
		return mod_date;
	}

	public void setMod_date(LocalDate mod_date) {
		this.mod_date = mod_date;
	}
}
