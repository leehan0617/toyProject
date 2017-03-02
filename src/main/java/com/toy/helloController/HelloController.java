package com.toy.helloController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
		System.out.println("main");
		
		return "main";
	}
	
}
