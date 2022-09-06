package com.sang.di.ex5;

import java.util.ArrayList;
import java.util.HashMap;

public class PrintClass {

	public void show(ArrayList<String> names, HashMap<String, String> menus) {
		for(String name : names) {
			System.out.println("지역 : " + name);
			System.out.println("#### 메뉴 ####");
			System.out.println("김밥 : " + menus.get("김밥"));
			System.out.println("라면 : " + menus.get("라면"));
			System.out.println();
		}

	}
}