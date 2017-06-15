package com.toy.user.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.security.model.CustomUser;

@Repository
public class UserDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 작성일 : 2017. 6. 15.
	 * 작성자 : 이한빈
	 * 설  명 : 회원가입 실행하는 쿼리문
	 */
	public int join(CustomUser user) {
		return sqlSession.insert("user.join" , user);
	}
	
	public int update(CustomUser user) {
		return sqlSession.update("user.update" , user);
	}

	public int delete(String username) {
		return sqlSession.delete("user.delete" , username);
	}
	
}
