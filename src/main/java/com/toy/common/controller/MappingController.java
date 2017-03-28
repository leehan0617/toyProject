package com.toy.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toy.user.model.UserDto;
import com.toy.user.service.UserService;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : 페이지 매핑 관련 컨트롤러 (회의 후 결정 아직 미사용)
 */
@Controller
public class MappingController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명 : default 페이지 , 로그인을 할떄 호출되어지는 메소드
	 */
	@RequestMapping(value={"/" , "/login"} , method=RequestMethod.GET)
	public String login(@RequestParam(value="error" , required=false) String error ,
			@RequestParam(value="logout" , required=false) String logout 
			, Model model , HttpServletRequest request) {
		
		if(error != null) {
			model.addAttribute("error" , error);
		}else if(logout != null) {
			model.addAttribute("logout" , logout);
		}else {
			model.addAttribute("check" , "checkIN");
		}
		
		return "common/login";
	}
	
	/**
	 * 작성일 : 2017. 3. 27.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인 성공 이후 프로젝트 페이지 이동 메소드 (제거 고려)
	 */
	@RequestMapping(value="/project" , method=RequestMethod.GET)
	public String project(Model model) {
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
}
