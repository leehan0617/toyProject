package com.toy.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * 작성일 : 2017. 3. 28.
 * 작성자 : 이한빈
 * 설  명 : 로그인성공 이후 작업에 대한 기능 클래스
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인 성공 후 리다이렉트 하는 메소드
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("LoginSuccess Handler 접근");
		logger.info("auth type : " + request.getAuthType());
//		response.sendRedirect(request.getContextPath() + "/project");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
