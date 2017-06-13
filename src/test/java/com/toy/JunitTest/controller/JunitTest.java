package com.toy.JunitTest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.toy.user.model.UserDto;

/**
 * 작성일 : 2017. 3. 28.
 * 작성자 : 김민지
 * 설  명 : junit 설정
 */


@RunWith(SpringJUnit4ClassRunner.class)//스프링의 테스트 컨텍스트 프레임워크 Junit 확장기능
/*
 * 스프링 빈설정 위치 지정 - 위치를 지정하지 않으면 -> 테스트클래스파일이 있는 패키지내에서 인식
*/
@ContextConfiguration(locations = {"file:src/test/resources/com/toy/junit/spring/*.xml",
								   "file:src/main/resources/spring/root/*-context.xml",
								   "file:src/main/resources/spring/servlet/*-context.xml",
								   }) 

@WebAppConfiguration//웹전용으로 WebApplocationContext 로드하기 위해서 사용 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//test 실행 순서 지정 (junit 4 버전 이상부터 가능)
public class JunitTest {
	
	
	UserDto userDto;
	
	@Autowired
//	UserDao userDao;
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 김민지
	 * 설  명 : 클래스가 실행되기 전에 한번만 실행됨 - BeforeClass (AfterClass 클래스에서 실행되고난 후 한번만 실행됨)
	 */
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("한번만");
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 김민지
	 * 설  명 : test 케이스 실행되기 전에 반복 실행
	 */
	@Before
	public void before(){
		System.out.println("전");
		userDto = new UserDto();
		userDto.setUser_id("aa");
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 김민지
	 * 설  명 : test 케이스 예제 - (Ignore 테스트 케이스 무시)
	 */
	@Test
	@Ignore
	public void testmain() {
		assertThat(userDto.getUser_id(), is("pp"));
		System.out.println("test");
	}
	
	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 김민지
	 * 설  명 : test 케이스 예제 - (DB연동 테스트)
	 */
	@Test
//	@Ignore
	public void testmember() throws Exception {
		System.out.println(userDto.getUser_id());
//		userDto = userDao.getUser(userDto.getUser_id());
		assertNotNull(userDto);
		System.out.println("not");
	}
	

	/**
	 * 작성일 : 2017. 3. 28.
	 * 작성자 : 김민지
	 * 설  명 : test 케이스 실행이 된후 반복 실행
	 */
	@After
	public void after() {
		System.out.println("후");
	}
}
