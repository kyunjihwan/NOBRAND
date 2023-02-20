package com.sys;

public class MainService {
	
	private MemberService ms = new MemberService();
	
	public boolean startService() throws Exception {
		//선택지 보여주기
		showMenu();
		
		//입력받기
		String input = Main.sc.nextLine();
		
		if("9".equals(input)) {
			return true;
		}
		
		//선택지에 따라 로직 실행
		processService(input);
		
		return false;
	}
	
	private void processService(String input) throws Exception {
		switch(input) {
		case "99" : ms.managerLogin();  break;
		case "1" : ms.login(); break;
		case "2" : ms.showFindMemberId(); break;
		case "3" : ms.showFindMemberPwd(); break;		
		case "4" : ms.join(); break;
		case "5" : ms.showGreat(); break;
		default : System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	private void showMenu() {
		System.out.println("1. 로그인");
		System.out.println("2. 아이디 찾기");
		System.out.println("3. 비번 찾기");
		System.out.println("4. 회원가입");
		System.out.println("5. 좋아요");
		System.out.println("9. 종료");
	}
	
	private void showMemberMenu() {
		System.out.println("1. 마이페이지");
		System.out.println("2. 좋아요");
		System.out.println("3. 장바구니내역");
		System.out.println("4. 쇼핑페이지");
		System.out.println("5. 고객센터 문의");
		System.out.println("9. 종료");
	}
	
}//class



























