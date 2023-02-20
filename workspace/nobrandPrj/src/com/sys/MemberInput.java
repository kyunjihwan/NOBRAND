package com.sys;

import com.sys.MemberData;

public class MemberInput {
	
	//회원가입 입력받기
	public MemberData join() {
		System.out.print("아이디 : ");
		String memberId = Main.sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPwd = Main.sc.nextLine();
		System.out.print("닉네임 : ");
		String memberNick = Main.sc.nextLine();
		System.out.print("주소 : ");
		String memberAdr = Main.sc.nextLine();
		System.out.print("핸드폰번호 : ");
		String memberPh = Main.sc.nextLine();
		System.out.print("힌트내용 : ");
		String hintContent = Main.sc.nextLine();	
		System.out.print("힌트 정답 : ");
		String answerHint = Main.sc.nextLine();
		
		MemberData data = new MemberData();
		data.setMemberId(memberId);
		data.setMemberPwd(memberPwd);
		data.setMemberNick(memberNick);
		data.setMemberAdr(memberAdr);
		data.setMemberPh(memberPh);
		data.setHintContent(hintContent);;
		data.setAnswerHint(answerHint);
		
		return data;
	}
	
	//로그인 입력받기
	public MemberData login() {
		System.out.println("회원로그인");
		System.out.print("아이디 : ");
		String memberId = Main.sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPwd = Main.sc.nextLine();
		
		MemberData data = new MemberData();
		data.setMemberId(memberId);
		data.setMemberPwd(memberPwd);
		return data;
	}
	
	//관리자로그인 입력받기
	public MemberData managerLogin() {
		System.out.println("관리자로그인");
		System.out.print("아이디 : ");
		String managerId = Main.sc.nextLine();
		System.out.print("비밀번호 : ");
		String managerPwd = Main.sc.nextLine();
		
		MemberData data = new MemberData();
		data.setManagerId(managerId);
		data.setManagerPwd(managerPwd);
		return data;
	}
		
	//힌트질문 찾기 입력받기
	public MemberData showFindMemberId() {
				
		System.out.println("핸드폰번호 : ");
		String memberPh = Main.sc.nextLine();
		
		
		MemberData data = new MemberData();
		
		data.setMemberPh(memberPh);
		return data;
	}
	//아이디 찾기 입력받기
	public MemberData showFindMemberIdd() {
		
		System.out.print("정답 : ");
		String answerHint = Main.sc.nextLine();
		
		
		MemberData data = new MemberData();
		
		data.setAnswerHint(answerHint);
		return data;
	}
	//비번 찾기 입력받기
	public MemberData showFindMemberPwd() {
		
		System.out.println("아이디 : ");
		String memberId = Main.sc.nextLine();
		
		MemberData data = new MemberData();
		
		data.setMemberId(memberId);
		return data;
	}
	
	//좋아요 입력받기
	public MemberData insertGreat() {
		
		System.out.println("상품번호 : ");
		String productNo = Main.sc.nextLine();
		
		MemberData data = new MemberData();
		
		data.setProductNo(productNo);;
		return data;
	}

	//좋아요 삭제
	public MemberData deleteGreat() {
		
		System.out.println("상품번호 : ");
		String productNo = Main.sc.nextLine();
		
		MemberData data = new MemberData();
		
		data.setProductNo(productNo);;
		return data;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
