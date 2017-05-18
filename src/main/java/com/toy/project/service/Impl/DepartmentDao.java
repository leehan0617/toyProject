package com.toy.project.service.Impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.project.model.DepartmentDto;

@Repository
public class DepartmentDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 작성일 : 2017. 5 .16
	 * 작성자 : 김민지
	 * 설  명 : 직무 리스트 가져오기
	 */
	public List<DepartmentDto> getDepartment() {
		return sqlSession.selectList("department.getDepartment");
	}
	
}
