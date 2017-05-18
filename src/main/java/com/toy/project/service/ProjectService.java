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
}
