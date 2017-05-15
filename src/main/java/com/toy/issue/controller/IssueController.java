package com.toy.issue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : Issue에 관한 Controller
 */
@Controller
public class IssueController {
	
	@RequestMapping(value={"/issue/list"} , method=RequestMethod.GET)
	public String showIssueList(HttpServletRequest request) {
		return "issue/issueList";
	}
}
