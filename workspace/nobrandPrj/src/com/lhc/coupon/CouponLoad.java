package com.lhc.coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;

public class CouponLoad {

	CouponUse cu = new CouponUse();

	public void couponService() throws Exception {
		
		System.out.println("1. 쿠폰 목록 보기");
		System.out.println("2. 쿠폰 사용하기");
		System.out.println("3. 사용한 쿠폰 보기");
		System.out.println("0. 쿠폰 시스템 종료");
		System.out.print("사용할 쿠폰 서비스 : ");
		String sn = Main.SC.nextLine();
		
		switch(sn) {		
		case "1" : couponShow(); break;
		case "2" : cu.couponUse(); break;
		case "3" : cu.couponUsed(); break;
		case "0" : System.out.println("시스템으로 종료합니다"); break;
		default : System.out.println("잘못된 입력입니다.");
		}
		
	}
	
	
	// 쿠폰 보여주기
	public void couponShow() throws Exception {

		// 주문 시, 쿠폰 보여주기
		System.out.println("보유중인 쿠폰");

		String sql = "SELECT CP_NO, CP_NAME, CP_PERCENT, CP_PRICE, CP_YN, CP_DATE FROM COUPON WHERE CP_YN = 'N'";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String cpNo = rs.getString("CP_NO");
			String cpName = rs.getString("CP_NAME");
			String cpPercent = rs.getString("CP_PERCENT");
			String cpPrice = rs.getString("CP_PRICE");
			String cpYn = rs.getString("CP_YN");
			Timestamp cpDate = rs.getTimestamp("CP_DATE");

			if (cpPercent == null) {
				System.out.println(cpNo + " | " + cpName + " | " + cpPrice + "원 | " + cpYn + " | " + cpDate);
	
			} else if(cpPrice == null) { 
				System.out.println(cpNo + " | " + cpName + " | " + cpPercent + "% | " + cpYn + " | " + cpDate);
			}
			
		}
		
		System.out.print("쿠폰을 사용하시겠습니까?(y/n) : ");
		String s = Main.SC.nextLine();
		
			
		if(s.equals("y") || s.equals("Y")) {
			cu.couponUse();
		}else if(s.equals("n") || s.equals("N")) {
			System.out.println("사용을 종료합니다.");
		}else {
			System.out.println("잘못된 입력입니다.");
		}
		
	}

}
