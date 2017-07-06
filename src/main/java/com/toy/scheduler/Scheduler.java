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

@Component
public class Scheduler {
	@Autowired
	IssueDao dao;
	
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

}
