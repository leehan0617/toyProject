package com.toy.user.service;

import java.util.List;

import com.toy.user.model.MemberDto;
import com.toy.user.model.MemberVo;

public interface MemberService {
	
	/*
	 * 작성일 : 2017.03.10
	 * 작성자 : 김민지
	 * 설명 : 회원가입 insert
	*/
	public void insertMember(MemberVo vo) throws Exception;
	
	/*
	 * 작성일 : 2017.03.24
	 * 작성자 : 김민지
	 * 설명 : 내 정보 가져오기
	*/
	public MemberDto getMyInfo(String user_id) throws Exception;
	
	/*
	 * 작성일 : 2017.03.24
	 * 작성자 : 김민지
	 * 설명 : 내 정보 수정하기
	*/
    public void updateMember(MemberVo vo) throws Exception;
    
    /*
	 * 작성일 : 2017.03.27
	 * 작성자 : 김민지
	 * 설명 : 로그인 정보 가져오기
	*/
	public MemberDto getUserLogin(String user_id) throws Exception;
	
	 /*
		 * 작성일 : 2017.03.24
		 * 작성자 : 김민지
		 * 설명 : 로그인 권한 정보 가져오기
	*/
	public List<MemberDto> getUserLoginAuth(String user_id) throws Exception;


}
