
package com.ksk.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.jdbc.JdbcTemplate;
import com.ksk.basketlist.BasketlistInput;
import com.nobrand.main.Main;

public class BasketInput {

	
	BasketlistInput bli = new BasketlistInput();
	
	public void inputBasket(String pno, Connection conn) throws Exception {
		
		
		System.out.println("===== 장바구니 담기 =====");
		
		System.out.print("글자 : ");
		String ltr = Main.SC.nextLine();
		System.out.print("글자위치 : ");
		String lpo = Main.SC.nextLine();
				
		String sql = "INSERT INTO BASKET(BASKET_NO, PROD_NO, BASKET_DATE, LETTER, LETTER_POTION) VALUES (SEQ_BASKET_NO.NEXTVAL, ?, SYSDATE, ?, ?) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pno);
		pstmt.setString(2, ltr);
		pstmt.setString(3, lpo);
		
		int result = pstmt.executeUpdate();
		
		
		bli.inputBasketlist(conn);
		
		if(result == 1) {
			System.out.println("장바구니 담기 완료");
		}else {
			System.out.println("장바구니 담기 실패");
		}
		conn.close();
		
	}
	
	
}
