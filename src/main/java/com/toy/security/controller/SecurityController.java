package com.toy.security.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toy.security.model.CustomUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * 로그인 성공 메소드
	 * @return loginSuccess page
	 */
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

	/**
	 * 로그아웃 메소드
	 * @param request
	 * @param response
	 * @return redirect login page
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request , HttpServletResponse response) {
		logger.info("logout method 접근");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request , response , auth);
			return "redirect:/login?logout=success";
		}

		return "redirect:/login";
	}
}
