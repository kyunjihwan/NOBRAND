package com.ksk.orderProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.ksk.basketlist.BasketlistData;
import com.lhc.Pay;
import com.nobrand.main.Main;
import com.sys.MemberData;

public class OrderProductShow {

	public void productOrder() throws Exception {
		Connection conn = JdbcTemplate.getConnection();
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
				System.out.println("결제할 장바구니 번호를 입력해주세요 : ");
				Connection conn2 = JdbcTemplate.getConnection();
				String basketListNum = Main.SC.nextLine();
				String[] arr = basketListNum.split(",");
				for(int i = 0; i < arr.length; i++)
				{
					String sql2 = "INSERT INTO ORDER_PRODUCT(BASKETLIST_NO) VALUES (?)";
					PreparedStatement pstmt2 = conn2.prepareStatement(sql2);
					pstmt2.setString(1, arr[i]);
					ResultSet rs2 = pstmt2.executeQuery();
				}
				System.out.print("배송지주소 : ");
				String address = Main.SC.nextLine();
				System.out.print("연락처 : ");
				String phone = Main.SC.nextLine();
				
				conn.close();

				Connection conn3 = JdbcTemplate.getConnection();
				String sql3 = "UPDATE BASKET SET BUY_YN = 'N' WHERE BUY_YN = 'Y'";
				PreparedStatement pstmt3 = conn3.prepareStatement(sql3);
				pstmt3.execute();
				
				conn.close();
				
				//결제 메소드로 이동
				Pay p = new Pay();
				p.payShow();
				
			}else if (choiceNum == 2) {
				System.out.println("홈으로 돌아갑니다.");
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}

			
			
	}
	
}
