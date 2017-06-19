package com.toy.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@RequestMapping(value="/error/{error}" , method=RequestMethod.GET)
	public String error(@PathVariable String error , Model model) {
		logger.info("error 페이지 접근 {}", error);
		model.addAttribute("error" , error);
		return "error/error";
	}
}
