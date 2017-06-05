package com.toy.user.service.impl;

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
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 회원 한명에 관한 정보를 가져오는 쿼리문
	 */
	public UserDto getUser(String userId) {
		return sqlSession.selectOne("user.getUser" , userId);
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 회원에 관한 권한을 가져오는 쿼리문
	 */
	public List<UserAuthority> getAuthorities(String userId) {
		return sqlSession.selectList("user.getAuthorities" , userId);
	}

	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 회원등록을 하는 쿼리문
	 */
	public int joinUser(UserDto userDto) {
		return sqlSession.insert("user.joinUser" , userDto);
	}

	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 회원 수정을 하는 쿼리문
	 */
	public int update(UserDto userDto) {
		return sqlSession.update("user.update" , userDto);
	}

	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 이한빈
	 * 설  명 : 회원 삭제를 하는 쿼리문
	 */
	public int delete(String userId) {
		return sqlSession.delete("user.delete" , userId);
	}

}