package com.toy.project.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.issue.model.IssueDto;
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
	public List<IssueDto> selectIssueList (IssueDto issueDto) {
		return sqlSession.selectList("issue.selectIssueList", issueDto);
		
	}
	/**
	 * 작성일 : 2017.06.13
	 * 메소드명 : insertIssue 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈 생성
	 *
	 */
	public void insertIssue(IssueDto issueDto) {
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
	 * 메소드명 : insertIssueHistory 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈 히스토리 추가
	 *
	 */
	public void insertIssueHistory(IssueDto issueDto) {
		sqlSession.insert("issue.insertIssueHistory", issueDto);
	}
	/**
	 * 작성일 : 2017.06.19
	 * 메소드명 : insertIssueMember 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 이슈 멤버 추가
	 *
	 */
	public void insertIssueMember(IssueDto issueDto) {
		sqlSession.insert("issue.insertIssueMember", issueDto);
	}
	/**
	 * 작성일 : 2017.06.22
	 * 메소드명 : deleteIssueMember 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트에 해당하는 멤버 전부 삭제
	 *
	 */
	public void deleteIssueMember(String issue_id) {
		sqlSession.delete("issue.deleteIssueMember", issue_id);
	}
	/**
	 * 작성일 : 2017.06.23
	 * 메소드명 : updateIssueHisDate 
	 * 작성자 : 송하람
	 * 설명 : 프로젝트 히스토리 날짜 변경
	 *
	 */
	public void updateIssueHisDate(IssueDto issueDto) {
		sqlSession.insert("issue.updateIssueHisDate", issueDto);
	}
	/**
	 * 작성일 : 2017.06.26
	 * 메소드명 : selectTopIssueHistory 
	 * 작성자 : 송하람
	 * 설명 : 최근 이슈 정보 가져오기
	 *
	 */
	public IssueDto selectTopIssueHistory(String issue_id) {
		return sqlSession.selectOne("selectTopIssueHistory", issue_id);
	}
	/**
	 * 작성일 : 2017.06.26
	 * 메소드명 : selectIssueInfoByIssueId 
	 * 작성자 : 송하람
	 * 설명 : 클릭한 이슈 정보 가져오기
	 *
	 */
	public IssueDto selectIssueInfoByIssueId(String issue_id) {
		return sqlSession.selectOne("selectIssueInfoByIssueId", issue_id);
	}
	/**
	 * 작성일 : 2017.06.27
	 * 메소드명 : updateIssue 
	 * 작성자 : 송하람
	 * 설명 : 이슈 업데이트
	 *
	 */
	public void updateIssue (IssueDto issueDto) {
		sqlSession.insert("issue.updateIssue", issueDto);
	}

}
