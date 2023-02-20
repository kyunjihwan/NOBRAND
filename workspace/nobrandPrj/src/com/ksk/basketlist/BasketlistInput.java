package com.ksk.basketlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BasketlistInput {

	public void inputBasketlist(Connection conn) throws Exception {
		System.out.println("===== 장바구니 내역 담기 =====");
		
		String sql = "INSERT INTO BASKETLIST( MEMBER_NO, BASKET_NO ) VALUES (로그인한회원번호 , SEQ_BASKETLIST_NO.CURVAL) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("장바구니내역 담기 완료");
		}else {
			System.out.println("장바구니내역 담기 실패");
		}
				
		conn.close();
	}
	
}
