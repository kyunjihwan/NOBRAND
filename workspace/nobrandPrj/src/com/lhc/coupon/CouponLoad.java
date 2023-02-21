package com.lhc.coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.jdbc.JdbcTemplate;

public class CouponLoad {
	
	//쿠폰 보여주기
	public void CouponShow() throws Exception {
		
		// 주문 시, 쿠폰 보여주기
		System.out.println("보유중인 쿠폰");
		
		String sql = "SELECT CP_NO, CP_NAME, CP_PERCENT, CP_PRICE, CP_YN, CP_DATE FROM COUPON WHERE CP_YN = 'N'";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String cpNo = rs.getString("CP_NO");
			String cpName = rs.getString("CP_NAME");
			String cpPercent = rs.getString("CP_PERCENT");
			String cpPrice = rs.getString("CP_PRICE");
			String cpYn = rs.getString("CP_YN");
			Timestamp cpDate = rs.getTimestamp("CP_DATE");
			
			System.out.println(cpNo +" | "+ cpName +" | : "+ cpPercent +"% | "+ cpPrice +"원 | "+ cpYn +" | "+ cpDate);
			
			
			
		}
		
		conn.close();
		
		CouponUse cu = new CouponUse();
		cu.couponUse();
		
	}

}
