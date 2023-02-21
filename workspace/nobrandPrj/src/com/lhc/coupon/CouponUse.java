package com.lhc.coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;

public class CouponUse {

	// 쿠폰 사용 후 사용 여부 y로 바꾸기
	public void couponUse() throws Exception {

		System.out.print("사용하실 쿠폰 : ");
		String up = Main.SC.nextLine();

		String sql = "UPDATE COUPON SET CP_YN = 'Y' WHERE CP_NO = ?";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, up);
		int result = pstmt.executeUpdate();

		if (result == 1) {
			System.out.println("쿠폰 사용이 완료되었습니다.");
			
			String sql2 = "INSERT INTO COUPONUSE(CP_USE_NO, MEMBER_NO, CP_NO,CP_YN) VALUES(SEQ_CP_NO.NEXTVAL, ?, ?, 'Y')";
			pstmt = conn.prepareStatement(sql2);
			//회원 번호 들어가야함
			pstmt.setString(1, up);
			pstmt.setString(2, up);
			
			pstmt.executeUpdate();
			

		}
	}
	
	//사용한 쿠폰 보여주기
	public void couponUsed() throws Exception {
		
		System.out.println("사용한 쿠폰");
		
		String sql = "SELECT CP_USE_NO, C.CP_NO,MEMBER_NO, CP_NAME, CP_PERCENT, CP_PRICE, CU.CP_YN, CP_DATE FROM COUPON C JOIN COUPONUSE CU ON C.CP_NO = CU.CP_NO WHERE CU.CP_YN = 'Y'";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()) {
			String cpUseNo = rs.getString("CP_USE_NO");
			String cpNo = rs.getString("CP_NO");
			String memberNo = rs.getString("MEMBER_NO");
			String cpName = rs.getString("CP_NAME");
			String cpPercent = rs.getString("CP_PERCENT");
			String cpPrice = rs.getString("CP_PRICE");
			String cpYn = rs.getString("CP_YN");
			Timestamp cpDate = rs.getTimestamp("CP_DATE"); 
			
			if(cpPercent == null) {
				System.out.println(cpUseNo + " | " + cpNo + " | " + memberNo + " | " + cpName + " | " + cpPrice + "원 | " + cpYn + " | " + cpDate);
			}
			
			else if(cpPrice == null) {
				System.out.println(cpUseNo + " | " + cpNo + " | " + memberNo + " | " + cpName + " | " + cpPercent + "% | " + cpYn + " | " + cpDate);
			}
		}
		
		
	}

}
