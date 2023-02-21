package com.kjh.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;
import com.kjh.inquiry.InquiryData;
import com.ljh.inquirycenter.InquiryShow;

public class ManagerInquiry {

	ManagerInquiryInput mii = new ManagerInquiryInput();
	InquiryShow is = new InquiryShow();

	
	public void insertInquiryAns() throws Exception {
		
		is.showBoardList();
		
		Connection conn = JdbcTemplate.getConnection();
		
		InquiryData data = mii.insertInquiryAns();
		
		String sql = "UPDATE INQUIRYCENTER SET INQ_ANSWER = ?, MANAGER_NO = '1' WHERE INQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getInquiryContent());
		pstmt.setString(2, data.getInquiryNo());
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("답변 내용 : " + data.getInquiryContent());
			System.out.println("답변 완료");
		}else {
			System.out.println("답변 실패");
		}
		
		conn.close();
		
	}
	
	public void removeInquiry() throws Exception {
		
		is.showBoardList();
		
		Connection conn = JdbcTemplate.getConnection();
		
		String inqNum = mii.removeInquiryAns();
		
		String sql = "UPDATE INQUIRYCENTER SET INQ_DEL_YN = 'Y' WHERE INQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inqNum);
		int result = pstmt.executeUpdate();
		
		if(result == 1 ) {
			System.out.println("게시글 삭제 성공");
		}else {
			System.out.println("게시글 삭제 실패");
		}
		
		conn.close();
		
	}
	
	
}
