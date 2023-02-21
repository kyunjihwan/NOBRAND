package com.ksk.basketlist;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.nobrand.main.Main;

public class BasketlistInput {

	public void inputBasketlist(Connection conn) throws Exception {
		
		
		System.out.println("===== 장바구니 내역 담기 =====");
		
		String sql = "INSERT INTO BASKETLIST(BASKETLIST_NO, MEMBER_NO, BASKET_NO ) VALUES (SEQ_BASKETLIST_NO.NEXTVAL, ? , SEQ_BASKET_NO.CURRVAL)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("장바구니내역 담기 완료");
		}else {
			System.out.println("장바구니내역 담기 실패");
		}
				
	}
	
}
