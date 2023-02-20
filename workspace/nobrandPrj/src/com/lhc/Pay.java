package com.lhc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;

public class Pay {

	// 결제취소
	private static PayCancle PC = new PayCancle();

	// 결제정보
	public void payService() throws Exception {

		// pay sql문
		String sql = "SELECT PAY_NO, PROD_NAME, PRICE, PROD_CONTENT\r\n" + "FROM PRODUCT P\r\n"
				+ "JOIN BASKET B ON P.PROD_NO = B.PROD_NO\r\n" + "JOIN BASKETLIST BL ON B.BASKET_NO = bl.basket_no\r\n"
				+ "JOIN ORDER_PRODUCT OP ON OP.BASKETLIST_NO = BL.BASKETLIST_NO\r\n"
				+ "JOIN PAY P ON OP.ORD_NO = P.ORDER_NO";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		System.out.println("장바구니에 담긴 상품");

		while (rs.next()) {
			String ordNo = rs.getString("PAY_NO");
			String prodName = rs.getString("PROD_NAME");
			String price = rs.getString("PRICE");
			String prodContent = rs.getString("PROD_CONTENT");

			System.out.println(ordNo + "|" + prodName + "|" + price + "|" + prodContent);
		}

		// 결제수단
		pay();

		// 결제 상품 내역
		payProduct();

		conn.close();
	}

	// 결제
	public void pay() throws Exception {

		Connection conn = JdbcTemplate.getConnection();

		payType();

		int pt = Main.SC.nextInt();

		while (true) {
			if (pt == 1) {
				System.out.println("신용카드로 결제합니다.");
				// insert문을 사용해도 db에 저장이 안됨
				String sql = "INSERT INTO PAY(PAY_TYPE_NO,PAY_YN) VALUES(?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "계좌이체");
				pstmt.setString(2, "Y");
				break;
			} else if (pt == 2) {
				System.out.println("계좌이체로 결제합니다.");
				String sql = "INSERT INTO PAY(PAY_TYPE_NO,PAY_YN) VALUES(?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "카드");
				pstmt.setString(2, "Y");
				break;
			} else {
				System.out.println("사용할 수 없는 결제 수단입니다.");
				String sql = "INSERT INTO PAY(PAY_YN) VALUES(?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "N");
				break;
			}

		}

	}

	public void payType() throws Exception {

		Connection conn = JdbcTemplate.getConnection();

		System.out.println("결제 수단을 선택하세요");
		String sql = "SELECT PAY_TYPE_NO, PAY_TYPE_NAME FROM PAY_TYPE";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String payTypeNo = rs.getString("PAY_TYPE_NO");
			String payTypeName = rs.getString("PAY_TYPE_NAME");

			System.out.println(payTypeNo + "|" + payTypeName);
		}

	}

	// 결제상품 나열
	public void payProduct() throws Exception {

		System.out.println("결제한 상품");
		String sql = "SELECT PAY_NO, PROD_NAME, PRICE, PROD_CONTENT\r\n" + "FROM PRODUCT P\r\n"
				+ "JOIN BASKET B ON P.PROD_NO = B.PROD_NO\r\n" + "JOIN BASKETLIST BL ON B.BASKET_NO = bl.basket_no\r\n"
				+ "JOIN ORDER_PRODUCT OP ON OP.BASKETLIST_NO = BL.BASKETLIST_NO\r\n"
				+ "JOIN PAY P ON OP.ORD_NO = P.ORDER_NO";
		
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
		}

		// 결제취소
		PC.payCancleReason();

	}

}