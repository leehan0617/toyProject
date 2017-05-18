package com.toy.chat.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = {"com.toy.chat.controller"})
public class AppWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic" ,"/topic2" , "/topic3"); // // 서버가 클라이언트에게 보낼때 prefix 설정 (전역)
		config.setApplicationDestinationPrefixes("/calcApp"); // 클라이언트가 서버에게 메세지를 보낼떄 prefix 설정
		config.setUserDestinationPrefix("/user"); // 개인 클라이언트에게 메세지를 보낼때 prefix 설정
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/add").withSockJS(); // endpoint 설정 최초 socket 연결시 사용하는 url이다.
	}
	
}
