package com.kjh.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;

public class ManagerEvent {

	public void showEventList() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("===== 광고 목록 =====");
		
		String sql = "SELECT E_TITLE, E_CONTENT FROM EVENT";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String eventName = rs.getString("E_TITLE");
			String eventContent = rs.getString("E_CONTENT");
			
			System.out.println(eventName + " | " + eventContent);
		}
		
		conn.close();
		
	}
	
	public void insertEvent() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("===== 광고 추가 =====");
		System.out.print("광고 이름 : ");
		String eventName = Main.SC.nextLine();
		System.out.print("광고 내용 : ");
		String eventContent = Main.SC.nextLine();
		
		String sql = "INSERT INTO EVENT(E_NO, MANAGER_NO, E_TITLE, E_CONTENT) VALUES(SEQ_E_NO.NEXTVAL, 1, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, eventName);
		pstmt.setString(2, eventContent);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("광고 등록 성공");
		}else {
			System.out.println("광고 등록 실패");
		}
		
		conn.close();
		
	}
	
	public void removeEvent() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("===== 광고 제거 =====");
		showEventList();
		System.out.print("광고 제거 이름 : ");
		String eventName = Main.SC.nextLine();
		
		String sql = "DELETE FROM EVENT WHERE E_TITLE = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, eventName);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("광고 삭제 성공");
		}else {
			System.out.println("광고 삭제 실패...");
		}
		
		conn.close();
	}
	
}
