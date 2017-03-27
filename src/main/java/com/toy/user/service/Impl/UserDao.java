package com.toy.user.service.Impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toy.user.model.UserAuthority;
import com.toy.user.model.UserDto;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public UserDto getUser(String userId) {
		return sqlSession.selectOne("user.getUser" , userId);
	}

	public List<UserAuthority> getAuthorities(String userId) {
		return sqlSession.selectList("user.getAuthorities" , userId);
	}

}
