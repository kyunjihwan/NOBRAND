package com.sys;


import com.sys.MemberService;

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
		case "0" : ms.managerLogin(); break;
		case "1" : ms.join(); break;
		case "2" : ms.login(); break;
		case "5" : ms.showFindMemberId(); break;
		case "6" : ms.showFindMemberPwd(); break;		
		case "7" : ms.showGreat(); break;
		default : System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	private void showMenu() {
		System.out.println("0. 마스터계정로그인");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 게시글 작성");
		System.out.println("4. 게시글 목록 조회");
		System.out.println("5. 아이디 찾기");
		System.out.println("6. 비번 찾기");
		System.out.println("7. 좋아요 조회");
		System.out.println("9. 종료");
	}

}//class



























