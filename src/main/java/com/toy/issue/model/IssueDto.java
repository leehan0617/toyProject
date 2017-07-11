package com.toy.issue.model;

import java.util.List;

public class IssueDto {
	private String issue_id;
	private String issue_name;
	private String project_id;
	private int project_num;
	private String issue_detail;
	private String reg_id;
	private String start_date;
	private String end_date;
	private String state_code;
	private String state_name;
	private String user_id;
	private String type;
	private List<String> userList;
	private int issue_num;
	private String his_date;
	private String state_search;
	private String projectName;
	private String rowNum;
	
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	//페이징 관련 추가
	private int page;
	private int count;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getState_search() {
		return state_search;
	}
	public void setState_search(String state_search) {
		this.state_search = state_search;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getHis_date() {
		return his_date;
	}
	public void setHis_date(String his_date) {
		this.his_date = his_date;
	}
	public int getIssue_num() {
		return issue_num;
	}
	public void setIssue_num(int issue_num) {
		this.issue_num = issue_num;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
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
	public int getProject_num() {
		return project_num;
	}
	public void setProject_num(int project_num) {
		this.project_num = project_num;
	}
	
}
