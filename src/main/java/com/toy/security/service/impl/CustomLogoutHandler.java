package com.toy.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler{
	
	private static Logger customLogger = LoggerFactory.getLogger(CustomLogoutHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		customLogger.info("로그아웃 성공");
		
		// 필요한 경우 response에 referUrl로 redirect 할 수 있다. 
		String referUrl = request.getHeader("Referer");
		customLogger.info("로그아웃 전 url : {} " , referUrl);
		super.onLogoutSuccess(request, response, authentication);
	}
	
}
