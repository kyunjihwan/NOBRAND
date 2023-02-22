package com.ksk.orderProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.lhc.Pay;
import com.nobrand.main.Main;

public class OrderProductShow {

	public void productOrder() throws Exception {

		Connection conn = JdbcTemplate.getConnection();
		System.out.println("===== 주문하기 =====");
		
			
			System.out.println("해당 상품들을 주문하시겠습니까?");
			System.out.println("1. 주문하기");
			System.out.println("9. 뒤로가기");
			
			String choiceNum = Main.SC.nextLine();
			
			if (choiceNum.equals("1")) {
				System.out.println("주문할 장바구니 번호를 입력해주세요 : ");
				String basketListNum = Main.SC.nextLine();
				String[] arr = basketListNum.split(",");
				
				System.out.print("배송지주소 : ");
				String address = Main.SC.nextLine();
				System.out.print("연락처 : ");
				String phone = Main.SC.nextLine();
				for(int i = 0; i < arr.length; i++)
				{
					String price = searchPrice(arr[i], conn);
					String sql = "INSERT INTO ORDER_PRODUCT(ORD_NUM, BASKETLIST_NO, ORD_DATE, ORD_ADR, ORD_PH, ORDPRICE_TOTAL) VALUES (SEQ_ORD_NO.NEXTVAL,?, SYSDATE, ?, ?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, arr[i]);
					pstmt.setString(2, address);
					pstmt.setString(3, phone);
					pstmt.setString(4, price);
					
					pstmt.executeUpdate();
				}

				updateBasket(conn);
				
				conn.close();
				
				//결제 메소드로 이동
				Pay p = new Pay();
				p.payShow();
				
			}else if (choiceNum.equals("9")) {
				System.out.println("홈으로 돌아갑니다.");
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}

			
			
	}
	
	public void updateBasket(Connection conn) throws Exception {
		String sql = "UPDATE BASKET SET BUY_YN = 'Y' WHERE BUY_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.execute();
	}
	
	public String searchPrice(String basketNum, Connection conn) throws Exception {
		String sql = "SELECT PRICE FROM PRODUCT P JOIN BASKET B ON P.PROD_NO = B.PROD_NO JOIN BASKETLIST BL ON B.BASKET_NO = BL.BASKET_NO WHERE MEMBER_NO = ? AND BASKETLIST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		pstmt.setString(2, basketNum);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		String price = rs.getString("PRICE");
		
		return price;
		
	}
	
}
