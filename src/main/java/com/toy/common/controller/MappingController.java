package com.toy.common.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toy.issue.model.issueDto;
import com.toy.issue.model.projectMemberDto;
import com.toy.project.model.ProjectDto;
import com.toy.project.service.IssueService;
import com.toy.security.model.CustomUser;
import com.toy.security.service.UserService;
import com.toy.user.model.UserDto;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : 페이지 매핑 관련 컨트롤러 (회의 후 결정 아직 미사용)
 */
@Controller
public class MappingController {
	
	private static final Logger logger = LoggerFactory.getLogger(MappingController.class);
	
	@Resource(name="userService")
	private UserService userSerivce;
	
	@Autowired
	private IssueService issueService;
	
	/**
	 * 작성일 : 2017. 6. 5.
	 * 작성자 : 이한빈
	 * 설  명 : 서버가 최초 켜질때 실행되는 메소드
	 */
	@RequestMapping(value="/")
	public String loadOnStart(HttpServletRequest request , HttpServletResponse response) {
		logger.info("MappingController - loadOnStart 메소드 접근");
		return "common/start";
	}
	
	/**
	 * 작성일 : 2017. 6. 5.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인페이지 이동 메소드
	 */
	@RequestMapping(value="/login")
	public String login(@RequestParam(value="error" , required=false) String error ,
			@RequestParam(value="logout" , required=false) String logout 
			, Model model , HttpServletRequest request , HttpServletResponse response) {
		logger.info("MappingController - login 메소드 접근");
		
		if(error != null) {
			model.addAttribute("error" , error);
		} else if (logout != null) {
			model.addAttribute("logout" , logout);
		}
		
		return "common/login";
	}
	
//	@Secured({"01" , "02"})
	@RequestMapping(value="/loginSuccess")
	public String loginSuccess(HttpSession session) {
		logger.info("로그인 성공");
		
		Object userDto =SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		// 권한 확인을 할려면 이런식으로 해야함
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		// 권한 리스트
		Collection<? extends GrantedAuthority> list = au.getAuthorities();
		// userId 
		CustomUser user = (CustomUser) au.getPrincipal();
		// pwd null 값을 return 한다.
		String pwd = String.valueOf(au.getCredentials());
		
		
		Iterator<?> iter = list.iterator();
		while(iter.hasNext()) {
			logger.info("test {}" , iter.next());
		}
		
		logger.info("loginSuccess {} , {}" , session.getId() , userDto.toString());
		logger.info("login Info {} , {} " , user.getUsername() , user.getPassword());
		if(userDto instanceof UserDetails) {
			logger.info("test2 {} " , ((UserDetails) userDto).getUsername());
		} else {
			logger.info("test3 {} " , userDto.toString());
			logger.info("test4 {}  , {} " , au.getName() , au.getCredentials());
		}
		
		session.setAttribute("userLoginInfo", userDto);
		return "common/loginSuccess";
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명 : logout 페이지 
	 */
	@RequestMapping(value="/logout")
	public String logout(@RequestParam(value="error" , required=false) String error ,
			@RequestParam(value="logout" , required=false) String logout 
			, Model model , HttpServletRequest request , HttpServletResponse response) {
		logger.info("MappingController - logout 메소드 접근");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			logger.info("auth not null");
			new SecurityContextLogoutHandler().logout(request, response, auth);
			logger.info("logout 성공");
			return "redirect:/login?logout=success";
		} 
		
		return "redirect:/login";
	}
	
	@RequestMapping(value="loginDuplicate")
	public String loginDuplicate() {
		logger.info("MappingController - loginDuplicate 메소드 접근");
		
		return "common/login";
	}
	/**
	 * 작성일 : 2017. 3. 27.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인 성공 이후 프로젝트 페이지 이동 메소드 (제거 고려)
	 */
	@Secured("02")
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
			user = (CustomUser) principal;
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
//		model.addAttribute("userDto" , userService.getUser(userId));
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
		List<issueDto> issueList = issueService.selectIssueList(projectId);
		mav.addObject("issueList", issueList);
	
		
		UserDto dto = new UserDto();
		dto = (UserDto)this.getPrincipal();
		mav.addObject("myInfo", dto);
		
		List<projectMemberDto> memberList = issueService.selectApplyListFromProjectMember(projectId);
		mav.addObject("memberList", memberList);
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
