package com.toy.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.toy.util.RedisUtil;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("onAuthenticationSuccess 접근");
		logger.info("추후 로그인 시간 및 계정에 관한 정보를 세팅하면 된다.");
		
		redisUtil.test();
		logger.info("test1 {} " , redisUtil.getTest("foo"));
		logger.info("test2 {} " , redisUtil.getTest("foo2"));
		response.sendRedirect("/loginSuccess");
	}	
}
