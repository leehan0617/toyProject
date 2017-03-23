package com.toy.user.service.Impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.user.model.MemberDto;
import com.toy.user.model.MemberVo;

/**
 * 
 * 해외 영업활동 Plan - Monthly 
 * 
 * @since 2016.05.19
 * @author 김민지
 *
 */
@Repository("MemberDao")
public class MemberDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public void insertMember(MemberVo vo) throws Exception {
		sqlSession.insert("insertMember",vo);
	}
    
    public MemberDto getMyInfo(String user_id) throws Exception {
        return sqlSession.selectOne("getMyInfo",user_id);
    }
    
    public void updateMember(MemberVo vo) throws Exception {
        sqlSession.update("updateMember",vo);
    }
    

}
