package com.lhc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.ksk.basketlist.BasketlistShow;

public class Pay {

	// 결제정보
	public void payService() throws Exception {

		PayCancle pc = new PayCancle();

		while (true) {
			System.out.println("사용하실 기능을 선택해주세요");
			System.out.println("1. 결제하기");
			System.out.println("2. 결제취소");
			// 전단계로 돌아가는 거
			// System.out.println("9. OO시스템으로 돌아가기");
			System.out.println("0. 시스템 종료");
			int i = Main.SC.nextInt();
			Main.SC.nextLine();
			switch (i) {
			case 1:
				payShow();
				break;
			case 2:
				pc.payCancleService();
				break;
			case 0:
				System.out.println("시스템을 종료합니다.");
				return;
			default:
				System.out.println("입력을 잘못하였습니다. 다시 입력해주세요");
				continue;
			}

		}

	}

	// 주문한 상품 가져오기(완) - basketlistshow에서 가져오기
	public void payShow() throws Exception {

		System.out.print("결제하시겠습니까?(y/n) : ");
		String yn = Main.SC.nextLine();

		if (yn.equals("y") || yn.equals("Y")) {
			pay();
		} else if (yn.equals("n") || yn.equals("N")) {
			System.out.println("시스템 종료");
		} else {
			System.out.println("잘못된 입력입니다.");
		}

	}

	// 결제
	public void pay() throws Exception {

		// 결제수단
		payType();

	}

	public void payType() throws Exception {

		Connection conn = JdbcTemplate.getConnection();

		System.out.println("결제 수단을 선택하세요");
		String sql = "SELECT PAY_TYPE_NO, PAY_TYPE_NAME FROM PAY_TYPE";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String ptno = rs.getString("PAY_TYPE_NO");
			String ptname = rs.getString("PAY_TYPE_NAME");

			System.out.println(ptno + " | " + ptname);
		}

		String pt = Main.SC.nextLine();

		while (true) {

			if (pt.equals("1")) {
				System.out.println("계좌이체로 결제합니다.");
				String sql2 = "INSERT INTO PAY(PAY_NO, ORDER_NO, PAY_TYPE_NO, PAY_YN, PAY_DATE, PAY_COUNT_NO) VALUES (SEQ_PAY_NO.NEXTVAL, SEQ_ORD_NO.NEXTVAL, ?, 'Y', SYSDATE,'1')";
				Connection conn2 = JdbcTemplate.getConnection();
				PreparedStatement pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setString(1, pt);

				int result = pstmt2.executeUpdate();

				if (result == 1) {
					System.out.println("결제데이터 저장 완료");
				} else {
					System.out.println("결제 실패");
				}
				break;
				
			} else if (pt.equals("2")) {
				System.out.println("카드로 결제합니다.");
				String sql2 = "INSERT INTO PAY(PAY_NO, ORDER_NO, PAY_TYPE_NO, PAY_YN, PAY_DATE, PAY_COUNT_NO) VALUES (SEQ_PAY_NO.NEXTVAL, SEQ_ORD_NO.NEXTVAL, ?, 'Y', SYSDATE,'1')";
				Connection conn2 = JdbcTemplate.getConnection();
				PreparedStatement pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setString(1, pt);

				int result = pstmt2.executeUpdate();

				if (result == 1) {
					System.out.println("결제데이터 저장 완료");
				} else {
					System.out.println("결제 실패");
				}
				break;
				
			} else if(pt.equals("3")) {
				System.out.println("카카오페이로 결제합니다.");
				String sql2 = "INSERT INTO PAY(PAY_NO, ORDER_NO, PAY_TYPE_NO, PAY_YN, PAY_DATE, PAY_COUNT_NO) VALUES (SEQ_PAY_NO.NEXTVAL, SEQ_ORD_NO.NEXTVAL, ?, 'Y', SYSDATE,'1')";
				Connection conn2 = JdbcTemplate.getConnection();
				PreparedStatement pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setString(1, pt);

				int result = pstmt2.executeUpdate();

				if (result == 1) {
					System.out.println("결제데이터 저장 완료");
				} else {
					System.out.println("결제 실패");
				}
				break;
			}else if(pt.equals("4")) {
				System.out.println("비트코인으로 결제합니다.");
				String sql2 = "INSERT INTO PAY(PAY_NO, ORDER_NO, PAY_TYPE_NO, PAY_YN, PAY_DATE, PAY_COUNT_NO) VALUES (SEQ_PAY_NO.NEXTVAL, SEQ_ORD_NO.NEXTVAL, ?, 'Y', SYSDATE,'1')";
				Connection conn2 = JdbcTemplate.getConnection();
				PreparedStatement pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setString(1, pt);

				int result = pstmt2.executeUpdate();

				if (result == 1) {
					System.out.println("결제데이터 저장 완료");
				} else {
					System.out.println("결제 실패");
				}
				break;
			}
			
			else {
				System.out.println("사용할 수 없는 결제 수단입니다.");
				continue;
			}
		}
	}

	// 수정해야함
	// 결제상품 나열
	public void payProduct() throws Exception {

		String sql = "SELECT PAY_NO, PROD_NAME, PRICE, COLOR_NAME, PROD_CONTENT FROM COLOR C JOIN PRODUCT P ON P.COLOR_NO = C.COLOR_NO JOIN BASKET B ON P.PROD_NO = B.PROD_NO JOIN BASKETLIST BL ON B.BASKET_NO = BL.BASKET_NO JOIN ORDER_PRODUCT OP ON OP.BASKETLIST_NO = BL.BASKETLIST_NO JOIN PAY P ON OP.ORD_NUM = P.ORDER_NO WHERE P.PAY_YN = 'Y'";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			String payNo = rs.getString("PAY_NO");
			String prodName = rs.getString("PROD_NAME");
			String price = rs.getString("PRICE");
			String colorName = rs.getString("COLOR_NAME");
			String prodContent = rs.getString("PROD_CONTENT");

			System.out.println(payNo + " | " + prodName + " | " + price + " | " + colorName + " | " + prodContent);

		}

	}

	// 상품 결제로 바꾸기
	public void payYn() throws Exception {

		String sql = "UPDATE PAY SET PAY_YN = 'Y' WHERE PAY_YN = 'N'";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		int result = pstmt.executeUpdate();

		if (result == 1) {

		}

	}

}
