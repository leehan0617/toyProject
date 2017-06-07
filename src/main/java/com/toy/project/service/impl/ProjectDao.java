package com.toy.project.service.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.project.model.ProjectDto;

@Repository
public class ProjectDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 작성일 : 2017. 5 .16
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 생성하기
	 */
	public int insertProject(ProjectDto projectDto) {
		return sqlSession.insert("project.insertProject",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 5 .16
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 직무 생성하기
	 */
	public int insertProjectDepart(HashMap<String, String> departMap) {
		return sqlSession.insert("project.insertProjectDepart",departMap);
	}
	
	/**
	 * 작성일 : 2017. 5 .17
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 전체 리스트 가져오기
	 */
	public List<ProjectDto> getProjectList(ProjectDto projectDto) {
		return sqlSession.selectList("project.getProjectList",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 상세  가져오기
	 */
	public ProjectDto getProjectDetail(ProjectDto projectDto) {
		return sqlSession.selectOne("project.getProjectDetail",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트  직무 리스트 가져오기
	 */
	public List<ProjectDto> getProjectDePDetail(ProjectDto projectDto) {
		return sqlSession.selectList("project.getProjectDePDetail",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .1
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 멤버 테이블에 insert
	 */
	public int insertProjectMember(ProjectDto projectDto) {
		return sqlSession.insert("project.insertProjectMember",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 상태코드 확인 
	 */
	public String selectProjectState(ProjectDto projectDto) {
		return sqlSession.selectOne("project.selectProjectState",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 신청한 사람 있는지 체크 
	 */
	public int selectProjectMemberCnt(ProjectDto projectDto) {
		return sqlSession.selectOne("project.selectProjectMemberCnt",projectDto);
	}
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 삭제하기
	 */
	public int deleteProject(ProjectDto projectDto) {
		return sqlSession.delete("project.deleteProject",projectDto);
	}
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 상세직무 삭제하기
	 */
	public int deleteProjectDePDetail(ProjectDto projectDto) {
		return sqlSession.delete("project.deleteProjectDePDetail",projectDto);
	}
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 신청한 사람 삭제하기  
	 */
	public int deleteProjectMember(ProjectDto projectDto) {
		return sqlSession.delete("project.deleteProjectMember",projectDto);
	}
}
