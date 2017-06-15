package com.toy.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toy.project.model.ProjectDto;
import com.toy.project.service.ProjectService;
import com.toy.security.model.CustomUser;


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
	@RequestMapping(value="/project/checkIs/{projectId}" , method=RequestMethod.GET)
	public Boolean update(@PathVariable int projectId) {
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
				
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
				
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProject_id(projectId);
		projectDto.setUser_id(user.getUsername());
		
		// 모집 상태가 완료인지 아닌지 체크 
		String state_code = projectService.selectProjectState(projectDto);
		
		//프로젝트 멩버 신청한 사람 수 세기 
		int membercnt = projectService.selectProjectMemberCnt(projectDto);
		
		if("recruiting".equals(state_code)){//모집상태가 모집중일때 
//			if(membercnt > 0){//참여한 멤버가 있을경우 삭제 못함
//				return false;
//			}else{ //참여한 멤버가 없을경우 삭제 가능
				return true;
//			}
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
	public Boolean deleteproject(@PathVariable int projectId) throws Exception{
		
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProject_id(projectId);
		
		projectService.deleteProjectAll(projectDto);
		
		return true;
	}
	
	/**
	 * 작성일 : 2017. 6. 8.
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 수정하기
	 * @throws Throwable 
	 */
	@RequestMapping(value="/project/update/{projectId}" , method=RequestMethod.POST)
	public Boolean updateproject(@PathVariable int projectId,ProjectDto projectDto) throws Throwable {
		projectDto.setProject_id(projectId);
		projectService.updateProjectAll(projectDto);
		return true;
	}
	
	/**
	 * 작성일 : 2017. 6. 14.
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 신청하기
	 * @throws Throwable 
	 */
	@RequestMapping(value="/project/apply" , method=RequestMethod.POST)
	public Boolean applyproject(ProjectDto projectDto,HttpServletRequest request) throws Throwable {
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		
		projectDto.setUser_id(user.getUsername());//신청한 사람 ID
		projectDto.setReg_id(user.getUsername());
		projectDto.setState_code("apply");//신청 코드 apply :신청
		
		projectService.insertProjectMember(projectDto);//프로젝트 신청하기(insert)
		
		return true;
	}
}
