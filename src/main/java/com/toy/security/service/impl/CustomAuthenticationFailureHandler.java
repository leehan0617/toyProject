package com.toy.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{
	
	private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		int errorCode = 0;
		logger.info("CustomAuthenticationFailureHandler - 로그인 실패");
		
		if(exception.getClass().equals(InternalAuthenticationServiceException.class)) {
			errorCode = 1;
			logger.info("로그인 실패 : 아이디 없음");
		} else if (exception.getClass().equals(BadCredentialsException.class)) {
			errorCode = 2;
			logger.info("로그인 실패 : 비밀번호 틀림");
		} else if (exception.getClass().equals(DisabledException.class)) {
			errorCode = 3;
			logger.info("로그인 실패 : 사용할수 없는 계정");
		} else if (exception.getClass().equals(AccountExpiredException.class)) {
			errorCode = 4;
			logger.info("로그인 실패 : 만료된 계정");
		} else if (exception.getClass().equals(CredentialsExpiredException.class)) {
			errorCode = 5;
			logger.info("로그인 실패 : 계정권한 만료");
		} else if (exception.getClass().equals(LockedException.class)) {
			errorCode = 6;
			logger.info("로그인 실패 : 잠긴 계정");
		} else {
			logger.error("서버에러 발생");
		}
		
		response.setCharacterEncoding("UTF-8");
		response.sendRedirect("/login?error=" + errorCode);
	}
}
