package com.toy.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 작성일 : 2017. 3. 28.
 * 작성자 : 이한빈
 * 설  명 : 로그인성공 이후 작업에 대한 기능 클래스
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인 성공 후 리다이렉트 하는 메소드
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + "/project");
	}
}
