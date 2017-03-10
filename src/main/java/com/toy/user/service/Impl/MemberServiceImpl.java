package com.toy.user.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.toy.user.model.MemberVo;
import com.toy.user.service.MemberService;

@Service("MemberService")
public class MemberServiceImpl implements MemberService{
	
	
	@Resource(name="MemberDao")
	MemberDao memberDao;
	
	public void insertMember(MemberVo vo) throws Exception {
		  memberDao.insertMember(vo);
	}
}
