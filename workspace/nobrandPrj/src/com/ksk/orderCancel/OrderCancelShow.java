package com.ksk.orderCancel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;

public class OrderCancelShow {

	public void CancelOrder() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("===== 주문취소 =====");
		String sql = "SELECT ORD_C_NO , ORD_NO , ORD_C_DATE FROM ORDER_CANCEL OC JOIN ORDER_PRODUCT OR ON OC.ORD_NO = OP.ORD_NO ORDER BY ORD_C_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			String OrdCNo = rs.getString("ORD_C_NO");
			String OrdNo  = rs.getString("ORD_NO");
			String OrdCDate = rs.getString("ORD_C_DATE");
			
			System.out.println("취소할 주문의 주문취소번호, 주문번호, 주문취소일시입니다.");
			System.out.println(OrdCNo + " , " + OrdNo + " , " + OrdCDate);
		}
	
			conn.close();
			
			System.out.println("주문을 취소하시겠습니까?");
			System.out.println("1. 취소하기");
			System.out.println("2. 돌아가기");
			int choiceNum = Main.SC.nextInt();

			if (choiceNum == 1) {
				
				System.out.print("주문취소할 주문번호 : ");
				String can = Main.SC.nextLine();
				
				
				//주문취소하면 결제취소 테이블에 인서트하기
				Connection conn2 = JdbcTemplate.getConnection();
				String sql2 = "INSERT INTO ORDER_CANCEL(ORD_C_NO , ORD_NO , ORD_C_DATE) VALUES (SEQ_ORD_C_NO.NEXTVAL , ? , SYSDATE)";
				PreparedStatement pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setString(1, can);
				int result = pstmt.executeUpdate();
				
				if (result == 1) {
					System.out.println("주문취소가 완료되었습니다.");	
					}
				else {System.out.println("주문취소가 정상적으로 이루어지지 않았습니다.");}
				
			}else if (choiceNum == 2) {
				System.out.println("홈으로 돌아갑니다.");
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
			
	}
	
}
