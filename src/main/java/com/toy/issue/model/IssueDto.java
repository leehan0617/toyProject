package com.toy.issue.model;

public class IssueDto {
	private String issue_id;
	private String issue_name;
	private String project_id;
	private String issue_detail;
	private String reg_id;
	private String issue_start_date;
	private String issue_end_date;
	private String state_code;
	private String state_name;
	
	
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getIssue_start_date() {
		return issue_start_date;
	}
	public void setIssue_start_date(String issue_start_date) {
		this.issue_start_date = issue_start_date;
	}
	public String getIssue_end_date() {
		return issue_end_date;
	}
	public void setIssue_end_date(String issue_end_date) {
		this.issue_end_date = issue_end_date;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getIssue_detail() {
		return issue_detail;
	}
	public void setIssue_detail(String issue_detail) {
		this.issue_detail = issue_detail;
	}
	public String getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
	}
	public String getIssue_name() {
		return issue_name;
	}
	public void setIssue_name(String issue_name) {
		this.issue_name = issue_name;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	
}
