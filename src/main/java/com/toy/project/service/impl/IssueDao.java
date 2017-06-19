package com.toy.project.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.issue.model.issueDto;
import com.toy.issue.model.projectMemberDto;
import com.toy.project.model.ProjectDto;

@Repository
public class IssueDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 작성일 : 2017.05.29
	 * 메소드명 : selectAllProjectList 
	 * 작성자 : 송하람
	 * 설명 : 모든 모집완료된 프로젝트 전체 리스트 가져오기
	 *
	 */
	public List<ProjectDto> selectAllProjectList(String user_id) {
		return sqlSession.selectList("issue.selectAllProjectList", user_id);
	}
	
	/**
	 * 작성일 : 2017.05.29
	 * 메소드명 : selectApplyListFromProjectMember 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 참여 대상자 가져오기.
	 *
	 */
	public List<projectMemberDto> selectApplyListFromProjectMember (String projectId) {
		return sqlSession.selectList("issue.selectApplyListFromProjectMember", projectId);
		
	}
	/**
	 * 작성일 : 2017.05.29
	 * 메소드명 : selectIssueList 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈  가져오기.
	 *
	 */
	public List<issueDto> selectIssueList (String projectId) {
		return sqlSession.selectList("issue.selectIssueList", projectId);
		
	}
	/**
	 * 작성일 : 2017.06.13
	 * 메소드명 : insertIssue 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈 생성
	 *
	 */
	public void insertIssue(issueDto issueDto) {
		sqlSession.insert("issue.insertIssue", issueDto);
	}
	
	/**
	 * 작성일 : 2017.06.15
	 * 메소드명 : deleteIssue 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈 삭제
	 *
	 */
	public void deleteIssue(String issue_id) {
		sqlSession.insert("issue.deleteIssue", issue_id);
	}
	
	/**
	 * 작성일 : 2017.06.19
	 * 메소드명 : changeIssueState 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈 상태변경
	 *
	 */
	public void changeIssueState(issueDto issueDto) {
		sqlSession.insert("issue.changeIssueState", issueDto);
	}
	

}
