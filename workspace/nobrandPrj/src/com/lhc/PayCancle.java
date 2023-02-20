package com.lhc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jdbc.JdbcTemplate;

public class PayCancle {

	public void payCancleService() throws Exception {

		Pay p = new Pay();
		
		// 결제 상품 보여주기
		p.payProduct();
		
		// 환불 상품 선택하기
		deletePay();
		
		
	}

	public void payCancleReason() throws Exception {

		Connection conn = JdbcTemplate.getConnection();


		// 사유를 고르는게 아니라 직접 작성은 어때?
		System.out.print("환불하시겠습니까?(1.y / 2.n) : ");
		int yn = Main.SC.nextInt();
		Main.SC.nextLine();

		while (true) {

			if (yn == 1) {

				System.out.print("취소 사유를 작성해주세요 : ");
				String cancleReason = Main.SC.nextLine();
				String sql = "INSERT INTO PAY_CANCEL_REASON(CANCEL_REASON_NO, PAY_CANCEL_REASON) VALUES(SEQ_CANCEL_REASON_NO.NEXTVAL,?)";

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cancleReason);
				int result = pstmt.executeUpdate();
				if(result ==1) {
					System.out.println("취소 사유가 작성되었습니다.");
				}else {
					System.out.println("취소 사유 작성 실패..");
				}
				
				break;
			}else if (yn == 2) {
				System.out.println("환불을 하지 않습니다.");
				break;
			}else {
				System.out.println("잘못된 입력입니다.");
				break;
			}

		}
		
		conn.close();

	}

	public void deletePay() throws Exception {

		System.out.print("삭제할 상품번호 : ");
		String dp = Main.SC.nextLine();

		payCancleReason();
		
		String sql = "DELETE FROM PAY WHERE PAY_NO = ?";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dp);
		int result = pstmt.executeUpdate();
		if(result == 1) {
			System.out.println("삭제 완료되었습니다.");
		}else {
			System.out.println("삭제 실패");
		}
		
		conn.close();
		
	}

}