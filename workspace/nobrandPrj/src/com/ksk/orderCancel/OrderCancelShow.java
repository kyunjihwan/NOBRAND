package com.ksk.orderCancel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderCancelShow {

	public void showBasketlist(Connection conn) throws Exception {
		System.out.println("===== 주문취소 =====");
		String sql = "SELECT ORD_C_NO , ORD_NO , ORD_C_DATE FROM ORDER_CANCEL OC JOIN ORDER_PRODUCT OR ON OC.ORD_NO = OP.ORD_NO ORDER BY ORD_C_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
			String OrdCNo = rs.getString("ORD_C_NO");
			String OrdNo  = rs.getString("ORD_NO");
			String OrdCDate = rs.getString("ORD_C_DATE");
			
			System.out.println("취소할 주문의 주문취소번호, 주문번호, 주문취소일시입니다.");
			System.out.println(OrdCNo + " , " + OrdNo + " , " + OrdCDate);
	
			conn.close();
	}
	
}
