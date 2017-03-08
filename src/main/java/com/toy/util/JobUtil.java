package com.toy.util;

import org.springframework.stereotype.Component;

@Component("jobBean")
public class JobUtil {
	public void printMessage() {
		System.out.println("job running");
	}
}
