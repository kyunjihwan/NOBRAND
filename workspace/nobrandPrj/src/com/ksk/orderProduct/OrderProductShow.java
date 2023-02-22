package com.ksk.orderProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;

public class OrderProductShow {

	public void productOrder() throws Exception {
		System.out.println("===== 주문하기 =====");
		String sql = "SELECT ORD_NO , BASKETLIST_NO , ORDPRICE_TOTAL , ORD_DATE , ORD_ADR , ORD_PH FROM ORDER_PRODUCT OP JOIN BASKETLIST BL ON BL.BASKETLIST_NO = OR.BASKETLIST_NO ORDER BY ORD_NO";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			String OrdNo = rs.getString("ORD_NO");
			String BasketlistNo = rs.getString("BASKETLIST_NO");
			String OrdpriceTotal = rs.getString("ORDPRICE_TOTAL");
			String OrdDate = rs.getString("ORD_DATE");
			String OrdAdr = rs.getString("ORD_ADR");
			String OrdPh = rs.getString("ORD_PH");
			
			System.out.println("주문할 상품의 주문번호, 총구매금액, 주문일시, 배송주소, 연락처입니다.");
			System.out.println(OrdNo + " , " + BasketlistNo + " , " + OrdpriceTotal + " , " + OrdDate + " , " + OrdAdr + " , " + OrdPh);
		}
		
			conn.close();
			
			System.out.println("해당 상품들을 결제하시겠습니까?");
			System.out.println("1. 결제하기");
			System.out.println("2. 돌아가기");
			
			int choiceNum = Main.SC.nextInt();
			
			if (choiceNum == 1) {
				//결제 메소드로 넘어가기
			}else if (choiceNum == 2) {
				System.out.println("홈으로 돌아갑니다.");
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
			
	}
	
}
