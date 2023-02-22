package com.kjh.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;

public class ManagerEvent {

	public void showEventList() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("                ────────── 광고 등록 ──────────");
		
		String sql = "SELECT E_NO, E_TITLE, E_CONTENT FROM EVENT";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String eventNum = rs.getString("E_NO");
			String eventName = rs.getString("E_TITLE");
			String eventContent = rs.getString("E_CONTENT");
			
			System.out.println("            광고번호 : " + eventNum + "  광고이름 : " + eventName + "  광고 내용 : " + eventContent);
		}
		
		conn.close();
		
	}
	
	public void insertEvent() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("                ────────── 광고 등록 ──────────");
		System.out.println("                       1. 광고 제목");
		System.out.print("                        입력 >> ");
		String eventName = Main.SC.nextLine();
		System.out.println("                       1. 광고 내용");
		System.out.print("                        입력 >> ");
		String eventContent = Main.SC.nextLine();
		
		String sql = "INSERT INTO EVENT(E_NO, MANAGER_NO, E_TITLE, E_CONTENT) VALUES(SEQ_E_NO.NEXTVAL, 1, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, eventName);
		pstmt.setString(2, eventContent);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("                       *광고 등록*");
		}else {
			System.out.println("                       광고 등록 실패");
		}
		
		conn.close();
		
	}
	
	public void removeEvent() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("                ────────── 광고 삭제 ──────────");
		System.out.println();
		showEventList();
		System.out.println();
		System.out.println("                     광고명 제게 (9. 뒤로가기)");
		System.out.print("                        입력 >> ");
		String eventName = Main.SC.nextLine();
		
		if(eventName.equals("9")){
			return ;
		}
		
		String sql = "DELETE FROM EVENT WHERE E_TITLE = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, eventName);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("                       *광고 삭제*");
		}else {
			System.out.println("                       광고 삭제 실패");
		}
		
		conn.close();
	}
	
	public String showEventBoard() throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
//		String sql = "SELECT E_CONTENT FROM EVENT ORDER BY dbms_random.value";
		String sql = "SELECT E_CONTENT FROM EVENT ORDER BY dbms_random.value";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		String eventContent = rs.getString("E_CONTENT");
		
		conn.close();
		
		return eventContent;
		
	}
	
}
