package com.toy.chat.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
	
	@RequestMapping(value="/chatRoom")
	public String chatRoom(@RequestParam Map<String,Object> hMap) {
		return "chat/chatRoom";
	}
}
