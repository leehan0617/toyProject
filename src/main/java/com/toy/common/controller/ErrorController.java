package com.toy.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	
	 @RequestMapping(value = "/error/{error}", method = RequestMethod.GET)
	 public String error(@PathVariable String error,Model model) {
		 model.addAttribute("error", error);
		  return "/common/error/error";
	 }
	 
	 /**
	  * 작성일 : 2017. 3. 28.
	  * 작성자 : 송하람
	  * 설  명 : 접근권한이 없을 경우에 이동할 페이지
	  */
	 @RequestMapping("/denied")
	 public String denied(Model model ,  Authentication auth , HttpServletRequest req) {
		 AccessDeniedException ade = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
        model.addAttribute("auth", auth);
        model.addAttribute("errMsg", ade);
        return "error/denied";
	 }
}
