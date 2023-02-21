package com.kjh.manager;

import com.nobrand.main.Main;

public class ManagerService {

	ManagerProduct mp = new ManagerProduct();
	ManagerKind mk = new ManagerKind();
	ManagerInquiry mi = new ManagerInquiry();
	ManagerEvent me = new ManagerEvent();
	
	
	public void showManagerMenu() {
		System.out.println("1. 상품 관련");
		System.out.println("2. 고객문의센터");
		System.out.println("3. 이벤트");
		System.out.println("9. 뒤로가기");
	}
	
	public void startManagerMenu() {
		showManagerMenu();
		
		String input = Main.SC.nextLine();
		
		if(input.equals("9")) {
		}
		
	}
	
	
	
} // class
