package com.sang.di.ex1;

public class TestMain {
	public static void main(String[] args) {
		TestBean bean = new TestBean();
		bean.setTest(new Test());
		bean.getTest().printName("홍길동");
		bean.getTest().printAget(30);
	}

}
