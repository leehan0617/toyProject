package com.toy.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toy.security.model.CustomUser;
import com.toy.security.service.UserService;

@Controller
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 작성일 : 2017. 6. 15.
	 * 작성자 : 이한빈
	 * 설  명 : 회원가입 페이지 이동 메소드
	 */
	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value="/user/join" , method=RequestMethod.GET)
	public String join() {
		logger.info("User Controller - 회원가입 페이지 매핑");
		return "user/join";
	}
	
	/**
	 * 작성일 : 2017. 6. 15.
	 * 작성자 : 이한빈
	 * 설  명 : 회원가입 처리하는 메소드
	 */
	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value="/user/join" , method=RequestMethod.POST)
	public String join(CustomUser user) {
		logger.info("User Controller - 회원가입 실행 ");
		userService.join(user);
		return "common/login";
	}
	
	/**
	 * 작성일 : 2017. 6. 20.
	 * 작성자 : 이한빈
	 * 설  명 : 개인정보 페이지로 이동하는 메소드
	 */
	@PreAuthorize("(#username == principal.Username) or hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/user/{username}" , method=RequestMethod.GET)
	public String userPage(@PathVariable String username) {
		return "user/user";
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원 정보를 가져오는 메소드
	 */
	@PreAuthorize("(#username == principal.Username) or hasRole('ROLE_ADMIN')")
	@ResponseBody
	@RequestMapping(value="/user/get/{username}" , method=RequestMethod.GET)
	public CustomUser getUser(@PathVariable String username) {
		return userService.getUser(username);
	}
	
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원정보를 수정하는 메소드
	 */
	@PreAuthorize("(#username == principal.Username) or hasRole('ROLE_ADMIN')")
	@ResponseBody
	@RequestMapping(value="/user/update/{username}" , method=RequestMethod.POST)
	public int update(@PathVariable String username , CustomUser user) {
		return userService.update(user);
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈 
	 * 설 명  : 회원을 삭제하는 메소드
	 */
	@PreAuthorize("(#username == principal.Username) or hasRole('ROLE_ADMIN')")
	@ResponseBody
	@RequestMapping(value="/user/delete/{username}" , method=RequestMethod.POST)
	public int delete(@PathVariable String username) {
		return userService.delete(username);
	}
}
