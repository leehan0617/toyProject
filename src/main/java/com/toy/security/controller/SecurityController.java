package com.toy.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toy.user.model.MemberDto;
import com.toy.user.model.MemberVo;
import com.toy.user.service.MemberService;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	MemberService memberService;
	
	/**
	 * 작성일 : 2017. 3. 27.
	 * 작성자 : 이한빈
	 * 설  명 : default 페이지 , 로그인을 할때 호출되어지는 메소드
	 */
	@RequestMapping(value= {"/" ,"/login"} , method=RequestMethod.GET)
	public String login(@RequestParam(value="error" , required=false) String error , 
			@RequestParam(value="logout" , required=false) String logout , Model model) {
		model.addAttribute("error" , error);
		model.addAttribute("logout" , logout);
		
		return "common/login";
	}
	
	/**
	 * 작성일 : 2017. 3. 27.
	 * 작성자 : 이한빈
	 * 설  명 : 로그인 성공 이후 메인페이지 이동 메소드 (제거 고려)
	 */
	@PreAuthorize("authenticated")
	@RequestMapping(value="/main")
	public String main(Model model) {
//		model.addAttribute("user" , getUserInfo());
		
		return "project/main";
	}
	
	/**
     * 작성일 : 2017. 3. 27.
     * 작성자 : 이한빈
     * 설  명 : 로그인 한 user 정보를 가져오는 함수
     */
//	private String getUserInfo() {
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		return user;
//	}
	
	/*
	 * 작성일 : 2017.03.10
	 * 작성자	: 김민지
	 * 설명 : 회원가입하는 페이지로 이동  
	*/
	
	@RequestMapping(value="/add")
	public String add(Model model) {
		return "add";
	}
	
	/*
	 * 작성일 : 2017.03.10
	 * 작성자	: 김민지
	 * 설명 : 회원가입 사람 정보 저장하기 
	*/
	@RequestMapping(value="/save")
	public String save(MemberVo vo) throws Exception {
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));//암호화
		memberService.insertMember(vo);
        return "redirect:/login";

	}

	/*
	 * 작성일 : 2017.03.24
	 * 작성자	: 송하람
	 * 설명 : 접근권한이 없을 경우에 이동할 페이지2
	*/
    @RequestMapping("/denied")
    public String denied(Model model, Authentication auth, HttpServletRequest req){
        //권한없는 사용자가 접근하면 security에서 해당 request에 AccessDeniedException 전달
        //이떄의 속성명--ACCESS_DENIED_403
        AccessDeniedException ade = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
        model.addAttribute("auth", auth);
        model.addAttribute("errMsg", ade);
        return "common/error/denied";
    }
    
    
    /*
	 * 작성일 : 2017.03.24
	 * 작성자	: 송하람
	 * 설명 : 접근권한이 없을 경우에 이동할 페이지
	*/
    @RequestMapping("/admin")
    public String adminTest(){
        System.out.println("admin test");
        return "/admin";
    }
    
	/*
	 * 작성일 : 2017.03.24
	 * 작성자	: 김민지
	 * 설명 : 나의 정보 리스트 가져오기 - 내 정보 보기 페이지로 이동 
	*/
	
    @RequestMapping(value="/MyInfo/{userId}" , method=RequestMethod.GET)
    public String getMyInfo(@PathVariable String userId,Model model) throws Exception {
        MemberDto myinfo = memberService.getMyInfo(userId);//
        model.addAttribute("memberDto", myinfo);
        model.addAttribute("user" , userId);
        return "updateMyInfo";
    }
    
    /*
	 * 작성일 : 2017.03.24
	 * 작성자	: 김민지
	 * 설명 : 사원 정보 수정하기  
	*/
    
    @RequestMapping(value="/update")
    public String update(MemberVo vo) throws Exception {
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));//암호화
        memberService.updateMember(vo);
        return "redirect:/main";
    }
    
   

}
