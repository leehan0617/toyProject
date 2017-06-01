package com.toy.common.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toy.project.model.ProjectDto;
import com.toy.project.service.IssueService;
import com.toy.user.model.UserDto;
import com.toy.user.service.UserService;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : 페이지 매핑 관련 컨트롤러 (회의 후 결정 아직 미사용)
 */
@Controller
public class MappingController {
	
	private static final Logger logger = LoggerFactory.getLogger(MappingController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private IssueService issueService;
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명 : default 페이지 , 로그인을 할떄 호출되어지는 메소드
	 */
	@RequestMapping(value={"/" , "/logout"})
	public String login(@RequestParam(value="error" , required=false) String error ,
			@RequestParam(value="logout" , required=false) String logout 
			, Model model , HttpServletRequest request) {
		logger.info("MappingController login 메소드 접근");
		
		if(error != null) {
			model.addAttribute("error" , error);
		}else if(logout != null) {
			model.addAttribute("logout" , logout);
		}else {
			model.addAttribute("check" , "checkIN");
		}
		
		return "common/login";
	}
	
	@RequestMapping(value="/login")
	public String test(HttpServletRequest request , HttpServletResponse response) {
		logger.info("로그인로직실행");
		return "common/login";
	}
	
	/**
	 * 작성일 : 2017. 3. 27.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인 성공 이후 프로젝트 페이지 이동 메소드 (제거 고려)
	 */
	@RequestMapping(value="/project" , method=RequestMethod.GET)
	public String project(Model model) {
		logger.info("MappingController project 메소드 접근");
		
		// 회원정보를 가져간다.
		model.addAttribute("userDto" , this.getPrincipal());
		return "project/main";
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 권한정보를 가져오는 메소드
	 */
	private Object getPrincipal() {
		Object user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			user = (UserDto) principal;
		} else {
			user = principal.toString();
		}
		
		return user;
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원가입페이지로 이동하는 메소드
	 */
	@RequestMapping(value="/user/join" , method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 회원수정 페이지로 이동하는 메소드
	 */
	@RequestMapping(value="/user/update/{userId}" , method=RequestMethod.GET) 
	public String update(@PathVariable String userId , Model model) {
		model.addAttribute("userDto" , userService.getUser(userId));
		return "user/update";
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 관리자 페이지 이동 메소드
	 */
	@RequestMapping(value="/admin" , method=RequestMethod.GET)
	public String admin() {
		return "user/admin";
	}
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String hello(Locale locale, Model model) {
	
	  Date date = new Date();
	  DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	
	  String formattedDate = dateFormat.format(date);
	
	  model.addAttribute("serverTime", formattedDate );
	  return "tiles";
	}
	
	/**
	 * 작성일 : 2017. 05.24
	 * 작성자 : 송하람
	 * 설  명 : 이슈페이지
	 */
	@RequestMapping(value="/issue/add" , method=RequestMethod.GET) 
	public String addIssue() {
		return "issue/addIssue";
	}
	
	@RequestMapping(value={"/issue/detail/{projectId}"} , method=RequestMethod.GET)
	public ModelAndView showIssueList(@PathVariable String projectId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("issue/issueList");
		
		//프로젝트에 해당하는 이슈리스트가져오기
//		List<HashMap<String, Object>> memberList = issueService.selectApplyListFromProjectMember(projectId);
//		mav.addObject("projectMember", memberList);
		return mav;
	}
	
	@RequestMapping(value={"/issue/projectlist"} , method=RequestMethod.GET)
	public ModelAndView showProjectList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		UserDto dto = new UserDto();
		dto = (UserDto)this.getPrincipal();
		
		List<ProjectDto> projectList = issueService.selectAllProjectList(dto.getUser_id());
		mav.addObject("projectList", projectList);
		mav.addObject("myInfo", dto);
		
		mav.setViewName("issue/projectList");
		return mav;
	}
}
