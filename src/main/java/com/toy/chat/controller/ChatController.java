package com.toy.chat.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
	
	@RequestMapping(value="/chatRoom")
	public String chatRoom(@RequestParam Map<String,Object> hMap) {
		return "chat/chatRoom";
	}
	
	@RequestMapping(value="/stomp")
	public String stomp(@RequestParam Map<String,Object> map) {
		return "chat/stomp";
	}
	
	@MessageMapping("/add")
	@SendTo("/topic/showResult")
	public String addNum(@RequestParam Map<String,Object> map , Principal principal) {
		System.out.println("principal : " + principal.getName());
		Iterator<String> iter = map.keySet().iterator();
		
		while(iter.hasNext()) {
			String key = iter.next();
			System.out.println("key : " + key + ",value : " + map.get(key));
		}
		
		return (String) map.get("num1");
	}
	
	@MessageMapping("/privateMsg")
	@SendToUser("/topic2/privateMsg")
	public String handlingPrivateUser(String msg) {
		System.out.println("msg : " + msg);
		return "private message : " + msg;
	}
	
	@MessageMapping("/add2")
	@SendTo("/topic3/otherMsg")
	public String otherMsg(@RequestParam Map<String,Object> map) {
		System.out.println("check otherMsg" + map.get("msg"));
		return (String) map.get("msg") + "eee";
	}
	
	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handlingException (IllegalStateException e) {
		return e.getMessage();
	}
}
