package com.toy.security.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.security.model.CustomUser;

@Repository
public class SecurityDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 작성일 : 2017. 6. 9.
	 * 작성자 : 이한빈
	 * 설  명 : user 로그인 정보를 가져오는 쿼리문
	 */
	public CustomUser getUser(String username) {
		return sqlSession.selectOne("security.getUser" , username);
	}
	
	/**
	 * 작성일 : 2017. 6. 9.
	 * 작성자 : 이한빈
	 * 설  명 : userId에 대한 권한을 가져오는 쿼리문
	 */
	public List<String> getAuthorities(String username) {
		return sqlSession.selectList("security.getAuthorities" , username);
	}
}
