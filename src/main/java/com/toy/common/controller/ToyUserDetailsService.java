package com.toy.common.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.toy.user.model.MemberDto;
import com.toy.user.model.MemberVo;
import com.toy.user.service.MemberService;

/*
 * 작성일 : 2017.03.27
 * 작성자	: 김민지
 * 설명 : 로그인하기 dao 로 변경  
*/

@Component
public class ToyUserDetailsService  implements UserDetailsService{

	@Autowired
	MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		
		ArrayList<GrantedAuthority> authorities = null;
		UserDetails user = null;
		try {
			
			MemberVo vo = new MemberVo();
			vo.setUser_id(user_id);
			
			//패스워드 불러오기
			MemberDto dto = memberService.getUserLogin(user_id);
			
			if (dto == null) {
				throw new UsernameNotFoundException("접속자의 정보를 찾을 수 없습니다.");
			}else{//사용자 정보가 있을떄 
				
				//로그인 권한 리스트 불러오기 
				List<MemberDto> authList = memberService.getUserLoginAuth(user_id);
				
				if(!authList.isEmpty()){
					authorities = new ArrayList<GrantedAuthority>();
					
					for(MemberDto memberDto:authList){
						if(memberDto.getAuth_name() != null){
							authorities.add(new SimpleGrantedAuthority(memberDto.getAuth_name()));
						}
					}
					
				}
				//사용자의 사용가능여부/사용자 계정이 만료 여부 /사용자의 자격 증명 (암호)이 만료 여부/사용자가 잠겨 있는지 여부
				user = new User(user_id, dto.getPassword(), dto.getEnabled() ,true, true, true, authorities);
			}
			
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
