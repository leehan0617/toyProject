package com.toy.security.dto;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : 권한 관련 테이블
 */
public class Authority{

	private String auth_code;
	private String auth_name;
	
	public String getAuth_code() {
		return auth_code;
	}
	
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getAuth_name() {
		return auth_name;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
}
