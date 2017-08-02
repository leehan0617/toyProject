package com.toy.project.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy.project.model.ProjectDto;
import com.toy.project.service.ProjectService;
import com.toy.project.util.ProjectUtil;
import com.toy.security.model.CustomUser;

/**
 * 작성일 : 2017. 5. 16.
 * 작성자 : 김민지 
 * 설 명  : 직무관련 ServiceImpl
 */


@Service(value="ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService{

	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectDao projectDao; 
	
	/**
	 * 작성일 : 2017. 5. 16.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 생성 하기(저장)
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveNewProject(ProjectDto projectDto){
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		projectDto.setManager_id(user.getUsername());
		
		//현재 날짜 
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String his_date = dayTime.format(new Date(time));
		
		projectDto.setHis_date(his_date);
		projectDto.setProject_his_date(his_date);
		
		//프로젝트 생성
		projectDao.insertProject(projectDto);
		logger.info("프로젝트 생성");
		
		//프로젝트별 직무 생성
		if(projectDto.getDepartMap() != null && !projectDto.getDepartMap().isEmpty()){
			
			for(String key : projectDto.getDepartMap().keySet()){
				
				HashMap<String, String> map = new HashMap<>();
				
				map.put("project_id", String.valueOf(projectDto.getProject_id()));
				map.put("depart_code", key);
				map.put("usercount", projectDto.getDepartMap().get(key));
				projectDao.insertProjectDepart(map);
			}
			
		}
		
		logger.info("프로젝트 직무 생성");
		
		projectDto.setUser_id(user.getUsername());
		projectDto.setReg_id(user.getUsername());
		projectDto.setState_code(ProjectUtil.ACCEPT);//매니저 자동으로 프로젝트 참여 accept:수락 상태 넣기 
		projectDao.insertProjectMember(projectDto);
		logger.info("프로젝트 매니저 자동 수락");
		
		//프로젝트 모집 날짜 넣기 
		projectDto.setType(ProjectUtil.RECRUIT);
		projectDto.setState_code(projectDto.getRecruit_state_code());
		projectDao.insertProjectRecruitDate(projectDto);
		logger.info("프로젝트 모집날짜 생성");
		//프로젝트 날짜 넣기 
		projectDto.setType(ProjectUtil.PROJECT);
		projectDto.setState_code(projectDto.getProject_state_code());
		projectDao.insertProjectDate(projectDto);
		logger.info("프로젝트 날짜 생성");
	}
	
	/**
	 * 작성일 : 2017. 5. 17.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 리스트 불러오기
	 */
	public List<ProjectDto> getProjectList(ProjectDto projectDto) {
		
		List<ProjectDto> projectList = projectDao.getProjectList(projectDto);
	
		Map<Integer,ProjectDto> map  = new HashMap<>();
		
		if(!projectList.isEmpty()){
			
			//프로젝트 별로 직무 합치기 
			for(int i = 0 ; i <projectList.size();i++){
				
				int key = projectList.get(i).getProject_id();
				
				ProjectDto dto;
				Map<String,String> departMap;
				
				if(!map.containsKey(key)){//map 에 키가 존재할때
					dto = projectList.get(i);
					departMap = new HashMap<>();
				}else{
					dto = map.get(key);
					departMap = dto.getDepartMap();
				}

				departMap.put(projectList.get(i).getDepart_name(), String.valueOf(projectList.get(i).getUsercount()));
				dto.setDepartMap(departMap);
				
				map.put(key, dto);
			}
		}
		
		//프로젝트 별로 직무 행 -> 열로 합치기 
		List<ProjectDto> resultList = new ArrayList<>(map.values());
		
		resultList  = resultList.subList(projectDto.getPage(), projectDto.getCount());
		return resultList;
	}
	
	/**
	 * 작성일 : 2017. 7. 3.
	 * 작성자 : 김민지 
	 * 설 명  : 나의 프로젝트 전체 리스트 가져오기
	 * @return 
	 */
	public List<ProjectDto> getMyProjectList(ProjectDto projectDto) {
		
		List<ProjectDto> projectList = projectDao.getMyProjectList(projectDto);
	
		Map<Integer,ProjectDto> map  = new HashMap<>();
		
		if(!projectList.isEmpty()){
			
			//프로젝트 별로 직무 합치기 
			for(int i = 0 ; i <projectList.size();i++){
				
				int key = projectList.get(i).getProject_id();
				
				ProjectDto dto;
				Map<String,String> departMap;
				
				if(!map.containsKey(key)){//map 에 키가 존재할때
					dto = projectList.get(i);
					departMap = new HashMap<>();
				}else{
					dto = map.get(key);
					departMap = dto.getDepartMap();
				}

				departMap.put(projectList.get(i).getDepart_name(), String.valueOf(projectList.get(i).getUsercount()));
				dto.setDepartMap(departMap);
				
				map.put(key, dto);
			}
		}
		
		//프로젝트 별로 직무 행 -> 열로 합치기 
		List<ProjectDto> resultList = new ArrayList<>(map.values());
		
		resultList  = resultList.subList(projectDto.getPage(), projectDto.getCount());
		
		return resultList;
	}
	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 상세  가져오기
	 */
	public ProjectDto getProjectDetail(ProjectDto projectDto) {
		
		ProjectDto projectList = projectDao.getProjectDetail(projectDto);
		return projectList;
	}

	/**
	 * 작성일 : 2017. 5 .24
	 * 작성자 : 김민지
	 * 설  명 : 직무 리스트 가져오기
	 */
	public List<ProjectDto> getProjectDePDetail(ProjectDto projectDto) {
		
		List<ProjectDto> projectDePDetail = projectDao.getProjectDePDetail(projectDto);
		return projectDePDetail;
	}

	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 상태코드 확인 
	 */

	public String selectProjectState(ProjectDto projectDto) {
		return projectDao.selectProjectState(projectDto);
	}

	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트별 신청한 사람 있는지 체크 
	 */
	public int selectProjectMemberCnt(ProjectDto projectDto) {
		return projectDao.selectProjectMemberCnt(projectDto);
	}

	/**
	 * 작성일 : 2017. 6 .5
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 삭제하기
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteProjectAll(ProjectDto projectDto) {
		projectDao.deleteProjectMember(projectDto);//프로젝트 참여자 삭제
		projectDao.deleteProjectDePDetail(projectDto);//프로젝트 상세 직무 삭제
		projectDao.deleteProject(projectDto);//프로젝트 삭제
	}

	/**
	 * 작성일 : 2017. 6 .8
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 수정하기
	 */
	@Transactional(rollbackFor=Exception.class)
	public void updateProjectAll(ProjectDto projectDto){
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		
		//현재 날짜 
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String his_date = dayTime.format(new Date(time));
		
		projectDto.setHis_date(his_date);
		projectDto.setProject_his_date(his_date);
		
		projectDto.setMod_id(user.getUsername());
		projectDto.setReg_id(user.getUsername());
		
		//프로젝트 정보 수정
		projectDao.updateProject(projectDto);
		logger.info("프로젝트 정보수정");
		
		//프로젝트 상세 직무 삭제 
		projectDao.deleteProjectDePDetail(projectDto);
		logger.info("프로젝트 직무 전체 삭제");
		
		//프로젝트별 직무 생성
		if(projectDto.getDepartMap() != null && !projectDto.getDepartMap().isEmpty()){
			for(String key : projectDto.getDepartMap().keySet()){
				HashMap<String, String> map = new HashMap<>();
				
				map.put("project_id", String.valueOf(projectDto.getProject_id()));
				map.put("depart_code", key);
				map.put("usercount", projectDto.getDepartMap().get(key));
				//프로젝트 상세 직무 수정
				projectDao.insertProjectDepart(map);
			}
		}
		logger.info("프로젝트 직무 전체 insert");
		
		//프로젝트 모집 날짜 넣기 
		projectDto.setType(ProjectUtil.RECRUIT);
		projectDto.setState_code(projectDto.getRecruit_state_code());
		projectDao.insertProjectRecruitDate(projectDto);
		logger.info("프로젝트 모집 날짜 넣기 ");
		
		//프로젝트 날짜 넣기 
		projectDto.setType(ProjectUtil.PROJECT);
		projectDto.setState_code(projectDto.getProject_state_code());
		projectDao.insertProjectDate(projectDto);
		logger.info("프로젝트 날짜 넣기 ");
	}

	/**
	 * 작성일 : 2017. 6 .8
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 신청하기
	 */
	public void insertProjectMember(ProjectDto projectDto){
		projectDao.insertProjectMember(projectDto);
	}

	/**
	 * 작성일 : 2017. 6 .16
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트  사람리스트 가져오기
	 */
	public List<ProjectDto> getProjectMemberList(ProjectDto projectDto) {
		return projectDao.getProjectMemberList(projectDto);
	}

	/**
	 * 작성일 : 2017. 6 .19
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 멤버 정보 가져오기
	 */
	public ProjectDto getMemberInfo(ProjectDto projectDto) {
		return projectDao.getMemberInfo(projectDto);
	}

	/**
	 * 작성일 : 2017. 6 .19
	 * 작성자 : 김민지
	 * 설  명 : 프로잭트 인원 상태코드 수정하기 
	 */
	public int updateProjectMember(ProjectDto projectDto) {
		return projectDao.updateProjectMember(projectDto);
	}
	/**
	 * 작성일 : 2017. 6 .27
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 상태 코드 가져오기
	 */
	public List<ProjectDto> getStateCode(String type) {
		return projectDao.getStateCode(type);
	}
	
	/**
	 * 작성일 : 2017. 7 .7
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 모집 날짜 insert 하기 
	 */
	public int insertProjectRecruitDate(ProjectDto projectDto){
		return projectDao.insertProjectRecruitDate(projectDto);
	}
	
	/**
	 * 작성일 : 2017. 7 .7
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 프로젝트 날짜 insert 하기 
	 */
	public int insertProjectDate(ProjectDto projectDto){
		return projectDao.insertProjectDate(projectDto);
	}
	
	/**
	 * 작성일 : 2017. 7 .7
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 hisDate 수정하기  
	 */
	public int updateProjectHisDate(ProjectDto projectDto) {
		return projectDao.updateProjectHisDate(projectDto);
	}

	/**
	 * 작성일 : 2017. 7 .7
	 * 작성자 : 김민지
	 * 설  명 : 프로젝트 시작/종료 수정하기  
	 */
	@Transactional(rollbackFor=Exception.class)
	public int projectStart(ProjectDto projectDto) {
		
		int value = 0;
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		
		projectDto.setReg_id(user.getUsername());//로그인 한 사람
		projectDto.setMod_id(user.getUsername());//로그인 한 사람
		
		//현재 날짜 
		long time = System.currentTimeMillis();
		
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String hisDate = dayTime.format(new Date(time));
		
		String hisYmd  = hisDate.substring(0,10);
		
		// 모집 상태 넣기
		if(ProjectUtil.RECRUIT.equals(projectDto.getType())){
			
			projectDto.setHis_date(hisDate);
			projectDao.updateProjectHisDate(projectDto);//프로젝트 his_date 업데이트 하기 
			
			if(ProjectUtil.RECRUITING.equals(projectDto.getState_code())){//모집 시작 일때 시작날짜를 현재 날짜로 변경
				projectDto.setRecruit_start_date(hisYmd);
				logger.info("프로젝트 모집 시작 ");
			}else{//모집 종료일때 시작일짜를 현재 날짜로 변경 
				projectDto.setRecruit_end_date(hisYmd);
				value = 1;
				logger.info("프로젝트 모집 종료 ");
			}
			projectDao.insertProjectRecruitDate(projectDto);

		}else{
			
			projectDto.setProject_his_date(hisDate);
			projectDao.updateProjectHisDate(projectDto);//프로젝트 his_date 업데이트 하기 
			
			if(ProjectUtil.START.equals(projectDto.getState_code())){//프로젝트 시작 일때 시작날짜를 현재 날짜로 변경
				projectDto.setProject_start_date(hisYmd);
				logger.info("프로젝트 시작 ");
			}else{//모집 종료일때 시작일짜를 현재 날짜로 변경 
				projectDto.setProject_end_date(hisYmd);
				value = 1;
				logger.info("프로젝트 종료 ");
			}
			projectDao.insertProjectDate(projectDto);
		}
		return value;
	}
	/**
	 * 작성일 : 2017. 7. 12.
	 * 작성자 : 김민지 
	 * 설 명  : 프로젝트 전체 리스트 카운트
	 * @return 
	 */
	public int getProjectListTotalCount(ProjectDto projectDto){
		List<ProjectDto> projectList = projectDao.getProjectList(projectDto);
		
		Map<Integer,ProjectDto> map  = new HashMap<>();
		
		if(!projectList.isEmpty()){
			
			//프로젝트 별로 직무 합치기 
			for(int i = 0 ; i <projectList.size();i++){
				
				int key = projectList.get(i).getProject_id();
				
				ProjectDto dto;
				Map<String,String> departMap;
				
				if(!map.containsKey(key)){//map 에 키가 존재할때
					dto = projectList.get(i);
					departMap = new HashMap<>();
				}else{
					dto = map.get(key);
					departMap = dto.getDepartMap();
				}

				departMap.put(projectList.get(i).getDepart_name(), String.valueOf(projectList.get(i).getUsercount()));
				dto.setDepartMap(departMap);
				
				map.put(key, dto);
			}
		}
		
		//프로젝트 별로 직무 행 -> 열로 합치기 
		List<ProjectDto> resultList = new ArrayList<>(map.values());
		
		return resultList.size();
	}
	
	/**
	 * 작성일 : 2017. 7. 12.
	 * 작성자 : 김민지 
	 * 설 명  : 나의 프로젝트 전체 리스트 카운트
	 * @return 
	 */
	public int getMyProjectListTotalCount(ProjectDto projectDto){
		List<ProjectDto> projectList = projectDao.getMyProjectList(projectDto);
		
		Map<Integer,ProjectDto> map  = new HashMap<>();
		
		if(!projectList.isEmpty()){
			
			//프로젝트 별로 직무 합치기 
			for(int i = 0 ; i <projectList.size();i++){
				
				int key = projectList.get(i).getProject_id();
				
				ProjectDto dto;
				Map<String,String> departMap;
				
				if(!map.containsKey(key)){//map 에 키가 존재할때
					dto = projectList.get(i);
					departMap = new HashMap<>();
				}else{
					dto = map.get(key);
					departMap = dto.getDepartMap();
				}

				departMap.put(projectList.get(i).getDepart_name(), String.valueOf(projectList.get(i).getUsercount()));
				dto.setDepartMap(departMap);
				
				map.put(key, dto);
			}
		}
		
		//프로젝트 별로 직무 행 -> 열로 합치기 
		List<ProjectDto> resultList = new ArrayList<>(map.values());
		
		return resultList.size();
	}
	
}
