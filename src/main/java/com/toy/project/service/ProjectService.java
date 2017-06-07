package com.toy.project.service;

import java.util.List;

import com.toy.project.model.ProjectDto;

/**
 * 작성일 : 2017. 5. 16.
 * 작성자 : 김민지 
 * 설 명  : 프로젝트 관련 Service
 */

public interface ProjectService {

	/**
	 * 작성일 : 2017. 5. 16.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 생성 하기(저장)
	 * @return 
	 */
	public void saveNewProject(ProjectDto projectDto) throws Exception;
	
	/**
	 * 작성일 : 2017. 5. 17.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 전체 리스트 가져오기
	 * @return 
	 */
	public List<ProjectDto> getProjectList(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 상세  가져오기
	 */
	public ProjectDto getProjectDetail(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 5. 24.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 상세  직무 가져오기
	 * @return 
	 */
	public List<ProjectDto> getProjectDePDetail(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 상태코드 확인 
	 */
	public String selectProjectState(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 신청한 사람 있는지 체크 
	 */
	public int selectProjectMemberCnt(ProjectDto projectDto) ;
	
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 삭제하기
	 */
	public void deleteProjectAll(ProjectDto projectDto) ;
}
