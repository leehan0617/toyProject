package com.toy.issue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.toy.issue.model.IssueDto;
import com.toy.issue.model.projectMemberDto;
import com.toy.project.model.ProjectDto;
import com.toy.project.service.IssueService;
import com.toy.security.model.CustomUser;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 송하람
 * 설  명 : Issue에 관한 Controller
 */
@Controller
public class IssueController {
	
	@Autowired
	IssueService issueService;
	private static final Logger logger = LoggerFactory.getLogger(IssueController.class);
	
	@RequestMapping(value={"/issue/addIssue/{projectId}"}, method=RequestMethod.POST)
	public String showProjectList(HttpServletRequest request, @PathVariable String projectId) {
		
		logger.info(projectId);
		return "redirect:/issue/detail/"+projectId;
	}

	/**
	 * 작성일 : 2017. 05.24
	 * 작성자 : 송하람
	 * 설  명 : 이슈페이지
	 * @throws Exception 
	 */
	@RequestMapping(value="/issue/add" , method=RequestMethod.POST) 
	public String addIssue(IssueDto dto){
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// 나를 포함하여 userlist에 넣어줌
		CustomUser user = (CustomUser) au.getPrincipal();
		
		List<String> userList = dto.getUserList();
		userList.add(user.getUsername());
		dto.setReg_id(user.getUsername());
		dto.setUserList(userList);
		
		try {
			issueService.insertIssue(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/project/";
	}
	
	@RequestMapping(value={"/issue/detail/{projectId}"} , method=RequestMethod.GET)
	public ModelAndView showIssueList(@PathVariable String projectId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("issue/issueList");
		
		//프로젝트에 해당하는 이슈리스트가져오기
		List<IssueDto> issueList = issueService.selectIssueList(projectId);
		mav.addObject("issueList", issueList);
		
		List<projectMemberDto> memberList = issueService.selectApplyListFromProjectMember(projectId);
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		mav.addObject("myInfo", user);
		mav.addObject("memberList", memberList);
		return mav;
	}
	
	@RequestMapping(value={"/issue/projectlist"} , method=RequestMethod.GET)
	public ModelAndView showProjectList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		
		List<ProjectDto> projectList = issueService.selectAllProjectList(user.getUsername());
		mav.addObject("projectList", projectList);
		
		mav.setViewName("issue/projectList");
		return mav;
	}
	
	@RequestMapping(value={"/issue/delete"}, method=RequestMethod.POST)
	public String deleteIssue(HttpServletRequest request, IssueDto issuedto) {
		issueService.deleteIssue(issuedto.getIssue_id());
		return "redirect:/issue/detail/"+issuedto.getProject_id();
	}
	
	@RequestMapping(value={"/issue/changeIssue"}, method=RequestMethod.POST)
	public String changeIssue(HttpServletRequest request, IssueDto dto) {
		issueService.insertIssueHistory(dto);
		return "redirect:/issue/detail/"+dto.getProject_id();
	}
	
	
	

}
