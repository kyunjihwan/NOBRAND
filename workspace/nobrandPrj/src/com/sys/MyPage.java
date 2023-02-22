package com.sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.ksk.orderCancel.OrderCancelShow;
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
	
	//구매내역 조회
	public void showOrderProduct() throws Exception {
		
			
		//select
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT OP.ORD_NUM,P.PROD_NO, PROD_NAME , PRICE, LETTER, LETTER_POTION, SIZE_NAME FROM ORDER_PRODUCT OP JOIN BASKETLIST BL ON BL.BASKETLIST_NO = OP.BASKETLIST_NO JOIN BASKET B ON B.BASKET_NO = BL.BASKET_NO JOIN PRODUCT P ON B.PROD_NO = P.PROD_NO JOIN MAGNITUDE M ON M.SIZE_NO = P.SIZE_NO WHERE BL.MEMBER_NO = ? AND ORD_YN = 'N'"; 
				
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		ResultSet rs = pstmt.executeQuery();
		
		
		//결과집합에서 데이터 꺼내기
		while(rs.next()) {
			
			String on = rs.getString("ORD_NUM");
			String pno = rs.getString("PROD_NO");
			String pname = rs.getString("PROD_NAME");
			String price = rs.getString("PRICE");
			String le = rs.getString("LETTER");
			String lep = rs.getString("LETTER_POTION");
			String sz = rs.getString("SIZE_NAME");
			
			
			System.out.println(on + "     | " + pno + "   | " + pname + "   | " + price + " | " + le + " | " + lep + " | " + sz);
			
		}
		
		conn.close();
		OrderCancelShow ocs = new OrderCancelShow();
		ocs.CancelOrder();
	}

	
	
	
}
