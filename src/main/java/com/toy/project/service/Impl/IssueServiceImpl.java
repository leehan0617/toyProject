package com.toy.project.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toy.project.model.ProjectDto;
import com.toy.project.service.IssueService;

@Service("IssueService")
public class IssueServiceImpl implements IssueService{
	
	@Autowired
	IssueDao issueDao;

	/**
	 * 작성일 : 2017.05.29
	 * 메소드명 : selectAllProjectList 
	 * 작성자 : 송하람
	 * 설명 : 모든 모집완료된 프로젝트 전체 리스트 가져오기
	 *
	 */
	public List<ProjectDto> selectAllProjectList(String user_id) {
		return issueDao.selectAllProjectList(user_id);
	}

	/**
	 * 작성일 : 2017.05.29
	 * 메소드명 : selectApplyListFromProjectMember 
	 * 작성자 : 송하람
	 * 설명 : 이슈 참여 대상자 가져오기
	 *
	 */
	public List<HashMap<String, Object>> selectApplyListFromProjectMember(String projectId) {
		return issueDao.selectApplyListFromProjectMember(projectId);
	}
	
	
	

}
