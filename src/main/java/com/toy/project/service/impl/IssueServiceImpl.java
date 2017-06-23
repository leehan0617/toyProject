package com.toy.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.toy.issue.model.IssueDto;
import com.toy.issue.model.projectMemberDto;
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
	public List<projectMemberDto> selectApplyListFromProjectMember(String projectId) {
		return issueDao.selectApplyListFromProjectMember(projectId);
	}

	/**
	 * 작성일 : 2017.06.07
	 * 메소드명 : selectIssueList
	 * 작성자 : 송하람
	 * 설명 : 이슈  가져오기
	 *
	 */
	public List<IssueDto> selectIssueList(String projectId) {
		return issueDao.selectIssueList(projectId);
	}
	
	/**
	 * 작성일 : 2017.06.13
	 * 메소드명 : insertIssue 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈 생성
	 *
	 */
	@Transactional(rollbackFor=Exception.class)
	public void insertIssue(IssueDto issueDto) throws Exception{
		try {
			issueDao.insertIssue(issueDto);
			this.insertIssueHistory(issueDto);
			
			List<String> userList = issueDto.getUserList();
			for (int i = 0; i < userList.size(); i++) {
				IssueDto memberDto = new IssueDto();
				memberDto.setIssue_id(issueDto.getIssue_id());
				memberDto.setProject_id(issueDto.getProject_id());
				memberDto.setUser_id(userList.get(i));
				issueDao.insertIssueMember(memberDto);
			}
		}
		catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		
	}

	/**
	 * 작성일 : 2017.06.15
	 * 메소드명 : deleteIssue 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈 삭제
	 *
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteIssue(String issue_id) {
		issueDao.deleteIssue(issue_id);
		issueDao.deleteIssueMember(issue_id);
	}
	/**
	 * 작성일 : 2017.06.19
	 * 메소드명 : changeIssueState 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈 상태변경
	 *
	 */
	public void insertIssueHistory(IssueDto issueDto) {
		System.out.println("-------------------------------------2");
		issueDao.insertIssueHistory(issueDto);
		
	}
	

}
