package com.toy.security.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toy.user.model.MemberVo;
import com.toy.user.service.MemberService;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	MemberService memberService;
		
	@RequestMapping(value= {"/" ,"/login"} , method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/logout" , method=RequestMethod.GET)
	public String logout(HttpServletRequest request , HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);
		
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/main")
	public String main(Model model) {
		model.addAttribute("user" , getPrincipal());
		
		return "add";
	}
	
	@RequestMapping(value="/login/add")
	public String add(Model model) {
		return "add";
	}
	
	@RequestMapping(value="/login/save")
	public String save(MemberVo vo) throws Exception {
		System.out.println(vo.getUser_id());
		System.out.println(vo.getUser_name());
		System.out.println(vo.getPassword());
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));//암호화
		System.out.println(vo.getPassword());
		System.out.println(vo);
		memberService.insertMember(vo);
		return "redirect:/login";
	}
	
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		
		return userName;
	}
}
