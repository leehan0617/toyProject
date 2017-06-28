package com.toy.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<IssueDto> selectIssueList(IssueDto issueDto) {
		return issueDao.selectIssueList(issueDto);
	}
	
	/**
	 * 작성일 : 2017.06.13
	 * 메소드명 : insertIssue 
	 * 작성자 : 송하람
	 * 설명 : 이슈 생성
	 *
	 */
	@Transactional(rollbackFor=Exception.class)
	public void insertIssue(IssueDto issueDto){
			Calendar calendar = Calendar.getInstance();
	        Date date = calendar.getTime();
	        String today = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	
			issueDto.setHis_date(today);
			issueDao.insertIssue(issueDto);
			
			int issue_id = Integer.parseInt(ObjectUtils.toString(issueDto.getIssue_id()));
			
			issueDto.setIssue_num(issue_id);
			issueDto.setProject_num(Integer.parseInt(ObjectUtils.toString(issueDto.getProject_id())));
			issueDao.insertIssueHistory(issueDto);
			
			List<String> userList = issueDto.getUserList();
			for (int i = 0; i < userList.size(); i++) {
				IssueDto memberDto = new IssueDto();
				memberDto.setIssue_num(issue_id);
				memberDto.setProject_num(Integer.parseInt(ObjectUtils.toString(issueDto.getProject_id())));
				memberDto.setUser_id(userList.get(i));
				issueDao.insertIssueMember(memberDto);
			}
	}

	/**
	 * 작성일 : 2017.06.15
	 * 메소드명 : deleteIssue 
	 * 작성자 : 송하람
	 * 설명 :  이슈 삭제
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
	 * 설명 :  이슈 상태변경
	 *
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertIssueHistory(IssueDto issueDto) {
		String issue_id = issueDto.getIssue_id();
		
		IssueDto paramDto = issueDao.selectTopIssueHistory(issue_id);
		
		Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		
    	paramDto.setIssue_num(Integer.parseInt(issue_id));
    	paramDto.setProject_num(Integer.parseInt(ObjectUtils.toString(paramDto.getProject_id())));
        paramDto.setHis_date(today);
        paramDto.setState_code(issueDto.getState_code());
        
        issueDao.updateIssueHisDate(paramDto);
		issueDao.insertIssueHistory(paramDto);
	}
	/**
	 * 작성일 : 2017.06.26
	 * 메소드명 : selectIssueInfoByIssueId 
	 * 작성자 : 송하람
	 * 설명 : 이슈 정보가져오기
	 *
	 */
	public IssueDto selectIssueInfoByIssueId(String issue_id) {
		return issueDao.selectIssueInfoByIssueId(issue_id);
	}
	/**
	 * 작성일 : 2017.06.27
	 * 메소드명 : updateIssue
	 * 작성자 : 송하람
	 * 설명 : 이슈 수정
	 *
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateIssue (IssueDto issueDto) {
		Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        issueDto.setHis_date(today);
        issueDto.setIssue_num(Integer.parseInt(issueDto.getIssue_id()));
        issueDto.setProject_num(Integer.parseInt(ObjectUtils.toString(issueDto.getProject_id())));
        
		issueDao.updateIssue(issueDto);
		issueDao.insertIssueHistory(issueDto);
	}
	


}
