package com.ksk.basketlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BasketlistInput {

	public void inputBasketlist(Connection conn) throws Exception {
		System.out.println("===== 장바구니 내역 담기 =====");
		String sql = "INSERT BASKET_NO INTO BASKETLIST FROM BASKET WHERE BUY_YN = N ORDER BY BASKET_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("장바구니 내역 담기 완료");
		
		conn.close();
	}
	
}
