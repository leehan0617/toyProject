package com.toy.issue.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toy.project.service.IssueService;
import com.toy.user.service.impl.UserServiceImpl;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 송하람
 * 설  명 : Issue에 관한 Controller
 */
@Controller
public class IssueController {
	
	@Autowired
	IssueService issueService;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@RequestMapping(value={"/issue/addIssue/{projectId}"}, method=RequestMethod.POST)
	public String showProjectList(HttpServletRequest request, @PathVariable String projectId) {
		
		logger.info(projectId);
		return "redirect:/issue/detail/"+projectId;
	}

	
	

}
