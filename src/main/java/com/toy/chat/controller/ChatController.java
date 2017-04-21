package com.toy.chat.controller;

import java.util.Iterator;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
	public String addNum(@RequestParam Map<String,Object> map) throws Exception {
		Iterator<String> iter = map.keySet().iterator();
		
		while(iter.hasNext()) {
			String key = iter.next();
			System.out.println("key : " + key + ",value : " + map.get(key));
		}
		
		return (String) map.get("num1");
	}
	
	@MessageMapping("/add2")
	@SendTo("/topic/showResult2")
	public String addNum2(@RequestParam Map<String,Object> map) throws Exception {
		
		return "ddd2";
	}
}
