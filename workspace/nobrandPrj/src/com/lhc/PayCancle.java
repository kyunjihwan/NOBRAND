package com.lhc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;

public class PayCancle {

	String dp;

	//
	public void payCancle() {

		

	}

	public void payCancleReason() throws Exception {

		Connection conn = JdbcTemplate.getConnection();

		// 결제 취소 상품 보여주기
		

		// 사유를 고르는게 아니라 직접 작성은 어때?
		System.out.print("결제를 취소하시겠습니까?(1.y / 2.n) : ");
		int yn = Main.SC.nextInt();

		while (true) {

			if (yn == 1) {

				System.out.print("취소 사유를 작성해주세요 : ");
				String cancleReason = Main.SC.nextLine();
				String sql = "INSERT INTO PAY_CANCLE_REASON(CANCLE_REASON_NO, PAY_CANCLE_REASON) VALUES(SEQ_PAY_C_NO,?)";

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cancleReason);

				deletePay();
				
				break;

			}

			else if (yn == 2) {
				System.out.println("환불을 하지 않습니다.");
				break;
			}

			else {
				System.out.println("잘못된 입력입니다.");
				break;
			}
			
		}

	}

	public void deletePay() throws Exception {

		System.out.print("삭제할 상품번호 : ");
		dp = Main.SC.nextLine();

		String sql = "DELETE FROM PAY WHERE PAY_NO = ?";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dp);
		
		
	}

}