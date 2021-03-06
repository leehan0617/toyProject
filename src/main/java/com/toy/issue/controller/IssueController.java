package com.toy.issue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.toy.issue.model.IssueDto;
import com.toy.issue.model.projectMemberDto;
import com.toy.issue.service.IssueService;
import com.toy.security.model.CustomUser;
import com.toy.util.PagingUtil;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 송하람
 * 설  명 : Issue에 관한 Controller
 */
@Controller
public class IssueController {
	@Autowired
	IssueService issueService;
	private static final Logger logger = LoggerFactory.getLogger(IssueController.class);
	
	private static final String REDIRECT_URL = "redirect:/issue/detail/";
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value={"/issue/addIssue/{projectId}"}, method=RequestMethod.POST)
	public String showProjectList(HttpServletRequest request, @PathVariable String projectId) {
		
		logger.info(projectId);
		return REDIRECT_URL+projectId;
	}

	/**
	 * 작성일 : 2017. 05.24
	 * 작성자 : 송하람
	 * 설  명 : 이슈 생성
	 * @throws Exception 
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/issue/add" , method=RequestMethod.POST) 
	public String addIssue(IssueDto dto){
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// 나를 포함하여 userlist에 넣어줌
		CustomUser user = (CustomUser) au.getPrincipal();
		
		List<String> userList;
		if (dto.getUserList() == null) {
			userList = new ArrayList<>();
		}
		else {
			userList = dto.getUserList();
		}
		
		userList.add(user.getUsername());
		dto.setReg_id(user.getUsername());
		dto.setState_code("wait");
		dto.setType("issue");
		dto.setUserList(userList);
		
		issueService.insertIssue(dto);
		return REDIRECT_URL+dto.getProject_id()+"/"+dto.getProjectName()+"/1";
	}
	/**
	 * 작성일 :  2017. 06. 15
	 * 작성자 : 송하람
	 * 설  명 : 이슈 상세보기
	 * @throws Exception 
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value={"/issue/detail/{projectId}/{projectName}/{seq}"} , method=RequestMethod.GET)
	public ModelAndView showIssueList(@PathVariable(value="projectId") String projectId, @PathVariable(value="projectName") String projectName, @PathVariable(value="seq") String seq) {
		//한 화면에 출력하고 싶은 목록 갯수
		final int countPerPage = 7;
		// 한 화면에 출력하고 싶은 페이지 갯수
		final int pageNumber = 5;
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("issue/issueList");
		
		IssueDto issuedto = new IssueDto();
		issuedto.setProject_id(projectId);
		issuedto.setPage((Integer.parseInt(seq)-1)*countPerPage);
		issuedto.setCount(countPerPage);
		
		//프로젝트에 해당하는 이슈리스트가져오기
		List<IssueDto> issueList = issueService.selectIssueList(issuedto);
		mav.addObject("issueList", issueList);
		
		List<projectMemberDto> memberList = issueService.selectApplyListFromProjectMember(projectId);
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		mav.addObject("projectName", projectName);
		mav.addObject("myInfo", user);
		mav.addObject("memberList", memberList);
		mav.addObject("state_code", "all");
		
		//현재페이지
		mav.addObject("seq", seq);
		//총갯수
		int totalCount = issueService.selectIssueTotalCount(issuedto);
			
		Double nowPage = Double.parseDouble(seq);
		
		PagingUtil page = new PagingUtil(countPerPage, pageNumber, nowPage, totalCount);
		
		mav.addObject("pageCount", page.getPageCount());
		mav.addObject("nextPage", page.getNextPage());
		mav.addObject("prevPage", page.getPrevPage());
		
		//시작페이지
		mav.addObject("nowBlockFirst", page.getNowBlockFirst());
		//마지막페이지
		mav.addObject("nowBlockLast", page.getNowBlockLast());
		mav.addObject("url", "/issue/detail/"+projectId+"/"+projectName);
		return mav;
	}
	/**
	 * 작성일 : 2017. 06. 15
	 * 작성자 : 송하람
	 * 설  명 : 이슈 삭제
	 * @throws Exception 
	 */
	@PreAuthorize("(#issuedto.reg_id == principal.Username)")
	@RequestMapping(value={"/issue/delete"}, method=RequestMethod.POST)
	public String deleteIssue(HttpServletRequest request, IssueDto issuedto) {
		issueService.deleteIssue(issuedto.getIssue_id());
		return REDIRECT_URL+issuedto.getProject_id();
	}
	
	/**
	 * 작성일 : 2017. 06. 15
	 * 작성자 : 송하람
	 * 설  명 : 이슈상태변경
	 * @throws Exception 
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value={"/issue/changeIssue"}, method=RequestMethod.POST)
	public String changeIssue(HttpServletRequest request, IssueDto dto) {
		issueService.insertIssueHistory(dto);
		return REDIRECT_URL+dto.getProject_id();
	}
	
	/**
	 * 작성일 : 2017. 06. 27
	 * 작성자 : 송하람
	 * 설  명 : 이슈 update
	 * @throws Exception 
	 */
	@PreAuthorize("(#issuedto.reg_id == principal.Username)")
	@RequestMapping(value={"/issue/update"}, method=RequestMethod.POST)
	public String updateIssue(HttpServletRequest request, IssueDto dto) {
		issueService.updateIssue(dto);
		return REDIRECT_URL+dto.getProject_id() + "/" + dto.getProjectName();
	}
	
	/**
	 * 작성일 : 2017. 06. 27
	 * 작성자 : 송하람
	 * 설  명 : 이슈 검색
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value={"/issue/search"}, method=RequestMethod.GET)
	public ModelAndView searchIssue(HttpServletRequest request, IssueDto dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("issue/issueList");
		
		//프로젝트에 해당하는 이슈리스트가져오기
		List<IssueDto> issueList = issueService.selectIssueList(dto);
		mav.addObject("issueList", issueList);
		
		List<projectMemberDto> memberList = issueService.selectApplyListFromProjectMember(dto.getProject_id());
		
		// 로그인정보를 가져온다.
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		mav.addObject("myInfo", user);
		mav.addObject("memberList", memberList);
		mav.addObject("projectId", dto.getProject_id());
		mav.addObject("state_code", dto.getState_code());
		mav.addObject("projectName", dto.getProjectName());
		
		if (dto.getUser_id() != null) {
			mav.addObject("viewMyIssueFlag", 1);
		}
		return mav;
	}
	

}
