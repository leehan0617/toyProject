package com.toy.user.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.toy.user.model.MemberDto;
import com.toy.user.model.MemberVo;
import com.toy.user.service.MemberService;

@Service("MemberService")
public class MemberServiceImpl implements MemberService{
	
	
	@Resource(name="MemberDao")
	MemberDao memberDao;
	
	/*
	 * 작성일 : 2017.03.10
	 * 작성자 : 김민지
	 * 설명 : 회원가입 insert
	*/
	public void insertMember(MemberVo vo) throws Exception {
		  memberDao.insertMember(vo);
		  vo.setAuth_code("01");//초기 권한 셋팅 (01:Role_user, 02:Role_admin)
		  memberDao.insertUserAuth(vo);//권한 초기 셋팅
	}
	
	/*
	 * 작성일 : 2017.03.24
	 * 작성자 : 김민지
	 * 설명 : 내 정보 가져오기
	*/
    public MemberDto getMyInfo(String user_id) throws Exception {
        return  memberDao.getMyInfo(user_id);
    }
    
	/*
	 * 작성일 : 2017.03.24
	 * 작성자 : 김민지
	 * 설명 : 내 정보 수정하기
	*/
    public void updateMember(MemberVo vo) throws Exception {
        memberDao.updateMember(vo);
    }

}
