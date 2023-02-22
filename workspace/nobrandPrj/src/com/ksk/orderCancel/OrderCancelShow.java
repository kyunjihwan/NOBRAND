package com.ksk.orderCancel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;

public class OrderCancelShow {

	public void CancelOrder() throws Exception {

		Connection conn = JdbcTemplate.getConnection();

		System.out.println();
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║            ORDER CANCL           ║ ");
		System.out.println("           ╚══════════════════════════════════╝");	
		System.out.println();
		System.out.println("                     주문을 취소하시겠습니까?");
		System.out.println("                        1. 취소하기");
		System.out.println("                        9. 돌아가기");
		System.out.println();
		System.out.print("                       입력 >> ");
		String choiceNum = Main.SC.nextLine();

		if (choiceNum.equals("1")) {

			System.out.print("주문취소할 주문번호 : ");
			System.out.println("                        주문취소번호");
			System.out.print("                       입력 >> ");
			String can = Main.SC.nextLine();

			// 주문취소하면 결제취소 테이블에 인서트하기
			String sql = "UPDATE ORDER_PRODUCT SET ORD_YN = 'Y' WHERE ORD_YN = 'N' AND ORD_NUM = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, can);
			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("                        주문취소가 완료되었습니다.");
			} else {
				System.out.println("                        주문취소가 정상적으로 이루어지지 않았습니다.");
			}

		} else if (choiceNum.equals("9")) {
			return ;
		} else {
		}

	}

}
