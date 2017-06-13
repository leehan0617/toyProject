package com.toy.util;

import java.net.URL;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.toy.util.model.CustomLog;

@Component("redisUtil")
public class RedisUtil {
	
	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
	@Autowired
	private RedisTemplate<String,CustomLog> template;
	
	@Resource(name="redisTemplate")
	private ListOperations<String , String> listOps;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String , String> valueOps;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String , CustomLog> valueOps2;
	
	public void addLink(String userId , URL url) {
		listOps.leftPush(userId , url.toExternalForm());
	}
	
	public void test() {
		valueOps.set("testKey", "bar");
		logger.info("redis test {}, " , valueOps.get("foo"));
		Set<String> keys = template.keys("*");
		logger.info("size : {} " , keys.size());
		for(String key : keys) {
			logger.info("key : {} " , key);
		}
	}
	
	public String getTest(String key) {
		return valueOps.get(key);
	}

	public void test2() {
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CustomLog.class));
		
//		CustomLog log = new CustomLog();
//		log.setId(3);
//		log.setName("이한빈");
//		
//		template.opsForValue().set("hanbin", log);
	}
	
	public void test3() {
//		CustomLog log = new CustomLog();
//		log.setId(4);
//		log.setName("이한빈");
//		
//		valueOps2.set("hanbin2", log);
		
		logger.info("redis test3 {}" , valueOps2.get("hanbin2"));
	}
}
