package com.toy.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	private static Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	private String errorUrl;
	
	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException ex) throws IOException, ServletException {
		logger.info("권한없는 페이지 접근");
		response.sendRedirect(errorUrl);
	}

}
