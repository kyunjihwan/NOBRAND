package com.kjh.manager;

import com.ljh.inquirycenter.InquiryShow;
import com.nobrand.main.Main;

public class ManagerService {

	ManagerProduct mp = new ManagerProduct();
	ManagerKind mk = new ManagerKind();
	ManagerInquiry mi = new ManagerInquiry();
	ManagerEvent me = new ManagerEvent();
	InquiryShow is = new InquiryShow();
	
	public boolean startManagerMenu() throws Exception {
		
		showManagerMenu();
		
		String input = Main.SC.nextLine();
		
		if(input.equals("9")) {
			return true;
		}
		
		switch(input) {
		case "1" :
			while(true) {
				boolean isFinish = startMangerProduct();
				if(isFinish) {break;}
			}
			 break;
		case "2" : 
			while(true) {
				boolean isFinish = startManagerInquiry();
				if(isFinish) {break;}
			} break;
		case "3" : break;
		default : System.out.println("잘못입력했습니다"); break;
		}
		
		
		return false;
		
	}
	
	public void showManagerMenu() {
		System.out.println("1. 상품 관련");
		System.out.println("2. 고객문의센터");
		System.out.println("3. 이벤트");
		System.out.println("9. 뒤로가기");
	}
	
	private boolean startMangerProduct() throws Exception {
		showManagerProduct();
		
		String input = Main.SC.nextLine();
		
		if(input.equals("9")) {
			return true;
		}
		
		switch(input) {
		case "1" : mp.insertProduct(); break;
		case "2" : mp.removeProduct(); break;
		case "3" : mk.insertKind(); break;
		case "4" : mk.removeKind(); break;
		}
		
		return false;
	}
	
	private void showManagerProduct() {
		System.out.println("1. 상품 등록");
		System.out.println("2. 상품 삭제");
		System.out.println("3. 카테고리 등록");
		System.out.println("4. 카테고리 삭제");
		System.out.println("9. 뒤로가기");
	}
	
	private boolean startManagerInquiry() throws Exception {
		showManagerInquiry();
		
		String input = Main.SC.nextLine();
		
		if(input.equals("9")) {
			return true;
		}
		
		switch(input) {
		case "1" : mi.showBoardList(); break;
		case "2" : mi.removeInquiry(); break;
		}
		
		return false;
	}
	
	private void showManagerInquiry() {
		System.out.println("====== 고객 답변 ======");
		System.out.println("1. 고객문의게시글 답변 등록");
		System.out.println("2. 고객문의게시글 삭제");
	}
	
} // class
