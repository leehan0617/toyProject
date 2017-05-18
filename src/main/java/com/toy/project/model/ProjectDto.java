package com.toy.project.model;

import java.io.Serializable;
import java.util.List;

/**
 * 작성일 : 2017. 5. 16.
 * 작성자 : 김민지 
 * 설 명  : 프로젝트 관련  Dto
 */

@SuppressWarnings("serial")
public class ProjectDto implements Serializable{
	
	//프로젝트 키
	private int project_id;
	//프로젝트 명
	private String project_name;
	//프로젝트 시작 일자
	private String project_start_date;
	//프로젝트 종료 일자
	private String project_end_date;
	//프로젝트 참여 수
	private int usercount;
	//프로젝트 상세
	private String project_detail;
	//관리자
	private String manager_id;
	//관리자 이름
	private String manager_name;
	//프로젝트 상태
	private String state_code;
	//프로젝트 상태명
	private String state_name;
	//등록된 날짜
	private String reg_date;
	
	//모집 포지션코드 리스트
	private List depart_code;
	
	//모집 포지션 명
	private String depart_name;
	
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_start_date() {
		return project_start_date;
	}
	public void setProject_start_date(String project_start_date) {
		this.project_start_date = project_start_date;
	}
	public String getProject_end_date() {
		return project_end_date;
	}
	public void setProject_end_date(String project_end_date) {
		this.project_end_date = project_end_date;
	}
	public int getUsercount() {
		return usercount;
	}
	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}
	public String getProject_detail() {
		return project_detail;
	}
	public void setProject_detail(String project_detail) {
		this.project_detail = project_detail;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public List getDepart_code() {
		return depart_code;
	}
	public void setDepart_code(List depart_code) {
		this.depart_code = depart_code;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getDepart_name() {
		return depart_name;
	}
	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
}