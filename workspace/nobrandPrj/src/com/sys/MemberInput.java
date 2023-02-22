package com.sys;

import com.nobrand.main.Main;

public class MemberInput {
	
	//회원가입 입력받기
	public MemberData join() {
		System.out.println();
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║               JOIN               ║ ");
		System.out.println("           ╚══════════════════════════════════╝");
		System.out.println("                        1. 아이디");
		System.out.print("                       입력 >> ");
		String memberId = Main.SC.nextLine();
		System.out.println("                        2. 비밀번호");
		System.out.print("                       입력 >> ");
		String memberPwd = Main.SC.nextLine();
		System.out.println("                        3. 닉네임");
		System.out.print("                       입력 >> ");
		String memberNick = Main.SC.nextLine();
		System.out.println("                        4. 주소");
		System.out.print("                       입력 >> ");
		String memberAdr = Main.SC.nextLine();
		System.out.println("                        5. 핸드폰번호");
		System.out.print("                       입력 >> ");
		String memberPh = Main.SC.nextLine();
		System.out.println("                        6. 힌트내용");
		System.out.print("                       입력 >> ");
		String hintContent = Main.SC.nextLine();	
		System.out.println("                        7. 힌트 정답");
		System.out.print("                       입력 >> ");
		String answerHint = Main.SC.nextLine();
		
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
		System.out.println();
		System.out.println();
		System.out.println(" ██╗   ██╗ ██████╗ ███████╗ ███████╗  ██████╗ ██╗   ██╗██████╗  ");
		System.out.println(" ████╗ ██║██╔═══██╗██╔═══██╗██╔═══██╗██╔═══██╗████╗ ██║██╔══██╗");
		System.out.println(" ██║██╗██║██║   ██║███████═╝███████╔╝████████║██╔██╗██║██║  ██║ ");
		System.out.println(" ██║╚████║██║   ██║██╔═══██╗██╔══██╚╗██╔═══██║██║╚████║██║  ██║");
		System.out.println(" ██║  ╚██║╚██████╔╝███████╔╝██║  ╚██║██║   ██║██║  ╚██║██████╔╝" );
		System.out.println(" ╚═╝   ╚═╝ ╚═════╝ ╚══════╝ ╚═╝   ╚═╝╚═╝   ╚═╝╚═╝   ╚═╝╚═════╝  ");
		System.out.println();
		System.out.println();
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║              LOGIN               ║ ");
		System.out.println("           ╚══════════════════════════════════╝");
		System.out.println();
		System.out.print("                      아이디 >> ");
		String memberId = Main.SC.nextLine();
		System.out.print("                     비밀번호 >> ");
		String memberPwd = Main.SC.nextLine();
		
		MemberData data = new MemberData();
		data.setMemberId(memberId);
		data.setMemberPwd(memberPwd);
		return data;
	}
	
	//관리자로그인 입력받기
	public MemberData managerLogin() {
		System.out.println();
		System.out.println();
		System.out.println(" ██╗   ██╗ ██████╗ ███████╗ ███████╗  ██████╗ ██╗   ██╗██████╗  ");
		System.out.println(" ████╗ ██║██╔═══██╗██╔═══██╗██╔═══██╗██╔═══██╗████╗ ██║██╔══██╗");
		System.out.println(" ██║██╗██║██║   ██║███████═╝███████╔╝████████║██╔██╗██║██║  ██║ ");
		System.out.println(" ██║╚████║██║   ██║██╔═══██╗██╔══██╚╗██╔═══██║██║╚████║██║  ██║");
		System.out.println(" ██║  ╚██║╚██████╔╝███████╔╝██║  ╚██║██║   ██║██║  ╚██║██████╔╝" );
		System.out.println(" ╚═╝   ╚═╝ ╚═════╝ ╚══════╝ ╚═╝   ╚═╝╚═╝   ╚═╝╚═╝   ╚═╝╚═════╝  ");
		System.out.println();
		System.out.println();
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║          MANAGER LOGIN           ║ ");
		System.out.println("           ╚══════════════════════════════════╝");
		System.out.println();
		System.out.print("                      아이디 >> ");
		String managerId = Main.SC.nextLine();
		System.out.print("                     비밀번호 >> ");
		String managerPwd = Main.SC.nextLine();
		
		MemberData data = new MemberData();
		data.setManagerId(managerId);
		data.setManagerPwd(managerPwd);
		return data;
	}
		
	//힌트질문 찾기 입력받기
	public MemberData showFindMemberId() {
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║             ID SEARCH            ║ ");
		System.out.println("           ╚══════════════════════════════════╝");		
		System.out.println("                       1. 핸드폰번호 ");
		System.out.print("                       입력 >> ");
		String memberPh = Main.SC.nextLine();
		
		
		MemberData data = new MemberData();
		
		data.setMemberPh(memberPh);
		return data;
	}
	//아이디 찾기 입력받기
	public MemberData showFindMemberIdd() {
		
		System.out.println("                       2. 힌트 정답 ");
		System.out.print("                       입력 >> ");
		String answerHint = Main.SC.nextLine();
		
		
		MemberData data = new MemberData();
		
		data.setAnswerHint(answerHint); 
		return data;
	}
	//비번 찾기 입력받기
	public MemberData showFindMemberPwd() {
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║             PWD SEARCH           ║ ");
		System.out.println("           ╚══════════════════════════════════╝");	
		System.out.println("                       1. 아이디   ");
		System.out.print("                       입력 >> ");
		String memberId = Main.SC.nextLine();
		
		MemberData data = new MemberData();
		
		data.setMemberId(memberId);
		return data;
	}
	
	//좋아요 입력받기
	public MemberData insertGreat() {
		
		System.out.println("상품번호 : ");
		String productNo = Main.SC.nextLine();
		
		MemberData data = new MemberData();
		
		data.setProductNo(productNo);;
		return data;
	}

	//좋아요 삭제
	public MemberData deleteGreat() {
		
		System.out.println("                        삭제할 상품번호");
		System.out.println("                       입력 >> ");
		String productNo = Main.SC.nextLine();
		
		
		MemberData data = new MemberData();
		
		data.setProductNo(productNo);
		return data;
		
	}
	
	//비번변경
	public MemberData updateMemberPwdInfo() {
		
		System.out.println();
		System.out.println("                         새 비밀번호 변경");
		System.out.print("                       입력 >> ");
		String memberPwd = Main.SC.nextLine();
		
		MemberData data = new MemberData();
		
		data.setMemberPwd(memberPwd);
		return data;
		
	}
	
	//닉변경
	public MemberData updateMemberNickInfo() {
		
		System.out.println("");
		System.out.println("                         새 닉네임 ");
		System.out.print("                       입력 >> ");
		String memberNick = Main.SC.nextLine();
		
		MemberData data = new MemberData();
		
		data.setMemberNick(memberNick);
		return data;
		
	}
	

	//회원탈퇴
	public MemberData withDraw() {
		
		System.out.print("회원탈퇴 하시겠습니까? 1. Y 2. N: ");
		String withDrawalYN = Main.SC.nextLine();
		
		
		
		MemberData data = new MemberData();
		
		data.setWithDrawalYN(withDrawalYN);
		return data;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
