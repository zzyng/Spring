package com.sang.di.ex4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		String location = "classpath:ex4.xml";
		GenericXmlApplicationContext gxac = new GenericXmlApplicationContext(location);
		SaveClass sc = gxac.getBean(SaveClass.class);
		sc.show();
	}
}
