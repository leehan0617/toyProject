package com.toy.user.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : 회원이 가지고있는 권한에 대한 DTO
 */
public class UserAuthority implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	
	private String user_id;
	private String auth_code;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	@Override
	public String getAuthority() {
		return this.user_id;
	}
}
