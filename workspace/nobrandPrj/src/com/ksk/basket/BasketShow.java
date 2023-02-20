package com.ksk.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;

public class BasketShow {

	public void showBasket() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("===== 장바구니 =====");
		String sql = "SELECT BASKET_NO, B.PROD_NO, BASKET_DATE, BUY_YN, LETTER, LETTER_POTION FROM BASKET B JOIN PRODUCT P ON B.PROD_NO = P.PROD_NO ORDER BY PROD_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String BasketNo = rs.getString("BASKET_NO");
			String ProdNo  = rs.getString("PROD_NO");
			String BasketDate = rs.getString("BASKET_DATE");
			String BuyYn = rs.getString("BUY_YN");
			String Letter = rs.getString("LETTER");
			String LetterPotion = rs.getString("LETTER_POTION");
			
			System.out.println("담은 상품의 장바구니번호, 상품번호, 담은날짜, 구매여부, 글자, 글자위치입니다.");
			System.out.println(BasketNo + " , " + ProdNo + " , " + BasketDate + " , " + BuyYn + " , " + Letter + " , " + LetterPotion);
		}
			conn.close();
	}
	
}
