package com.toy.issue.model;

public class issueDto {
	private String issue_id;
	private String issue_name;
	private String project_id;
	private String issue_detail;
	
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
