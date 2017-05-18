package com.toy.project.service;

import java.util.List;

import com.toy.project.model.DepartmentDto;

/**
 * 작성일 : 2017. 5. 16.
 * 작성자 : 김민지 
 * 설 명  : 직무관련 Service
 */

public interface DepartmentService {

	/**
	 * 작성일 : 2017. 5. 16.
	 * 작성자 : 김민지 
	 * 설 명  : 직무 전체 리스트 불러오기
	 */
	public List<DepartmentDto> getDepartment();
}
