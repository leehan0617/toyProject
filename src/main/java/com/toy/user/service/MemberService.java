package com.toy.user.service;

import com.toy.user.model.MemberDto;
import com.toy.user.model.MemberVo;

public interface MemberService {
	
	public void insertMember(MemberVo vo) throws Exception;
    public MemberDto getMyInfo(String user_id) throws Exception;
    public void updateMember(MemberVo vo) throws Exception;

}
