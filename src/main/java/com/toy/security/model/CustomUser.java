package com.toy.security.model;

import java.time.LocalDate;

public class CustomUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String departCode;
	private transient LocalDate regDate;
	private transient LocalDate modDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	public LocalDate getModDate() {
		return modDate;
	}
	public void setModDate(LocalDate modDate) {
		this.modDate = modDate;
	}
}
