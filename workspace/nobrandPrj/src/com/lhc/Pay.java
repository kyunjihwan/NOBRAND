package com.lhc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;

public class Pay {

	// 결제정보
	public void payService() throws Exception {

		PayCancle pc = new PayCancle();

		System.out.println("사용하실 기능을 선택해주세요");
		System.out.println("1. 결제상품목록");
		System.out.println("2. 상품결제");
		System.out.println("3. 결제취소");
		// System.out.println("*. 주문시스템으로 돌아가기");
		System.out.println("0. 시스템 종료");
		int i = Main.SC.nextInt();
		Main.SC.nextLine();
		while (true) {
			switch (i) {
			case 1:
				payShow();
				break;
			case 2:
				pay();
				break;
			case 3:
				pc.payCancleService();
				break;
			case 0:
				System.out.println("시스템을 종료합니다.");
				break;
			// case '*': System.out.println("주문시스템으로 돌아갑니다."); break;
			default:
				System.out.println("입력을 잘못하였습니다.");
			}
			break;
		}
	}

	// 결제 상품 보여주기
	public void payShow() throws Exception {
		
		System.out.println("주문한 상품");
		String sql = "SELECT P.PAY_NO, PROD_NAME, PRICE, COLOR_NAME, PROD_CONTENT, PAY_COUNT_NO FROM COLOR C JOIN PRODUCT P ON P.COLOR_NO = C.COLOR_NO JOIN BASKET B ON P.PROD_NO = B.PROD_NO JOIN BASKETLIST BL ON B.BASKET_NO = BL.BASKET_NO JOIN ORDER_PRODUCT OP ON OP.BASKETLIST_NO = BL.BASKETLIST_NO JOIN PAY P ON OP.ORD_NO = P.ORDER_NO WHERE PAY_YN = 'Y'";

		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String payNo = rs.getString("PAY_NO");
			String prodName = rs.getString("PROD_NAME");
			String price = rs.getString("PRICE");
			String prodContent = rs.getString("PROD_CONTENT");

			System.out.println(payNo + "|" + prodName + "|" + price + "|" + prodContent);
		
		}
		
		System.out.print("결제하시겠습니까?(y/n) : ");
		String yn = Main.SC.nextLine();
		
		if(yn.equals("y") || yn.equals("Y")) {
			pay();
		}
		else if(yn.equals("n") || yn.equals("N")) {
			System.out.println("시스템 종료");
		}else {
			System.out.println("잘못된 입력입니다.");
		}
		
	}

	// 결제
	public void pay() throws Exception {

		System.out.println("주문한 상품");

		// 결제수단
		payType();

		// 결제 상품 내역
		payProduct();

	}

	public void payType() throws Exception {

		Connection conn = JdbcTemplate.getConnection();

		System.out.println("결제 수단을 선택하세요");
		String sql2 = "SELECT PAY_TYPE_NO, PAY_TYPE_NAME FROM PAY_TYPE";

		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		ResultSet rs = pstmt2.executeQuery();

		while (rs.next()) {
			String ptno = rs.getString("PAY_TYPE_NO");
			String ptname = rs.getString("PAY_TYPE_NAME");

			System.out.println(ptno + " | " + ptname);
		}

		String pt = Main.SC.nextLine();

		if (pt.equals("1")) {
			System.out.println("계좌이체로 결제합니다.");
			payYn();
		} else if (pt.equals("2")) {
			System.out.println("카드로 결제합니다.");
			payYn();
		} else {
			System.out.println("사용할 수 없는 결제 수단입니다.");
		}

	}

	// 결제상품 나열
	public void payProduct() throws Exception {

	}

	// 상품 결제상태 바꾸기
	public void payYn() throws Exception {
		
		String sql = "UPDATE PAY SET PAY_YN = 'Y' WHERE PAY_NO = ?";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println("결제가 완료되었습니다.");
		}

	}

}