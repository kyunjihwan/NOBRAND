package com.sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nobrand.main.Main;

public class MyPage {
	private MemberService ms = new MemberService();
	
	
	
	
	//회원정보 조회
	public void showMemberInfo() throws Exception {
		
			
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT MEMBER_ID, MEMBER_NICK, MEMBER_ADR, MEMBER_PH, JOIN_DATE FROM MEMBER WHERE MEMBER_NO = ?"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		ResultSet rs = pstmt.executeQuery();
		
		//결과집합에서 데이터 꺼내기
		while(rs.next()) {
			
			String mi = rs.getString("MEMBER_ID");
			String mn = rs.getString("MEMBER_NICK");
			String ma = rs.getString("MEMBER_ADR");
			String mp = rs.getString("MEMBER_PH");
			String jd = rs.getString("JOIN_DATE");
			
			System.out.println(mi + " | " + mn + " | " + ma + " | " + mp + " | " + jd);
			
		}
		
		
		
		conn.close();
	}
	
	
	
	
	
	
}
