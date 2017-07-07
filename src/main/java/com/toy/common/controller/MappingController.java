package com.toy.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 작성일 : 2017. 3. 27.
 * 작성자 : 이한빈
 * 설  명 : 페이지 매핑 관련 컨트롤러 (회의 후 결정 아직 미사용)
 */
@Controller
public class MappingController {
	
	private static final Logger logger = LoggerFactory.getLogger(MappingController.class);
	
	/**
	 * 작성일 : 2017. 6. 5.
	 * 작성자 : 이한빈
	 * 설  명 : 서버가 최초 켜질때 실행되는 메소드
	 */
	@RequestMapping(value="/")
	public String loadOnStart() {
		logger.info("MappingController - loadOnStart 메소드 접근");
		
		return "common/login";
	}
}
