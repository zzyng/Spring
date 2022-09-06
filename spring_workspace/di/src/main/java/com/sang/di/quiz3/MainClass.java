package com.sang.di.quiz3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		String location = "classpath:quiz3.xml";
		GenericXmlApplicationContext gxac = new GenericXmlApplicationContext(location);
		
		SaveClass sc = gxac.getBean(SaveClass.class);
		sc.getReulst();
	}
}
