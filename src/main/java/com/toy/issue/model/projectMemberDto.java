package com.toy.issue.model;

import java.util.List;

import com.toy.user.model.UserDto;

public class projectMemberDto {
	private String project_id;
	private List<String> memberList;
	private String  state_code;
	private String  depart_detail;
	private String  reg_date;
	private String  reg_id;
	private String  mod_date;
	private String  mod_id;
	private String project_name;
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public List<String> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<String> memberList) {
		this.memberList = memberList;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getDepart_detail() {
		return depart_detail;
	}
	public void setDepart_detail(String depart_detail) {
		this.depart_detail = depart_detail;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
}
