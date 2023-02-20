package com.ksk.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.jdbc.JdbcTemplate;

public class BasketInput {

	
	
	public void inputBasket() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("===== 장바구니 담기 =====");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("상품 번호 : ");
		String pno = sc.nextLine();
		System.out.print("글자 : ");
		String ltr = sc.nextLine();
		System.out.print("글자위치 : ");
		String lpo = sc.nextLine();
				
		String sql = "INSERT INTO BASKET(BASKET_NO, PROD_NO, LETTER, LETTER_POTION) VALUES (SEQ_BAKSET_NO.NEXTVAL, ?, ?, ?) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pno);
		pstmt.setString(2, ltr);
		pstmt.setString(3, lpo);
		
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("장바구니 담기 완료");
		}else {
			System.out.println("장바구니 담기 실패");
		}
		
		conn.close();
	}
	
}
