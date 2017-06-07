package com.toy.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toy.project.model.ProjectDto;
import com.toy.project.service.ProjectService;
import com.toy.user.model.UserDto;


/**
 * 작성일 : 2017. 6. 5.
 * 작성자 : 김민지
 * 설  명 : Project에 관한  RESTController
 */

@RestController
public class ProjectRestController {
	
	@Autowired
	private ProjectService projectService;
	
	/**
	 * 작성일 : 2017. 6. 5.
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 삭제 여부 체크 
	 */
	@RequestMapping(value="/project/json/{projectId}" , method=RequestMethod.GET)
	public Boolean update(@PathVariable int projectId) {
		
		// 로그인정보를 가져온다.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		UserDto currentUser = (UserDto) auth.getPrincipal();
				
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProject_id(projectId);
		projectDto.setUser_id(currentUser.getUser_id());
		
		// 모집 상태가 완료인지 아닌지 체크 
		String state_code = projectService.selectProjectState(projectDto);
		
		//프로젝트 멩버 신청한 사람 수 세기 
		int membercnt = projectService.selectProjectMemberCnt(projectDto);
		
		if(state_code.equals("0101")){//모집상태가 모집중일때 
			if(membercnt > 0){//참여한 멤버가 있을경우 삭제 못함
				return false;
			}else{ //참여한 멤버가 없을경우 삭제 가능
				return true;
			}
		}else{//모집상태가 완료일때 
			return false;
		}
	}
	
	/**
	 * 작성일 : 2017. 6. 5.
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 삭제 하기
	 */
	@RequestMapping(value="/project/delete/{projectId}" , method=RequestMethod.GET)
	public Boolean deleteproject(@PathVariable int projectId) throws Exception {
		
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProject_id(projectId);
		
		projectService.deleteProjectAll(projectDto);
		
		return true;
	}
}
