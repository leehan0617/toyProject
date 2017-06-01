package com.toy.issue.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toy.project.model.ProjectDto;
import com.toy.project.service.IssueService;
import com.toy.user.model.UserDto;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 송하람
 * 설  명 : Issue에 관한 Controller
 */
@RestController
public class IssueController {
	
	@Autowired
	IssueService issueService;
	
	@RequestMapping(value={"/issue/projectMember"})
	public List<HashMap<String, Object>> showProjectList(HttpServletRequest request) {
		String project_id = request.getParameter("project_id");		
		List<HashMap<String, Object>> memberList = issueService.selectApplyListFromProjectMember(project_id);
		
		return memberList;
	}

	
	

}
