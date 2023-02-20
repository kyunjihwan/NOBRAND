package com.ksk.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BasketInput {

	public void inputBasket(Connection conn) throws Exception {
		System.out.println("===== 장바구니 담기 =====");
		String sql = "INSERT PROD_NO INTO BASKET ORDER BY PROD_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("장바구니 담기 완료");
		
		conn.close();
	}
	
}
