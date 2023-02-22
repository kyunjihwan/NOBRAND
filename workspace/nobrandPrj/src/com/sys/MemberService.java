package com.sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.nobrand.main.Main;

public class MemberService {

	private final MemberInput mi = new MemberInput();
	
	//회원가입
	public void join() throws Exception {
		//유저 데이터 입력받기
		MemberData data = mi.join();
		
		//insert
		Connection conn = JdbcTemplate.getConnection();
		String sql = "INSERT ALL INTO MEMBER (MEMBER_NO, MEMBER_ID, MEMBER_PWD, MEMBER_NICK, MEMBER_ADR, MEMBER_PH, JOIN_DATE, HINT_NO, ANSWER_HINT) VALUES (SEQ_MEMBER_NO.NEXTVAL, ? , ? , ?, ?, ? ,TO_TIMESTAMP(SYSDATE), SEQ_HINT_NO.NEXTVAL, ?) INTO HINT (HINT_NO, HINT_CONTENT)  VALUES (SEQ_HINT_NO.NEXTVAL, ?) SELECT * FROM DUAL";                                 	
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, data.getMemberId());
		pstmt.setString(2, data.getMemberPwd());
		pstmt.setString(3, data.getMemberNick());
		pstmt.setString(4, data.getMemberAdr());
		pstmt.setString(5, data.getMemberPh());
		pstmt.setString(6, data.getAnswerHint());
		pstmt.setString(7, data.getHintContent());
		
		int result = pstmt.executeUpdate();
		
		//결과에 따른 처리
		if(result == 1) {
			System.out.println("                        회원가입 실패...");
		}else {
			System.out.println("                        회원가입 성공!");
		}
		
		//커넥션 정리
		conn.close();
	}
	
	//로그인
	public boolean login() throws Exception {
		//유저 데이터 입력받기
		MemberData data = mi.login();
		boolean isFinish = true;
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT MEMBER_ID, MEMBER_PWD, MEMBER_NICK, MEMBER_NO FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ? AND WITHDRAWAL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getMemberId());
		pstmt.setString(2, data.getMemberPwd());
		ResultSet rs = pstmt.executeQuery();
		
		
		
		//결과집합에서 데이터 꺼내기
		if(rs.next()) {
			String mno = rs.getString("MEMBER_NO");
			Main.loginMemberNo = mno;
			String nick = rs.getString("MEMBER_NICK");
			System.out.println();
			System.out.println("                   * " + nick + " 님 환영합니다 *");
			Main.loginMemberNick = nick;
		}else {
			System.out.println("로그인 실패");
			isFinish = false;
		}
		
		conn.close();
		return isFinish;
	}
	
	//관리자계정로그인
	public boolean managerLogin() throws Exception {
		//유저 데이터 입력받기
		MemberData data = mi.managerLogin();
		boolean isFinish = true;
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT MANGER_ID, MANGER_PWD, MANGER_NICK FROM MANAGER WHERE MANGER_ID = ? AND MANGER_PWD = ?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getManagerId());
		pstmt.setString(2, data.getManagerPwd());
		ResultSet rs = pstmt.executeQuery();
		
		//결과집합에서 데이터 꺼내기
		if(rs.next()) {
			String nick = rs.getString("MANGER_NICK");
			System.out.println();
			System.out.println("                   * " + nick + " 님 환영합니다 *");
			Main.loginManagerNick = nick;
		}else {
			System.out.println("로그인 실패");
			isFinish = false;
		}
		
		conn.close();
		
		return isFinish;
	}

	
	//아이디 찾기
	public void showFindMemberId() throws Exception {
		//유저 데이터 입력받기
		MemberData data = mi.showFindMemberId();
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT MEMBER_ID, M.HINT_NO, HINT_CONTENT, ANSWER_HINT FROM MEMBER M JOIN HINT H ON H.HINT_NO = M.HINT_NO WHERE MEMBER_PH = ?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getMemberPh());
		
		ResultSet rs = pstmt.executeQuery();
		
		//결과집합에서 데이터 꺼내기
		if(rs.next()) {
			String hint = rs.getString("HINT_CONTENT");
			System.out.println("                       질문 : " + hint);
			
			
		}else {
			System.out.println("실패");
			return;
		}
		
		MemberData data2 = mi.showFindMemberIdd();
		
		
		String sql2 = "SELECT MEMBER_ID, M.HINT_NO, HINT_CONTENT, ANSWER_HINT FROM MEMBER M JOIN HINT H ON H.HINT_NO = M.HINT_NO WHERE ANSWER_HINT = ?";
				
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		pstmt2.setString(1, data2.getMemberPh());
		
		ResultSet rs2 = pstmt.executeQuery();
		
		
		//결과집합에서 데이터 꺼내기
		if(rs2.next()) {
			
			
			String id = rs2.getString("MEMBER_ID");
			System.out.println();
			System.out.println("                      아이디: " + id);
			
			
		}else {
			System.out.println("                       실패");
		}
		
		conn.close();
	}
	
	//비번찾기
	public void showFindMemberPwd() throws Exception {
		
		//유저 데이터 입력받기
		MemberData data = mi.showFindMemberPwd();
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT MEMBER_ID, MEMBER_PWD FROM MEMBER WHERE MEMBER_ID = ?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getMemberId());
		ResultSet rs = pstmt.executeQuery();
		
		//결과집합에서 데이터 꺼내기
		if(rs.next()) {
			String PWD = rs.getString("MEMBER_PWD");
			System.out.println();
			System.out.println("                      비밀번호는 " + PWD + "입니다.");
			
		}else {
			System.out.println("                       실패");
		}
		
		conn.close();
	}
	//좋아요 입력
	public void insertGreat(String proNum) throws Exception {
		
		//로그인 여부 검사
		if(Main.loginMemberNick == null) {
			System.out.println("로그인 한 유저만 글쓰기가 가능합니다.");
			return;
		}
		String proNum1 = proNum;
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "INSERT INTO GREAT (MEMBER_NO, PROD_NO) VALUES (?, ?)"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		pstmt.setString(2, proNum1);
		int result = pstmt.executeUpdate();
		
		//결과에 따른 처리
		if(result == 1) {
			System.out.println("                        *좋아요*");
			System.out.println();
		}else {
			System.out.println("                        좋아요 실패");
			System.out.println();
		}
		
		//커넥션 정리
		conn.close();
	}

	public void Great() throws Exception {
		showGreat();
		System.out.println();
		System.out.println("                        1. 좋아요 삭제");
		System.out.println("                        9. 뒤로가기");
		System.out.println();
		System.out.print("                       입력 >> ");
		String choice = Main.SC.nextLine();
		
		if(choice.equals("9")) {
			return ; 
		}
		switch(choice) {
		case "1" : deleteGreat();
		}
	}
	
	
	//좋아요 조회
	public void showGreat() throws Exception {
		
		//로그인 여부 검사
		if(Main.loginMemberNick == null) {
			System.out.println("로그인 한 유저만 조회가 가능합니다.");
			return;
		}
		System.out.println();
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║               GREAT♥             ║ ");
		System.out.println("           ╚══════════════════════════════════╝");
		System.out.println();
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT P.PROD_NO, PROD_NAME, PRICE FROM PRODUCT P JOIN GREAT G ON G.PROD_NO = P.PROD_NO JOIN MEMBER M ON M.MEMBER_NO = G.MEMBER_NO WHERE M.MEMBER_NO = ?"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("               NO    PROD_NAME      PRICE");
		System.out.println("               ═════════════════════════════");
		//결과집합에서 데이터 꺼내기
		while(rs.next()) {
			
			String pno = rs.getString("PROD_NO");
			String pname = rs.getString("PROD_NAME");
			String price = rs.getString("PRICE");
			
			System.out.println("               " + pno + "      " + pname + "          " + price);
			
		}
		
		
		
		conn.close();
	}
		
		
	//좋아요 삭제
	public void deleteGreat() throws Exception {
		//로그인 여부 검사
		if(Main.loginMemberNick == null) {
			System.out.println("로그인 한 유저만 삭제가 가능합니다.");
			return;
		}
		
		//유저 데이터 입력받기
		MemberData data = mi.deleteGreat();
		
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "DELETE FROM GREAT WHERE MEMBER_NO = ? AND PROD_NO = ?"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		pstmt.setString(2, data.getProductNo());
		int result = pstmt.executeUpdate();
		
		//결과에 따른 처리
		if(result == 1) {
			System.out.println("                       *삭제됐습니다*");
		}else {
			System.out.println("                       실패");
		}
		
		//커넥션 정리
		conn.close();
	}
	
	//회원정보 수정
	public void updateMemberInfo() throws Exception {
		if(Main.loginMemberNick == null) {
			System.out.println("로그인 한 유저만 변경이 가능합니다.");
			return;
		}
		System.out.println();
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║             EDIT INFO            ║ ");
		System.out.println("           ╚══════════════════════════════════╝");	
		System.out.println("                        1. 비밀번호 변경");
		System.out.println("                        2. 닉네임 변경");
		System.out.println("                        9. 뒤로 가기");
		System.out.println();
		System.out.print("                       입력 >> ");
		String c = Main.SC.nextLine();
		if(c.equals("1")) {
			updateMemberPwdInfo();
		}else if(c.equals("2")) {
			updateMemberNickInfo();
		}else if(c.equals("9")) {
			return ;
		}
		
	}
	
	
	//비밀번호 변경
	public void updateMemberPwdInfo() throws Exception {
		
		
		//유저 데이터 입력받기
		MemberData data = mi.updateMemberPwdInfo();
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "UPDATE MEMBER SET MEMBER_PWD = ? WHERE MEMBER_NO = ?"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getMemberPwd());
		pstmt.setString(2, Main.loginMemberNo);
		int result = pstmt.executeUpdate();
		
		//결과에 따른 처리
		if(result == 1) {
			System.out.println("                       *변경완료*");
		}else {
			System.out.println("                        실패");
		}
		
		//커넥션 정리
		conn.close();
	}

	//닉네임 변경
	public void updateMemberNickInfo() throws Exception {
		
		//유저 데이터 입력받기
		MemberData data = mi.updateMemberNickInfo();
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "UPDATE MEMBER SET MEMBER_NICK = ? WHERE MEMBER_NO = ?"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getMemberNick());
		pstmt.setString(2, Main.loginMemberNo);
		int result = pstmt.executeUpdate();
		
		//결과에 따른 처리
		if(result == 1) {
			System.out.println("                        변경완료");
		}else {
			System.out.println("                        실패");
		}
		
		//커넥션 정리
		conn.close();
	}

	//회원탈퇴
	public void withDraw() throws Exception {
		//로그인 여부 검사
		if(Main.loginMemberNick == null) {
			System.out.println("로그인 한 유저만 삭제가 가능합니다.");
			return;
		}
		
		//유저 데이터 입력받기
		MemberData data = mi.withDraw();
		
		if(data.getWithDrawalYN().equals("N")) {
			return;
		}
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "UPDATE MEMBER SET WITHDRAWAL_YN = ? WHERE MEMBER_NO = ?"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getWithDrawalYN());
		pstmt.setString(2, Main.loginMemberNo);
		int result = pstmt.executeUpdate();
		
		//결과에 따른 처리
		if(result == 1) {
			System.out.println("탈퇴완료");
		}else {
			System.out.println("실패");
		}
		
		//커넥션 정리
		conn.close();
	}
	
	
	

}//class


























