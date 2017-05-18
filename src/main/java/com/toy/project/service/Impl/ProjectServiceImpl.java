package com.toy.project.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy.project.model.DepartmentDto;
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
		if(projectDto.getDepart_code() != null){
			if(projectDto.getDepart_code().size() > 0){
				projectDao.insertProjectDepart(projectDto);
			}
		}
		
	}
	
	/**
	 * 작성일 : 2017. 5. 17.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 리스트 불러오기
	 */
	public List<ProjectDto> getProjectList(ProjectDto projectDto) {
		
		List<ProjectDto> projectList = projectDao.getProjectList(projectDto);
		
		Map<Integer,ProjectDto> map  = new HashMap();
		
		if(projectList.size() > 0){
			
			//프로젝트 별로 직무 합치기 
			for(int i = 0 ; i <projectList.size();i++){
				
				int key = projectList.get(i).getProject_id();
				
				if(!map.containsKey(key)){//map 에 키가 존재할때
					ProjectDto dto = projectList.get(i);
					List departList = new ArrayList();
					departList.add(projectList.get(i).getDepart_name());
					dto.setDepart_code(departList);
					map.put(key, dto);
				}else{
					ProjectDto dto = map.get(key);
					List departList = dto.getDepart_code();
					departList.add(projectList.get(i).getDepart_name());
					dto.setDepart_code(departList);
					map.put(key, dto);
				}
			}
		}
		
		//프로젝트 별로 직무 행 -> 열로 합치기 
		List<ProjectDto> resultList = new ArrayList<>(map.values());
		
		return resultList;
	}
	

}
