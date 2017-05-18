package com.toy.project.model;

import java.io.Serializable;

/**
 * 작성일 : 2017. 5. 16.
 * 작성자 : 김민지 
 * 설 명  : 직무관련 Dto
 */

@SuppressWarnings("serial")
public class DepartmentDto implements Serializable{
	
	//직무 코드
	private String depart_code;
	
	//직무 명칭
	private String depart_name;
	
	public String getDepart_code() {
		return depart_code;
	}
	public void setDepart_code(String depart_code) {
		this.depart_code = depart_code;
	}
	public String getDepart_name() {
		return depart_name;
	}
	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
	
}
