package com.toy.user.model;

public class MemberDto {
	
	private String user_id; //id
	private String user_name;//이름
	private String password;//패스워드
	private String auth_name;//권한 명
	private Boolean enabled;//계정활성화
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuth_name() {
		return auth_name;
	}

	public String setAuth_name(String auth_name) {
		return this.auth_name = auth_name;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
