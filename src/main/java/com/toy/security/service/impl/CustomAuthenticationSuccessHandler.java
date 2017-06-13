package com.toy.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
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
		
		try{
			logger.info("redis 실행");
			redisUtil.test();
			redisUtil.test2();
			redisUtil.test3();
			logger.info("test1 {} " , redisUtil.getTest("foo"));
			logger.info("test2 {} " , redisUtil.getTest("foo2"));
			logger.info("redis 종료 로그인로직 종료");
		} catch(RedisConnectionFailureException e) {
			logger.error("redis 접속 에러 서버켜져있는지 확인" , e);
			logger.info("window : window+R -> NET START Redis");
			logger.info("Mac OS : update 예정");
			logger.info("Linux : update 예정");
		} catch(Exception e) {
			logger.error("error 발생" , e);
		}
		
		response.sendRedirect("/loginSuccess");
	}	
}
