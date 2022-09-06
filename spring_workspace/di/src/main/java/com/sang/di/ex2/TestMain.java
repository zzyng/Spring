package com.sang.di.ex2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		
		String location = "classpath:ex2.xml";
		GenericXmlApplicationContext gxac = new GenericXmlApplicationContext(location);
		
		TestBean bean = gxac.getBean(TestBean.class);
		
		bean.getTest().printName("홍길동");
		bean.getTest().printAget(30);
	}

}
