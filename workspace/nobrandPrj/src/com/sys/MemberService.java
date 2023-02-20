package com.sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.sys.MemberData;
import com.sys.MemberInput;

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
			System.out.println("회원가입 실패...");
		}else {
			System.out.println("회원가입 성공!");
		}
		
		//커넥션 정리
		conn.close();
	}
	
	//로그인
	public void login() throws Exception {
		//유저 데이터 입력받기
		MemberData data = mi.login();
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT MEMBER_ID, MEMBER_PWD, MEMBER_NICK, MEMBER_NO FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getMemberId());
		pstmt.setString(2, data.getMemberPwd());
		ResultSet rs = pstmt.executeQuery();
		
		
		
		//결과집합에서 데이터 꺼내기
		if(rs.next()) {
			String mno = rs.getString("MEMBER_NO");
			Main.loginMemberNo = mno;
			System.out.println(mno + "GD");
			String nick = rs.getString("MEMBER_NICK");
			System.out.println(nick + " 님 환영합니다.");
			Main.loginMemberNick = nick;
		}else {
			System.out.println("로그인 실패");
		}
		
		conn.close();
	}
	
	//관리자계정로그인
	public void managerLogin() throws Exception {
		//유저 데이터 입력받기
		MemberData data = mi.managerLogin();
		
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
			System.out.println(nick + " 님 환영합니다.");
			Main.loginManagerNick = nick;
		}else {
			System.out.println("로그인 실패");
		}
		
		conn.close();
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
			System.out.println("질문: " + hint);
			
			
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
			System.out.println("아이디: " + id);
			
			
		}else {
			System.out.println("실패");
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
			System.out.println("비밀번호는 " + PWD + "입니다.");
			
		}else {
			System.out.println("실패");
		}
		
		conn.close();
	}
	//좋아요 입력
	public void insertGreat() throws Exception {
		
		//로그인 여부 검사
		if(Main.loginMemberNick == null) {
			System.out.println("로그인 한 유저만 글쓰기가 가능합니다.");
			return;
		}
		
		//유저 데이터 입력받기
		MemberData data = mi.insertGreat();
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "INSERT INTO GREAT (MEMBER_NO, PROD_NO) VALUES (?, ?)"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		pstmt.setString(2, data.getProductNo());
		int result = pstmt.executeUpdate();
		
		//결과에 따른 처리
		if(result == 1) {
			System.out.println("좋아요가 되었습니다.");
		}else {
			System.out.println("좋아요 실패");
		}
		
		//커넥션 정리
		conn.close();
	}

	public void Great() throws Exception {
		showGreat();
		deleteGreat();
	}
	
	
	//좋아요 조회
	public void showGreat() throws Exception {
		
		//로그인 여부 검사
		if(Main.loginMemberNick == null) {
			System.out.println("로그인 한 유저만 글쓰기가 가능합니다.");
			return;
		}
		
		
		
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT P.PROD_NO, PROD_NAME, PRICE FROM PRODUCT P JOIN GREAT G ON G.PROD_NO = P.PROD_NO JOIN MEMBER M ON M.MEMBER_NO = G.MEMBER_NO WHERE M.MEMBER_NO = ?"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		ResultSet rs = pstmt.executeQuery();
		
		//결과집합에서 데이터 꺼내기
		while(rs.next()) {
			
			String pno = rs.getString("PROD_NO");
			String pname = rs.getString("PROD_NAME");
			String price = rs.getString("PRICE");
			
			System.out.println(pno + " | " + pname + " | " + price);
			
		}
		
		
		
		conn.close();
	}
		
		
		
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
			System.out.println("삭제됨");
		}else {
			System.out.println("실패");
		}
	}
	





}//class


























