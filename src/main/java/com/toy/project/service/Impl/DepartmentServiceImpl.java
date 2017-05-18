package com.toy.project.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toy.project.model.DepartmentDto;
import com.toy.project.service.DepartmentService;

/**
 * 작성일 : 2017. 5. 16.
 * 작성자 : 김민지 
 * 설 명  : 직무관련 ServiceImpl
 */


@Service(value="DepartmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 작성일 : 2017. 5. 16.
	 * 작성자 : 김민지 
	 * 설 명  : 직무 전체 리스트 불러오기 
	 */
	public List<DepartmentDto> getDepartment() {
		return departmentDao.getDepartment();
	}

}
