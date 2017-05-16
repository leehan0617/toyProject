package com.toy.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 작성일 : 2017. 4. 24.
 * 작성자 : 송하람
 * 설  명 : 로그아웃할때 호출하는 클래스
 */
@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{
	@Override
    public void onLogoutSuccess(javax.servlet.http.HttpServletRequest request,
                                javax.servlet.http.HttpServletResponse response,
                                Authentication authentication)
            throws java.io.IOException, javax.servlet.ServletException {
		
		System.out.println("로그아웃!");

        response.sendRedirect("/toyProject/login");

    }

}
