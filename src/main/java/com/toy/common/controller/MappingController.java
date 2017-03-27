package com.toy.common.controller;

import org.springframework.stereotype.Controller;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : 페이지 매핑 관련 컨트롤러 (회의 후 결정 아직 미사용)
 */
@Controller
public class MappingController {
	
	/**
	 * 작성일 : 2017. 3. 27.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인 페이지로 이동하는 함수
	 
	@RequestMapping(value= {"/" ,"/login"} , method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	*/
}
