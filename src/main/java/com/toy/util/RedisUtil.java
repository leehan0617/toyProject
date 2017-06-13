package com.toy.util;

import java.net.URL;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component("redisUtil")
public class RedisUtil {
	
	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
	@Autowired
	private RedisTemplate<String,String> template;
	
	@Resource(name="redisTemplate")
	private ListOperations<String , String> listOps;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String , String> valueOps;
	
	public void addLink(String userId , URL url) {
		listOps.leftPush(userId , url.toExternalForm());
		template.boundListOps(userId).leftPush(url.toExternalForm());
	}
	
	public void test() {
		valueOps.set("foo", "bar");
		logger.info("redis test {}, " , valueOps.get("foo"));
	}
	
	public String getTest(String key) {
		return valueOps.get(key);
	}
}
