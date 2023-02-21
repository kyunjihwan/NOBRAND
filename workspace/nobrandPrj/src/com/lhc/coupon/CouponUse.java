package com.lhc.coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;

public class CouponUse {
	
	//쿠폰 사용 후 사용 여부 y로  바꾸기
	public void couponUse() throws Exception {
		
		System.out.print("사용하실 쿠폰을 선택해주세요");
		String up = Main.SC.nextLine();
		
		if(up.equals(up)) {
			
			String sql = "UPDATE COUPON SET CP_NO = 'Y' WHERE CP_NO = ?";
			Connection conn = JdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			String cpNo = pstmt.setString(1, up);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("쿠폰 사용이 완료되었습니다.");
			}
			
		
		}
		
	}

}
