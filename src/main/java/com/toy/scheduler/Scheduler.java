package com.toy.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.toy.issue.model.IssueDto;
import com.toy.issue.service.impl.IssueDao;
import com.toy.project.model.ProjectDto;
import com.toy.project.service.impl.ProjectDao;

@Component
public class Scheduler {
	@Autowired
	IssueDao dao;
	
	@Autowired
	ProjectDao projectdao;
	
	@Scheduled(cron="0 39 13 * * *")
	//시작날짜가 오늘인 경우 상태 변경
	public void cronTest1() {
		try {
			Calendar calendar = Calendar.getInstance();
	        Date date = calendar.getTime();
	        String today = (new SimpleDateFormat("yyyy-MM-dd").format(date));
	        
	        List<IssueDto> issueList = dao.selectCronIssue(today);
	        String today2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	        
	        for (int i = 0; i < issueList.size(); i++) {
	        	IssueDto paramDto = issueList.get(i);
	        	
		    	paramDto.setIssue_num(Integer.parseInt(paramDto.getIssue_id()));
		    	paramDto.setProject_num(Integer.parseInt(ObjectUtils.toString(paramDto.getProject_id())));
		        paramDto.setHis_date(today2);
		        paramDto.setState_code("start");
		        
		        dao.updateIssueHisDate(paramDto);
				dao.insertIssueHistory(paramDto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * 작성일 : 2017. 7 .10
	 * 작성자 : 김민지
	 * 설  명 : 배치로 상태 코드 변경하기- 모집 상태 
	 */
	
	@Scheduled(cron="0 30 10 * * *")
	public void cronProjectRecruit() {
		try {
			Calendar calendar = Calendar.getInstance();
	        Date date = calendar.getTime();
	        String today = (new SimpleDateFormat("yyyy-MM-dd").format(date));
	        
	        //모집상태 오늘인 프로젝트 불러오기
	        List<ProjectDto> projectRecruitList = projectdao.getTodayRecruit(today);
	        
	        String today2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	        
	        for (int i = 0; i < projectRecruitList.size(); i++) {
	        	ProjectDto paramDto = projectRecruitList.get(i);
	        	
		        paramDto.setHis_date(today2);
		        paramDto.setState_code("recruiting");
		        paramDto.setType("recruit");
		        paramDto.setMod_id("BATCH");
		        paramDto.setReg_id("BATCH");
		        
		        //his_date 업데이트
		        projectdao.updateProjectHisDate(paramDto);
		       
		        //프로젝트 모집상태 history insert
		        projectdao.insertProjectRecruitDate(paramDto);
		        
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * 작성일 : 2017. 7 .10
	 * 작성자 : 김민지
	 * 설  명 : 배치로 상태 코드 변경하기- 프로젝트 상태 
	 */
	
	@Scheduled(cron="0 57 10 * * *")
	public void cronProject() {
		try {
			Calendar calendar = Calendar.getInstance();
	        Date date = calendar.getTime();
	        String today = (new SimpleDateFormat("yyyy-MM-dd").format(date));
	        
	        //프로젝트 시작 오늘인 프로젝트 불러오기
	        List<ProjectDto> projectRecruitList = projectdao.getTodayProject(today);
	        
	        String today2 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	        
	        for (int i = 0; i < projectRecruitList.size(); i++) {
	        	ProjectDto paramDto = projectRecruitList.get(i);
	        	
		        paramDto.setProject_his_date(today2);
		        paramDto.setState_code("start");
		        paramDto.setType("project");
		        paramDto.setMod_id("BATCH");
		        paramDto.setReg_id("BATCH");
		        
		        //his_date 업데이트
		        projectdao.updateProjectHisDate(paramDto);
		       
		        //프로젝트 모집상태 history insert
		        projectdao.insertProjectDate(paramDto);
		        
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
