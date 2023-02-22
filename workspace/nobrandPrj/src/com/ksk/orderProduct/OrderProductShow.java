package com.ksk.orderProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kjh.Main;
import com.ksk.basketlist.BasketlistData;
import com.sys.MemberData;

public class OrderProductShow {

	public void productOrder(Connection conn) throws Exception {
		System.out.println("===== 주문하기 =====");
		String sql = "SELECT ORD_NO ,PROD_NAME, OP.BASKETLIST_NO , ORDPRICE_TOTAL , ORD_DATE , ORD_ADR , ORD_PH\r\n"
				+ "FROM ORDER_PRODUCT OP \r\n"
				+ "JOIN BASKETLIST BL ON BL.BASKETLIST_NO = OP.BASKETLIST_NO \r\n"
				+ "JOIN BASKET B ON  BL.BASKET_NO = B.BASKET_NO\r\n"
				+ "JOIN PRODUCT P ON P.PROD_NO = B.PROD_NO\r\n"
				+ "ORDER BY ORD_NO;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			String OrdNo = rs.getString("ORD_NO");
			String ProdName = rs.getString("PROD_NAME");
			String BasketlistNo = rs.getString("BASKETLIST_NO");
			String OrdpriceTotal = rs.getString("ORDPRICE_TOTAL");
			String OrdDate = rs.getString("ORD_DATE");
			String OrdAdr = rs.getString("ORD_ADR");
			String OrdPh = rs.getString("ORD_PH");
			
			System.out.println("주문할 상품의 주문번호, 상품이름, 총구매금액, 주문일시, 배송주소, 연락처입니다.");
			System.out.println(OrdNo + " , " + ProdName + " , " + BasketlistNo + " , " + OrdpriceTotal + " , " + OrdDate + " , " + OrdAdr + " , " + OrdPh);
		}
		
			conn.close();
			
			System.out.println("해당 상품들을 결제하시겠습니까?");
			System.out.println("1. 결제하기");
			System.out.println("2. 돌아가기");
			
			int choiceNum = Main.SC.nextInt();
			
			if (choiceNum == 1) {
				
				String sql2 = "INSERT INTO ORDER_PRODUCT(ORD_NO, BASKETLIST_NO, ORDPRICE_TOTAL, ORD_DATE, ORD_ADR, ORD_PH )"
						+ "VALUES (SEQ_ORD_NO.CURRVAL, SEQ_BASKETLIST_NO.CURRVAL, ?, SYSDATE, ?, ?)";
				PreparedStatement pstmt2 = conn.prepareStatement(sql);
				
				OrderProductData opd = new OrderProductData();
				pstmt2.setString(1, opd.getOrdpriceTotal());
				
				MemberData md = new MemberData();
				pstmt2.setString(2, md.getMemberAdr());
				pstmt2.setString(3, md.getMemberPh());
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				
				//결제 메소드로 넘어가기
				
			}else if (choiceNum == 2) {
				System.out.println("홈으로 돌아갑니다.");
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
			
	}
	
}
