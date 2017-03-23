package com.toy.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toy.user.model.MemberDto;
import com.toy.user.model.MemberVo;
import com.toy.user.service.MemberService;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	MemberService memberService;
		
	@RequestMapping(value= {"/" ,"/login"} , method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/logout" , method=RequestMethod.GET)
	public String logout(HttpServletRequest request , HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);
		
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/main")
	public String main(Model model) {
		model.addAttribute("user" , getPrincipal());
		
		return "main";
	}
	
	@RequestMapping(value="/add")
	public String add(Model model) {
		return "main";
	}
	
	@RequestMapping(value="/save")
	public String save(MemberVo vo) throws Exception {
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));//암호화
		memberService.insertMember(vo);
        return "redirect:/main";

	}
	
	    //접근권한이 없을 경우에 이동할 페이지
    @RequestMapping("/user/denied")
    public String denied(Model model, Authentication auth, HttpServletRequest req){
        //권한없는 사용자가 접근하면 security에서 해당 request에 AccessDeniedException 전달
        //이떄의 속성명--ACCESS_DENIED_403
        AccessDeniedException ade = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
        model.addAttribute("auth", auth);
        model.addAttribute("errMsg", ade);
        return "common/error/denied";
    }
    
    //접근권한이 없을 경우에 이동할 페이지
    @RequestMapping("/admin")
    public String adminTest(){
        System.out.println("admin test");
        return "/admin";
    }

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		
		return userName;
	}
	
    @RequestMapping(value="/updateMyInfo" , method=RequestMethod.GET)
    public String updateMyInfo(HttpServletRequest request ,Model model) throws Exception {
        String user_id = getPrincipal();
        MemberDto myinfo = memberService.getMyInfo(user_id);
        model.addAttribute("memberDto", myinfo);
        model.addAttribute("user" , user_id);
        return "updateMyInfo";
    }
    
    @RequestMapping(value="/update")
    public String update(MemberVo vo) throws Exception {
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));//암호화
        memberService.updateMember(vo);
        return "main";
    }

}
