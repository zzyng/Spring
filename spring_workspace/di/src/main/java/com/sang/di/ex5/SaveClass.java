package com.sang.di.ex5;

import java.util.ArrayList;
import java.util.HashMap;

public class SaveClass {
	private PrintClass pc;
	private ArrayList<String> names;
	private HashMap<String, String> menus;
	
	public void show() {
		pc.show(names,  menus);
	}

	public SaveClass(PrintClass pc, ArrayList<String> names, HashMap<String, String> menus) {
		super();
		this.pc = pc;
		this.names = names;
		this.menus = menus;
	}
	
	
	
	
	
	
	
	
}
