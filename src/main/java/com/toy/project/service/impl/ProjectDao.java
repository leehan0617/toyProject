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
	 * 설  명 : 프로젝트 생성하기
	 */
	public int insertProject(ProjectDto projectDto) {
		return sqlSession.insert("project.insertProject",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 5 .16
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트별 직무 생성하기
	 */
	public int insertProjectDepart(HashMap<String, String> departMap) {
		return sqlSession.insert("project.insertProjectDepart",departMap);
	}
	
	/**
	 * 작성일 : 2017. 5 .17
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 전체 리스트 가져오기
	 */
	public List<ProjectDto> getProjectList(ProjectDto projectDto) {
		return sqlSession.selectList("project.getProjectList",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 7. 3.
	 * 작성자 : 김민지 
	 * 설 명  : 나의 프로젝트 전체 리스트 가져오기
	 * @return 
	 */
	public List<ProjectDto> getMyProjectList(ProjectDto projectDto) {
		return sqlSession.selectList("project.getMyProjectList",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 상세  가져오기
	 */
	public ProjectDto getProjectDetail(ProjectDto projectDto) {
		return sqlSession.selectOne("project.getProjectDetail",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트  직무 리스트 가져오기
	 */
	public List<ProjectDto> getProjectDePDetail(ProjectDto projectDto) {
		return sqlSession.selectList("project.getProjectDePDetail",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .1
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트별 멤버 테이블에 insert
	 */
	public int insertProjectMember(ProjectDto projectDto) {
		return sqlSession.insert("project.insertProjectMember",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트별 상태코드 확인 
	 */
	public String selectProjectState(ProjectDto projectDto) {
		return sqlSession.selectOne("project.selectProjectState",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트별 신청한 사람 있는지 체크 
	 */
	public int selectProjectMemberCnt(ProjectDto projectDto) {
		return sqlSession.selectOne("project.selectProjectMemberCnt",projectDto);
	}
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 삭제하기
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
	 * 설  명 : 프로젝트별 신청한 사람 삭제하기  
	 */
	public int deleteProjectMember(ProjectDto projectDto) {
		return sqlSession.delete("project.deleteProjectMember",projectDto);
	}
	/**
	 * 작성일 : 2017. 6 .8
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 정보 수정하기  
	 */
	public int updateProject(ProjectDto projectDto) {
		return sqlSession.update("project.updateProject",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .16
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트  직무 리스트 가져오기
	 */
	public List<ProjectDto> getProjectMemberList(ProjectDto projectDto) {
		return sqlSession.selectList("project.getProjectMemberList",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .16
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 인원 상태코드 수정하기 
	 */
	public int updateProjectMember(ProjectDto projectDto) {
		return sqlSession.update("project.updateProjectMember",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .19
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 멤버 정보 가져오기
	 */
	public ProjectDto getMemberInfo(ProjectDto projectDto) {
		return sqlSession.selectOne("project.getMemberInfo",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .23
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 모집 날짜 insert 하기 
	 */
	public int insertProjectRecruitDate(ProjectDto projectDto) {
		return sqlSession.insert("project.insertProjectRecruitDate",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .23
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 프로젝트 날짜 insert 하기 
	 */
	public int insertProjectDate(ProjectDto projectDto) {
		return sqlSession.insert("project.insertProjectDate",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 6 .27
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 상태 코드 가져오기
	 */
	public List<ProjectDto> getStateCode(String type) {
		return sqlSession.selectList("project.getStateCode",type);
	}
	
	/**
	 * 작성일 : 2017. 7 .7
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 hisDate 수정하기  
	 */
	public int updateProjectHisDate(ProjectDto projectDto) {
		return sqlSession.update("project.updateProjectHisDate",projectDto);
	}
	
	/**
	 * 작성일 : 2017. 7 .10
	 * 작성자 : 김민지
	 * 설  명 : 배치로 상태 코드 변경하기- 모집 상태 
	 */
	public List<ProjectDto> getTodayRecruit(String start_date) {
		return sqlSession.selectList("project.getTodayRecruit",start_date);
	}
	
	/**
	 * 작성일 : 2017. 7 .10
	 * 작성자 : 김민지
	 * 설  명 : 배치로 상태 코드 변경하기- 프로젝트 상태 
	 */
	public List<ProjectDto> getTodayProject(String start_date) {
		return sqlSession.selectList("project.getTodayProject",start_date);
	}
}
