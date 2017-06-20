package com.toy.project.service;

import java.util.List;

import com.toy.issue.model.IssueDto;
import com.toy.issue.model.projectMemberDto;
import com.toy.project.model.ProjectDto;

public interface IssueService {
	
	public List<ProjectDto> selectAllProjectList(String user_id);
	
	public List<projectMemberDto> selectApplyListFromProjectMember (String projectId);
	
	public List<IssueDto> selectIssueList (String projectId);
	
	public void insertIssue(IssueDto issueDto);
	
	public void deleteIssue(String issue_id);
	
	public void changeIssueState(IssueDto issueDto);
}
