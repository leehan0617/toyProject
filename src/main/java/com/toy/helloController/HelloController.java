package com.toy.helloController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@RequestMapping("/hello2")
	public String initPage(HttpServletRequest request , HttpServletResponse response){
		System.out.println("test2ddd");
		System.out.println("test322");
		return "hello2";
	}
	
	@RequestMapping("/hello")
	public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name , Model model){
		System.out.println("test");
		model.addAttribute("name" , name);
		return "helloWorld";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		System.out.println("login");
		
		return "login";
	}
	
	@RequestMapping("/main")
	public String main(Model model) {
		model.addAttribute("user" , getPrincipal());
		System.out.println("main");
		
		return "main";
	}
	
	@RequestMapping(value="/logout" , method = RequestMethod.GET)
	public String logout(HttpServletRequest request , HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
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
