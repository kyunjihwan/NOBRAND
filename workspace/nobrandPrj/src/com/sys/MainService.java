package com.sys;

import com.nobrand.main.Main;

public class MainService {
	
	private MemberService ms = new MemberService();
	
	public boolean startService() throws Exception {
		//선택지 보여주기
		showMenu();
		
		//입력받기
		String input = Main.SC.nextLine();
		
		if("9".equals(input)) {
			return true;
		}
		
		//선택지에 따라 로직 실행
		processService(input);
		
		return false;
	}
	
	private void processService(String input) throws Exception {
		switch(input) {
		case "99" : ms.managerLogin(); break;
		case "1" : ms.join(); startMenuService(); break;
		case "2" : ms.login(); break;
		case "3" : ms.showFindMemberId(); break;
		case "4" : ms.showFindMemberPwd(); break;		
		default : System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	private void showMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 아이디 찾기");
		System.out.println("4. 비번 찾기");
		System.out.println("9. 종료");
		
	}
	
	private boolean startMenuService() {
		
		showMenuService();
		
		String input = Main.SC.nextLine();
		
		if("9".equals(input)) {
			return true;
		}
		
		switch(input) {
		case "1" : break;
		case "2" : break;
		case "3" : break;
		case "4" : break;
		case "5" : break;
		default : System.out.println("잘못 입력했습니다."); 
		}
		
		return false;
		
	}
	
	private void showMenuService() {

		System.out.println("1. 마이페이지");
		System.out.println("2. 좋아요");
		System.out.println("3. 장바구니");
		System.out.println("4. 쇼핑페이지");
		System.out.println("5. 고객문의센터");
		System.out.println("9. 뒤로가기");
		
	}

}//class



























