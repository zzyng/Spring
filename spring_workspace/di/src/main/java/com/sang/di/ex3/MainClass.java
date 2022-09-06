package com.sang.di.ex3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	
	public static void main(String []args) {
		String location = "classpath:ex3.xml";
		GenericXmlApplicationContext gxac = new GenericXmlApplicationContext(location);
		
		PrintBean pb = gxac.getBean(PrintBean.class);
		pb.getPs().show(pb.getData());
	
	
	}
}
