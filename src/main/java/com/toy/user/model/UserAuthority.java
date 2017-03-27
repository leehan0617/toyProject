package com.toy.user.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.toy.security.dto.Authority;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : 회원이 가지고있는 권한에 대한 DTO
 */
public class UserAuthority implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	
	private String user_id;
	private List<Authority> authorityList;
	
	@Override
	public String getAuthority() {
		return this.user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}
}
