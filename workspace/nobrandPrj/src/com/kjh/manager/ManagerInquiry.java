package com.kjh.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;
import com.kjh.inquiry.InquiryData;
import com.ljh.inquirycenter.InquiryShow;

public class ManagerInquiry {

	ManagerInquiryInput mii = new ManagerInquiryInput();
	InquiryShow is = new InquiryShow();

	
	public void insertInquiryAns() throws Exception {
		
		
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
	
	public void showBoardList() throws Exception {
		System.out.println("                   문의 게시판");
		System.out.println("");
		System.out.println("NO        TITLE          WRITER           DATE");
		System.out.println("--------------------------------------------------");
		String sql = "SELECT INQ_NO, RPAD(INQ_TITLE,15), RPAD(MEMBER_NICK,10), TO_CHAR(INQ_ENROLL_DATE, 'YYYY-MM-DD') "
				+ "FROM INQUIRYCENTER I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE INQ_DEL_YN = 'N' ORDER BY INQ_NO";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4));
		}
		requestDetail(conn);
		conn.close();
	}
	
	public void requestDetail(Connection conn) throws Exception {
		while(true) {
			System.out.print("\n1.글 상세조회\n2.글 목록 조회\n9.뒤로가기\n\n메뉴를 선택하세요 : ");
			String no = Main.SC.nextLine();
			if(no.equals("1")) {
				while(true) {
					System.out.print("\n상세조회 할 글의 번호를 입력하세요(9.뒤로가기) : ");
					no = Main.SC.nextLine();
					conn = JdbcTemplate.getConnection();
					String sql = "SELECT INQ_NO FROM INQUIRYCENTER WHERE INQ_NO = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, no);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						showDetailBoard(no);
						break;
					}else if(no.equals("9")) {
						break;
					}else {
						System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
					}
				}
			}else if (no.equals("2")) {
				is.showBoardList();
			}else if(no.equals("9")) {
				break;
			}else {
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
	}
	
	public void showDetailBoard(String no) throws Exception {
		System.out.println("\nNO        TITLE          WRITER           DATE");
		System.out.println("--------------------------------------------------");
		Connection conn = JdbcTemplate.getConnection();
		String sql = "SELECT INQ_NO, RPAD(INQ_TITLE,15), RPAD(MEMBER_NICK,10), TO_CHAR(INQ_ENROLL_DATE, 'YYYY-MM-DD') FROM INQUIRYCENTER I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE INQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4));
		}
		rs.close();
		System.out.println("==================================================");
		sql = "SELECT INQ_CONTENT FROM INQUIRYCENTER WHERE INQ_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println("내용\n: " + rs.getString(1));
		}
		rs.close();
		System.out.println("==================================================");
		sql = "SELECT INQ_ANSWER FROM INQUIRYCENTER WHERE INQ_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			String inqAnswer = rs.getString(1);
			if(rs.wasNull()) {
				System.out.println("답변\n: 문의 답변 등록 중...");
//				showAndRequestBoardList();
			}else {
				System.out.println("답변\n: " + inqAnswer);
//				showAndRequestBoardList();
			}
		}
		System.out.println("==================================================");
		
		
		
		rs.close();
		conn.close();
	}
	
	
}
