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

	
	public void insertInquiryAns(String no) throws Exception {
		
		
		Connection conn = JdbcTemplate.getConnection();
		
		InquiryData data = mii.insertInquiryAns();
		
		String sql = "UPDATE INQUIRYCENTER SET INQ_ANSWER = ?, MANAGER_NO = '1' WHERE INQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getInquiryContent());
		pstmt.setString(2, no);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println();
			System.out.println("                        답변 내용 : " + data.getInquiryContent());
			
			System.out.println("                          *답변 완료*");
		}else {
			System.out.println("                           답변 실패");
		}
		
		conn.close();
		
	}
	
	
	
	public void removeInquiry() throws Exception {
		
		is.showBoardList();
		
		Connection conn = JdbcTemplate.getConnection();
		 
		String inqNum = mii.removeInquiryAns();
		
		if(inqNum.equals("9")) {
			return ;
		}
		
		String sql = "UPDATE INQUIRYCENTER SET INQ_DEL_YN = 'Y' WHERE INQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inqNum);
		int result = pstmt.executeUpdate();
		
		if(result == 1 ) {
			System.out.println("                       게시글 삭제 성공");
		}else {
			System.out.println("                       게시글 삭제 실패");
		}
		
		conn.close();
		
	}
	
	public void showBoardList() throws Exception {
		System.out.println("");
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║           SERVICE BOARD          ║ ");
		System.out.println("           ╚══════════════════════════════════╝");
		System.out.println("");
		System.out.println("     NO        TITLE          WRITER           DATE");
		System.out.println("     ═════════════════════════════════════════════════");
		String sql = "SELECT INQ_NO, RPAD(INQ_TITLE,15), RPAD(MEMBER_NICK,10), TO_CHAR(INQ_ENROLL_DATE, 'YYYY-MM-DD') "
				+ "FROM INQUIRYCENTER I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE INQ_DEL_YN = 'N' ORDER BY INQ_NO";
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println("     " + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4));
		}
		requestDetail(conn);
		conn.close();
	}
	
	public void requestDetail(Connection conn) throws Exception {
		while(true) {
			System.out.println("\n                         1.글 상세조회");
			System.out.println("                         2.글 목록 조회");
			System.out.println("                         9.뒤로 가기");
			System.out.println("");
			System.out.print("                        입력 >> ");
			String no = Main.SC.nextLine();
			if(no.equals("1")) {
				while(true) {
					System.out.println("\n               상세조회 할 글의 번호를 입력하세요(9.뒤로가기)");
					System.out.print("                        입력 >> ");
					no = Main.SC.nextLine();
					conn = JdbcTemplate.getConnection();
					String sql = "SELECT INQ_NO FROM INQUIRYCENTER WHERE INQ_NO = ? AND INQ_DEL_YN = 'N'";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, no);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						showDetailBoard(no);
						break;
					}else if(no.equals("9")) {
						break;
					}else {
						System.out.println("         해당하는 글이 없습니다. 다시 입력해주세요.");
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
		String sql;
		System.out.println("\n     NO        TITLE          WRITER           DATE");
		System.out.println("     ───────────────────────────────────────────────────");
		Connection conn = JdbcTemplate.getConnection();
		sql = "SELECT INQ_NO, RPAD(INQ_TITLE,15), RPAD(MEMBER_NICK,10), TO_CHAR(INQ_ENROLL_DATE, 'YYYY-MM-DD') FROM INQUIRYCENTER I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE INQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			System.out.println("     " + rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
					+ rs.getString(4));
		}
		rs.close();
		System.out.println("     ═══════════════════════════════════════════════════");
		sql = "SELECT INQ_CONTENT FROM INQUIRYCENTER WHERE INQ_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			System.out.println("     내용\n     : " + rs.getString(1));
		}
		rs.close();
		System.out.println("     ═══════════════════════════════════════════════════");
		sql = "SELECT INQ_ANSWER FROM INQUIRYCENTER WHERE INQ_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			String inqAnswer = rs.getString(1);
			if (rs.wasNull()) {
				System.out.println("     답변\n     : 문의 답변 등록 중...");
//				showAndRequestBoardList();
			} else {
				System.out.println("     답변\n     : " + inqAnswer);
//				showAndRequestBoardList();
			}
		}
		System.out.println("     ═══════════════════════════════════════════════════");
		rs.close();
		System.out.println("                        1. 답변 달기");
		System.out.println("                        9. 뒤로가기");
		System.out.println();
		System.out.print("                       입력 >> ");
		
		String choice = Main.SC.nextLine();
		if(choice.equals("9")) {
			return ;
		}else if(choice.equals("1")) {
			insertInquiryAns(no);
		}
		
		conn.close();
	}
	
	
}
