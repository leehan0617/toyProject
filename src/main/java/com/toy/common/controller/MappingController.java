package com.toy.common.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : 페이지 매핑 관련 컨트롤러 (회의 후 결정 아직 미사용)
 */
@Controller
public class MappingController {
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명 : default 페이지 , 로그인을 할떄 호출되어지는 메소드
	 */
	@RequestMapping(value={"/" , "/login"} , method=RequestMethod.GET)
	public String login(@RequestParam(value="error" , required=false) String error ,
			@RequestParam(value="logout" , required=false) String logout , Model model) {
		model.addAttribute("error" , error);
		model.addAttribute("logout" , logout);
		
		return "common/login";
	}
	
	/**
	 * 작성일 : 2017. 3. 27.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인 성공 이후 메인페이지 이동 메소드 (제거 고려)
	 */
	@PreAuthorize("authenticated")
	@RequestMapping(value="/main")
	public String main(Model model) {
		return "project/main";
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원가입페이지로 이동하는 메소드
	 */
	@RequestMapping(value="/join" , method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/{userId}/update" , method=RequestMethod.GET) 
	public String update() {
		return "user/update";
	}
}
