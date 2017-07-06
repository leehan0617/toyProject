package com.toy.issue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toy.issue.model.IssueDto;
import com.toy.issue.service.IssueService;

@RestController
public class IssueRestController {
	
	@Autowired
	IssueService issueService;
	
	@RequestMapping(value={"/issue/getIssueInfo/{issue_id}"}, method=RequestMethod.GET)
	public IssueDto getIssueInfoByIssueId(HttpServletRequest request, @PathVariable String issue_id) {
		IssueDto issuedto = issueService.selectIssueInfoByIssueId(issue_id);
		return issuedto;
	}
}
