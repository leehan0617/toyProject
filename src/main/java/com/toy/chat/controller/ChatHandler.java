package com.toy.chat.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler {
	// implements InitializingBean
	private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession> ();

	public ChatHandler() {
		super();
	}
	
	/**
	 * 소켓이 연결되면 실행되는 메소드
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessionSet.add(session);
	}
	
	/**
	 * 연결되어 있는 모든 사람들에게 메세지를 보내는 메소드
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session , TextMessage message) throws Exception{
		for(WebSocketSession element : sessionSet){
            element.sendMessage(new TextMessage("echo:" + message.getPayload()));
        }
	}
	
	/**
	 * 클라이언트가 소켓을 끊었을때 실행하는 메소드
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session , CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		
		sessionSet.remove(session);
	}
	
//	public void sendMessage (String message) {
//		for (WebSocketSession session : this.sessionSet) {
//			if (session.isOpen()) {
//				try {
//					session.sendMessage(new TextMessage(message));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		Thread thread = new Thread () {
//			int i = 0;
//			@Override
//			public void run() {
//				while (true) {
//					try {
//						sendMessage ("test" + i++);
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//		
//		thread.start();
//	}
}
