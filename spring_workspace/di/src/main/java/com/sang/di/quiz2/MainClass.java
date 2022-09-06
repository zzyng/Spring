package com.sang.di.quiz2;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		String location = "classpath:quiz2.xml";
		GenericXmlApplicationContext gxac = new GenericXmlApplicationContext(location);
		SaveClass save = gxac.getBean(SaveClass.class);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("입력 :");
		String input = sc.nextLine();
		String[] inputs = input.split(" ");
		
		save.setData1(Integer.parseInt(inputs[0]));
		save.setOper(inputs[1]);
		save.setData2(Integer.parseInt(inputs[2]));
		
		save.getReulst();
	}
}
