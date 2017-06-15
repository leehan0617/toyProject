package com.toy.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.toy.project.model.DepartmentDto;
import com.toy.project.model.ProjectDto;
import com.toy.project.service.DepartmentService;
import com.toy.project.service.ProjectService;
import com.toy.security.model.CustomUser;

/**
 * 작성일 : 2017. 5. 16.
 * 작성자 : 김민지
 * 설  명 : Project에 관한 Controller
 */

@Controller
public class ProjectController {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ProjectService projectService;
	
	/**
	 * 작성일 : 2017. 5. 15.
	 * 작성자 : 김민지
	 * 설  명 : 전체 프로젝트 리스트 보이기.
	 */
	@RequestMapping(value="/project" , method=RequestMethod.GET)
	public String project(Model model) {
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		
		ProjectDto projectDto = new ProjectDto();
		
		projectDto.setUser_id(user.getUsername());//본인이 신청한 프로젝트 구분하기
		
		List<ProjectDto> projectList = projectService.getProjectList(projectDto);
		
		model.addAttribute("projectList", projectList);
		model.addAttribute("user", user);
		return "project/project";
	}
	
	/**
	 * 작성일 : 2017. 5. 15.
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 생성하는 페이지 매핑하기.
	 */
	@RequestMapping(value="/project/new" , method=RequestMethod.GET)
	public String projectNew(Model model) {
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
				
		// 전체 직무 리스트 불러오기
		List<DepartmentDto> departList = departmentService.getDepartment();
		
		model.addAttribute("user" , user);
		model.addAttribute("departList" , departList);
		return "project/projectNew";
	}
	
	/**
	 * 작성일 : 2017. 5. 15.
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 생성하기 
	 * @throws Exception 
	 */
	@RequestMapping(value="/project" , method=RequestMethod.POST)
	public String insertproject(ProjectDto projectDto) throws Exception {
		projectService.saveNewProject(projectDto);
		return "redirect:/project/";
	}
	
	/**
	 * 작성일 : 2017. 5. 15.
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 상세보기 
	 */
	@RequestMapping(value="/project/{projectId}" , method=RequestMethod.GET)
	public String projectdetail(@PathVariable int projectId ,Model model) {
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProject_id(projectId);
		projectDto.setUser_id(user.getUsername());
		
		//프로젝트 상세 정보 불러오기
		ProjectDto projectDetail = projectService.getProjectDetail(projectDto);
		List<ProjectDto> projectDep = projectService.getProjectDePDetail(projectDto);
		
		// 전체 직무 리스트 불러오기
		List<DepartmentDto> departList = departmentService.getDepartment();

		model.addAttribute("projectDepList", projectDep);
		model.addAttribute("projectDetail" , projectDetail);
		model.addAttribute("user" , user);
		model.addAttribute("departList" , departList);
		return "project/projectDetail";
	}
	
	/**
	 * 작성일 : 2017. 6. 08.
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 수정하기
	 */
	@RequestMapping(value="/project/modify/{projectId}" , method=RequestMethod.GET)
	public String projectmodify(@PathVariable int projectId ,Model model) {
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProject_id(projectId);
		projectDto.setUser_id(user.getUsername());
		
		//프로젝트 상세 정보 불러오기
		ProjectDto projectDetail = projectService.getProjectDetail(projectDto);
		List<ProjectDto> projectDep = projectService.getProjectDePDetail(projectDto);
		
		// 전체 직무 리스트 불러오기
		List<DepartmentDto> departList = departmentService.getDepartment();

		Gson objGson = new Gson();//json으로 변경
		
		model.addAttribute("projectDep", objGson.toJson(projectDep));
		model.addAttribute("projectDetail" , projectDetail);
		model.addAttribute("user" , user);
		model.addAttribute("departList" , departList);
		return "project/projectModify";
	}

}
