package com.toy.common.controller;

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
}
