package com.toy.project.service;

import java.util.HashMap;
import java.util.List;

import com.toy.project.model.ProjectDto;

public interface IssueService {
	
	public List<ProjectDto> selectAllProjectList(String user_id);
	
	public List<HashMap<String, Object>> selectApplyListFromProjectMember (String projectId);
	

}
