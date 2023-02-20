package com.ksk.orderProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderProductShow {

	public void showBasketlist(Connection conn) throws Exception {
		System.out.println("===== 주문 =====");
		String sql = "SELECT ORD_NO , BASKETLIST_NO , ORDPRICE_TOTAL , ORD_DATE , ORD_ADR , ORD_PH FROM ORDER_PRODUCT OP JOIN BASKETLIST BL ON BL.BASKETLIST_NO = OR.BASKETLIST_NO ORDER BY ORD_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
			String OrdNo = rs.getString("ORD_NO");
			String BasketlistNo = rs.getString("BASKETLIST_NO");
			String OrdpriceTotal = rs.getString("ORDPRICE_TOTAL");
			String OrdDate = rs.getString("ORD_DATE");
			String OrdAdr = rs.getString("ORD_ADR");
			String OrdPh = rs.getString("ORD_PH");
			
			System.out.println("주문할 상품의 주문번호, 총구매금액, 주문일시, 배송주소, 연락처입니다.");
			System.out.println(OrdNo + " , " + BasketlistNo + " , " + OrdpriceTotal + " , " + OrdDate + " , " + OrdAdr + " , " + OrdPh);
	
			conn.close();
	}
	
}
