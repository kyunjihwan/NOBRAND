package com.ksk.basketlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BasketlistShow {

	public void showBasketlist(Connection conn) throws Exception {
		System.out.println("===== 장바구니내역 =====");
		String sql = "SELECT BASKETLIST_NO, PROD_NO, MEMBER_NO , BASKET_NO FROM BASKETLIST BL JOIN MEMBER M ON BL.MEMBER_NO = M.MEMBER_NO JOIN BASKET B ON BL.BASKET_NO = B.BASKET_NO ORDER BY BASKETLIST_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
			String BasketlistNo = rs.getString("BASKETLIST_NO");
			String MemberNo  = rs.getString("MEMBER_NO");
			String BasketNo = rs.getString("BASKET_NO");
			
			System.out.println("담은 상품의 장바구니내역번호, 회원번호, 장바구니번호입니다.");
			System.out.println(BasketlistNo + " , " + MemberNo + " , " + BasketNo);
	
			conn.close();
	}

}
