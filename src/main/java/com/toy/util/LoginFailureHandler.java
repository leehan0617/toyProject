package com.toy.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 작성일 : 2017. 3. 28.
 * 작성자 : 이한빈
 * 설  명 : 로그인 실패시 처리할 Handler 클래스
 */
public class LoginFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String message = "";
		System.out.println("에러접근");
		if(exception.getClass() == UsernameNotFoundException.class) {
			message = "존재하지 않는 유저입니다.";
		} else if (exception.getClass() == BadCredentialsException.class) {
			message = "비밀번호가 틀립니다.";
		} else {
			message = "오류가 발생하였습니다. 관리자에게 문의해주세요.";
		}
		
		request.getRequestDispatcher(String.format("/login?error=%s", message)).forward(request, response);
	}
}
