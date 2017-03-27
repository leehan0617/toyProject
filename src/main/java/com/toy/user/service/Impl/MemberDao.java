package com.toy.user.service.Impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.user.model.MemberDto;
import com.toy.user.model.MemberVo;

@Repository("MemberDao")
public class MemberDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/*
	 * 작성일 : 2017.03.10
	 * 작성자 : 김민지
	 * 설명 : 회원가입 insert
	*/
	public void insertMember(MemberVo vo) throws Exception {
		sqlSession.insert("insertMember",vo);
	}
    
	/*
	 * 작성일 : 2017.03.24
	 * 작성자 : 김민지
	 * 설명 : 내 정보 가져오기
	*/
    public MemberDto getMyInfo(String user_id) throws Exception {
        return sqlSession.selectOne("getMyInfo",user_id);
    }
    
    /*
	 * 작성일 : 2017.03.24
	 * 작성자 : 김민지
	 * 설명 : 내 정보 수정하기
	*/
    public void updateMember(MemberVo vo) throws Exception {
        sqlSession.update("updateMember",vo);
    }
    
    /*
	 * 작성일 : 2017.03.24
	 * 작성자 : 김민지
	 * 설명 : 회원가입 할때 초기 권한 셋팅
	*/
	public void insertUserAuth(MemberVo vo) throws Exception {
		sqlSession.insert("insertUserAuth",vo);
	}
	
	/*
	 * 작성일 : 2017.03.27
	 * 작성자 : 김민지
	 * 설명 : 로그인정보가져오기
	*/
    public MemberDto getUserLogin(String user_id) throws Exception {
        return sqlSession.selectOne("getUserLogin",user_id);
    }
    
    /*
	 * 작성일 : 2017.03.27
	 * 작성자 : 김민지
	 * 설명 : 로그인권한정보가져오기
	*/
    public List<MemberDto> getUserLoginAuth(String user_id) throws Exception {
        return sqlSession.selectList("getUserLoginAuth",user_id);
    }
    
}
