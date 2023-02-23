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
		System.out.println();
		
			
			System.out.println("                        1. 주문하기");
			System.out.println("                        9. 뒤로가기");
			System.out.println();
			System.out.print("                       입력 >> ");
			
			String choiceNum = Main.SC.nextLine();
			
			if (choiceNum.equals("1")) {
				System.out.println();
				System.out.println("           ╔══════════════════════════════════╗");
				System.out.println("           ║               ORDER              ║ ");
				System.out.println("           ╚══════════════════════════════════╝");	
				System.out.println();
				System.out.println("                        주문할 장바구니 번호(9. 뒤로가기) ");
				System.out.print("                       입력 >> ");
				String basketListNum = Main.SC.nextLine();
				if(basketListNum.equals("9")) {
					return ;
				}
				String[] arr = basketListNum.split(",");
				
				System.out.println("                     1. 배송지주소 ");
				System.out.print("                       입력 >> ");
				String address = Main.SC.nextLine();
				System.out.println("                     2. 연락처  ");
				System.out.print("                       입력 >> ");
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
			}else if (choiceNum.equals("9")) {
				return ;
			}else {
				System.out.println("                        잘못 입력하셨습니다.");
			}

				updateBasket(conn);
				
				
				//결제 메소드로 이동
				Pay p = new Pay();
				p.payShow(conn);
				

				conn.close();
			
			
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
