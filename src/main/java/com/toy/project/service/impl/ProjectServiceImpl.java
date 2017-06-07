package com.toy.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy.project.model.ProjectDto;
import com.toy.project.service.ProjectService;
import com.toy.user.model.UserDto;

/**
 * 작성일 : 2017. 5. 16.
 * 작성자 : 김민지 
 * 설 명  : 직무관련 ServiceImpl
 */


@Service(value="ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectDao projectDao; 
	
	/**
	 * 작성일 : 2017. 5. 16.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 생성 하기(저장)
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveNewProject(ProjectDto projectDto) throws Exception{
		// 로그인정보를 가져온다.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		UserDto currentUser = (UserDto) auth.getPrincipal();
		
		projectDto.setManager_id(currentUser.getUser_id());
		
		//프로젝트 생성
		projectDao.insertProject(projectDto);
		
		//프로젝트별 직무 생성
		if(projectDto.getDepartCodeList() != null){
			if(!projectDto.getDepartCodeList().isEmpty()){
				
				for(String departCode : projectDto.getDepartCodeList()){
					
					HashMap<String, String> map = new HashMap<>();
					
					map.put("project_id", String.valueOf(projectDto.getProject_id()));
					map.put("depart_code", departCode);
					map.put("usercount", projectDto.getDepartMap().get(departCode));
					projectDao.insertProjectDepart(map);
				}
				
			}
		}
		
		projectDto.setUser_id(currentUser.getUser_id());
		projectDto.setReg_id(currentUser.getUser_id());
		projectDto.setState_code("01");//매니저 자동으로 프로젝트 참여01:수락 상태 넣기 
		projectDao.insertProjectMember(projectDto);
	}
	
	/**
	 * 작성일 : 2017. 5. 17.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 리스트 불러오기
	 */
	public List<ProjectDto> getProjectList(ProjectDto projectDto) {
		
		List<ProjectDto> projectList = projectDao.getProjectList(projectDto);
		
		Map<Integer,ProjectDto> map  = new HashMap<>();
		
		if(!projectList.isEmpty()){
			
			//프로젝트 별로 직무 합치기 
			for(int i = 0 ; i <projectList.size();i++){
				
				int key = projectList.get(i).getProject_id();
				
				ProjectDto dto;
				Map<String,String> departMap;
				
				if(!map.containsKey(key)){//map 에 키가 존재할때
					dto = projectList.get(i);
					departMap = new HashMap<>();
				}else{
					dto = map.get(key);
					departMap = dto.getDepartMap();
				}

				departMap.put(projectList.get(i).getDepart_name(), String.valueOf(projectList.get(i).getUsercount()));
				dto.setDepartMap(departMap);
				
				map.put(key, dto);
			}
		}
		
		//프로젝트 별로 직무 행 -> 열로 합치기 
		List<ProjectDto> resultList = new ArrayList<>(map.values());
		
		return resultList;
	}

	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 상세  가져오기
	 */
	public ProjectDto getProjectDetail(ProjectDto projectDto) {
		
		ProjectDto projectList = projectDao.getProjectDetail(projectDto);
		return projectList;
	}

	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 직무 리스트 가져오기
	 */
	public List<ProjectDto> getProjectDePDetail(ProjectDto projectDto) {
		
		List<ProjectDto> projectDePDetail = projectDao.getProjectDePDetail(projectDto);
		return projectDePDetail;
	}

	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 상태코드 확인 
	 */

	public String selectProjectState(ProjectDto projectDto) {
		return projectDao.selectProjectState(projectDto);
	}

	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 신청한 사람 있는지 체크 
	 */
	public int selectProjectMemberCnt(ProjectDto projectDto) {
		return projectDao.selectProjectMemberCnt(projectDto);
	}

	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 삭제하기
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteProjectAll(ProjectDto projectDto) {
		projectDao.deleteProjectMember(projectDto);//프로젝트 참여자 삭제
		projectDao.deleteProjectDePDetail(projectDto);//프로젝트 상세 직무 삭제
		projectDao.deleteProject(projectDto);//프로젝트 삭제
	}
	
}
