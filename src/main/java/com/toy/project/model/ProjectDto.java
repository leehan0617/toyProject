package com.toy.project.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
	//프로젝트 모집 시작 일자
	private String recruit_start_date;
	//프로젝트 모집 종료 일자
	private String recruit_end_date;
	//프로젝트 참여 수
	private int usercount;
	//프로젝트 상세
	private String project_detail;
	//관리자
	private String manager_id;
	//관리자 이름
	private String manager_name;
	//프로젝트 상태
	private String project_state_code;
	//프로젝트 상태명
	private String project_state_name;
	//프로젝트 모집상태
	private String recruit_state_code;
	//프로젝트 모집상태명
	private String recruit_state_name;
	//상태
	private String state_code;
	//상태명
	private String state_name;

	//등록된 날짜
	private String reg_date;
	
	//모집 포지션코드 리스트
	private List<String> departCodeList;
	
	//모집 상태코드 리스트
	private List<String> recruitCodeList;
	
	//프로젝트 상태코드 리스트
	private List<String> projectCodeList;
	
	//모집 포지션코드 
	private String depart_code;
		
	//모집 포지션 명
	private String depart_name;
	
	//등록한 id
	private String reg_id;
	
	//사용자 id
	private String user_id;
	
	//사용자 이름 
	private String user_name;
	
	//수정한 id
	private String mod_id;
		
	//모집 포지션 별로 인원수
	private Map<String,String> departMap;
	
	//상세직무설명
	private String depart_detail;
	
	//프로젝트별 신청여부
	private String applyyn;
	
	//프로젝트 코드 type
	private String type;
	
	//프로젝트 recruit_history 테이블
	private String his_date;
	
	//프로젝트 history 테이블
	private String project_his_date;
	
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
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getProject_state_code() {
		return project_state_code;
	}
	public void setProject_state_code(String project_state_code) {
		this.project_state_code = project_state_code;
	}
	public String getProject_state_name() {
		return project_state_name;
	}
	public void setProject_state_name(String project_state_name) {
		this.project_state_name = project_state_name;
	}
	public String getRecruit_state_code() {
		return recruit_state_code;
	}
	public void setRecruit_state_code(String recruit_state_code) {
		this.recruit_state_code = recruit_state_code;
	}
	public String getRecruit_state_name() {
		return recruit_state_name;
	}
	public void setRecruit_state_name(String recruit_state_name) {
		this.recruit_state_name = recruit_state_name;
	}
	public String getDepart_name() {
		return depart_name;
	}
	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
	public Map<String,String> getDepartMap() {
		return departMap;
	}
	public void setDepartMap(Map<String,String> departMap) {
		this.departMap = departMap;
	}
	public String getDepart_code() {
		return depart_code;
	}
	public void setDepart_code(String depart_code) {
		this.depart_code = depart_code;
	}
	public List<String> getDepartCodeList() {
		return departCodeList;
	}
	public void setDepartCodeList(List<String> departCodeList) {
		this.departCodeList = departCodeList;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getDepart_detail() {
		return depart_detail;
	}
	public void setDepart_detail(String depart_detail) {
		this.depart_detail = depart_detail;
	}
	public String getApplyyn() {
		return applyyn;
	}
	public void setApplyyn(String applyyn) {
		this.applyyn = applyyn;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRecruit_start_date() {
		return recruit_start_date;
	}
	public void setRecruit_start_date(String recruit_start_date) {
		this.recruit_start_date = recruit_start_date;
	}
	public String getRecruit_end_date() {
		return recruit_end_date;
	}
	public void setRecruit_end_date(String recruit_end_date) {
		this.recruit_end_date = recruit_end_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getHis_date() {
		return his_date;
	}
	public void setHis_date(String his_date) {
		this.his_date = his_date;
	}
	public List<String> getRecruitCodeList() {
		return recruitCodeList;
	}
	public void setRecruitCodeList(List<String> recruitCodeList) {
		this.recruitCodeList = recruitCodeList;
	}
	public List<String> getProjectCodeList() {
		return projectCodeList;
	}
	public void setProjectCodeList(List<String> projectCodeList) {
		this.projectCodeList = projectCodeList;
	}
	public String getProject_his_date() {
		return project_his_date;
	}
	public void setProject_his_date(String project_his_date) {
		this.project_his_date = project_his_date;
	}
}
