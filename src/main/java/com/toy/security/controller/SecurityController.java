package com.toy.security.controller;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toy.security.model.CustomUser;

/**
 * 작성일 : 2017. 6. 16.
 * 작성자 : 이한빈
 * 설  명 : 보안관련 컨트롤러 
 */
@Controller
public class SecurityController {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	/**
	 * 작성일 : 2017. 6. 16.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인 페이지 이동 메소드
	 */
	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value="/login")
	public String login(@RequestParam(value="logout" , required=false) String logout , Model model) {
		logger.info("/login 접근");
		
		if(logout != null) {
			model.addAttribute("logout" , logout);
		}
		
		return "common/login";
	}
	
	@Secured({"ROLE_HUMAN" , "ROLE_ADMIN"})
	@RequestMapping(value="/loginSuccess")
	public String loginSuccess() {
		logger.info("로그인 성공");
		
		// 권한 조회
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> list = au.getAuthorities();
		CustomUser user = (CustomUser) au.getPrincipal();
		
		Iterator<?> iter = list.iterator();
		while(iter.hasNext()) {
			logger.info("{} 의권한 : {}" ,user.getUsername() , iter.next());
		}
		
		return "common/loginSuccess";
	}
}
