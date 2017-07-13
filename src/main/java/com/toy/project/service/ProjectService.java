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
	 * 작성일 : 2017. 7. 3.
	 * 작성자 : 김민지 
	 * 설 명  : 나의 프로젝트 전체 리스트 가져오기
	 * @return 
	 */
	public List<ProjectDto> getMyProjectList(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 상세  가져오기
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
	 * 설  명 : 프로젝트별 상태코드 확인 
	 */
	public String selectProjectState(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트별 신청한 사람 있는지 체크 
	 */
	public int selectProjectMemberCnt(ProjectDto projectDto) ;
	
	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 삭제하기
	 */
	public void deleteProjectAll(ProjectDto projectDto) ;
	
	
	/**
	 * 작성일 : 2017. 6. 08.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 수정하기
	 * @return 
	 */
	public void updateProjectAll(ProjectDto projectDto) throws Exception;
	
	/**
	 * 작성일 : 2017. 6. 14.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 신청하기
	 * @return 
	 */
	public void insertProjectMember(ProjectDto projectDto) throws Exception;
	
	/**
	 * 작성일 : 2017. 6 .16
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트  인원 리스트 가져오기
	 */
	public List<ProjectDto> getProjectMemberList(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 6 .19
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 멤버 정보 가져오기
	 */
	public ProjectDto getMemberInfo(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 6 .19
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 인원 상태코드 수정하기 
	 */
	public int updateProjectMember(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 6 .27
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 상태 코드 가져오기
	 */
	public List<ProjectDto> getStateCode(String type);
	
	
	/**
	 * 작성일 : 2017. 7 .7
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 모집 날짜 insert 하기 
	 */
	public int insertProjectRecruitDate(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 7 .7
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 프로젝트 날짜 insert 하기 
	 */
	public int insertProjectDate(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 7 .7
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 hisDate 수정하기  
	 */
	public int updateProjectHisDate(ProjectDto projectDto) ;
	
	/**
	 * 작성일 : 2017. 7 .7
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 시작/종료 수정하기  
	 */
	public int projectStart(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 7. 12.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 전체 리스트 카운트
	 * @return 
	 */
	public int getProjectListTotalCount(ProjectDto projectDto);
	
	/**
	 * 작성일 : 2017. 7. 12.
	 * 작성자 : 김민지 
	 * 설 명  : 나의 프로젝트 전체 리스트 카운트
	 * @return 
	 */
	public int getMyProjectListTotalCount(ProjectDto projectDto);
}
