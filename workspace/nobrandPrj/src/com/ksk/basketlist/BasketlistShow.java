package com.ksk.basketlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc. JdbcTemplate;
import com.ksk.orderProduct.OrderProductShow;
import com.nobrand.main.Main;

public class BasketlistShow {

	OrderProductShow ops = new OrderProductShow();
	
	
	public void showBasketlist() throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println();
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║              BASKET              ║ ");
		System.out.println("           ╚══════════════════════════════════╝");	
		System.out.println();
		String sql = "SELECT BL.BASKETLIST_NO , PROD_NAME , PRICE, LETTER, LETTER_POTION FROM BASKETLIST BL JOIN BASKET B ON BL.BASKET_NO = B.BASKET_NO JOIN PRODUCT P ON B.PROD_NO = P.PROD_NO WHERE MEMBER_NO = ? AND BUY_YN = 'N' ORDER BY BASKETLIST_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		ResultSet rs = pstmt.executeQuery();
		System.out.println(" BASKETLIST_NO    PROD_NAME        PRICE        LETTER        LETTER_P ");
		System.out.println(" ═══════════════════════════════════════════════════════════════");
			while(rs.next()) {
			String BasketlistNo = rs.getString("BASKETLIST_NO");
			String ProdName = rs.getString("PROD_NAME");
			String Price = rs.getString("PRICE");
			String letter = rs.getString("LETTER");
			String letterPot = rs.getString("LETTER_POTION");

			
			System.out.println(" " + BasketlistNo + "                  "  +  ProdName + "           " + Price + "         " + letter + "           " + letterPot);
			}
			
		conn.close();
		
		ops.productOrder();
		
		
	}


}
