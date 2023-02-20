package com.ljh.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.main.Main;

public class ReviewManager {
	
	private String sql;
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	private int result;
	private ReviewService rvs = new ReviewService();
	private int no;
	private String input;
	
	
	public void writeReview() throws Exception {
		
		System.out.print("제목(뒤로가기:9) : ");
		String title = Main.SC.next();
		if(title.equals("9")) {
			System.out.println("작성 취소");
			rvs.startService();
		}
		Main.SC.nextLine();
		
		System.out.print("내용(뒤로가기:9) : ");
		String content = Main.SC.nextLine();
		if(content.equals("9")) {
			System.out.println("작성 취소");
			rvs.startService();
		}
		
		System.out.print("별점 : ");
		String rating = Main.SC.nextLine();
		
		conn = JdbcTemplate.getConnection();
		sql = "INSERT INTO REVIEW (REVIEW_NO, PRODUCT_NO, MEMBER_NO, MANAGER_NO, RE_TITLE, RE_CONTENT, RE_ENROLL_DATE, STAR_COUNT) VALUES (SEQ_REVIEW_NO.NEXTVAL,'1','1','1',?,?,SYSDATE,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, rating);
		result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("작성 완료");
			rvs.startService();
		}else {
			System.out.println("작성 실패");
		}
		
		conn.close();
	}
	
	public void deleteReview() throws Exception {
		
		showReviewList();
		
		while(true) {
			System.out.print("\n삭제할 글의 번호를 선택하세요 : ");
			no = Main.SC.nextInt();
			
			conn = JdbcTemplate.getConnection();
			sql = "SELECT REVIEW_NO FROM REVIEW WHERE REVIEW_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				break;
			}else {
				System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
			}
			conn.close();
		}
		
		conn = JdbcTemplate.getConnection();
		sql = "DELETE FROM REVIEW WHERE REVIEW_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		result = pstmt.executeUpdate();
		
		showReviewList();
		
		if(result == 1) {
			System.out.println("\n삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
		conn.close();
	}
	
	public void updateReview() throws Exception {
		
		showReviewList();
		
		while(true) {
			System.out.print("\n수정할 글의 번호를 입력하세요 : ");
			no = Main.SC.nextInt();
			sql = "SELECT REVIEW_NO FROM REVIEW WHERE REVIEW_NO = ?";
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				break;
			}else {
				System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
			}
			conn.close();
		}
		
		showDetailReview();
		
		
		System.out.println("\n수정할 제목을 입력하세요.(수정사항 없으면 9) : ");
		String title = Main.SC.next();
		Main.SC.nextLine();
		
		if(title.equals("9")) {
			conn = JdbcTemplate.getConnection();
			sql = "SELECT RE_TITLE FROM REVIEW WHERE REVIEW_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				title = rs.getString(1);
			}
			System.out.println("수정할 내용을 입력하세요 : ");
		}else {
			System.out.println("수정할 내용을 입력하세요 (수정사항 없으면 9) : ");
		}
		String content = Main.SC.nextLine();
		
		sql = "UPDATE REVIEW SET RE_TITLE = ?, RE_CONTENT = ? WHERE REVIEW_NO = ?";
		conn = JdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setInt(3, no);
		result = pstmt.executeUpdate();
		
		showDetailReview();
		
		if(result == 1) {
			System.out.println("\n수정 완료");
		}else {
			System.out.println("수정 실패");
		}
		conn.close();
	}
	
	public void showAndRequestList() throws Exception {
		showReviewList();
		requestDatail();
	}
	
	public void showReviewList() throws Exception {
		System.out.println("                             리뷰 게시판");
		System.out.println("");
		System.out.println("NO          TITLE             RATING           WRITER             DATE");
		System.out.println("---------------------------------------------------------------------------");
		
		String sql = "SELECT REVIEW_NO, RPAD(RE_TITLE,23), RPAD(STAR_COUNT,10), RPAD(MEMBER_NICK,15), TO_CHAR(RE_ENROLL_DATE, 'YYYY-MM-DD') FROM REVIEW R JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO";
		conn = JdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4)+ "\t" +rs.getString(5));
		}
		rs.close();
		conn.close();
		
	}
	
	public void requestDatail() throws Exception {
		while(true) {
			System.out.print("\n1.글 상세조회\n2.글 목록 조회\n9.리뷰로 돌아가기\n\n메뉴를 선택하세요 : ");
			no = Main.SC.nextInt();
			if(no == 1) {
				while(true) {
					System.out.print("\n상세조회 할 글의 번호를 입력하세요(9.뒤로가기) : ");
					no = Main.SC.nextInt();
					conn = JdbcTemplate.getConnection();
					sql = "SELECT REVIEW_NO FROM REVIEW WHERE REVIEW_NO = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, no);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						showDetailReview();
						break;
					}else if(no == 9){
						break;
					}else {
						System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
					}
				}
			}else if(no == 2) {
				showReviewList();
			}else if(no == 9) {
				break;
			}else {
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
	}
	
	public void showDetailReview() throws Exception {
		
		System.out.println("\nNO          TITLE             RATING           WRITER             DATE");
		System.out.println("---------------------------------------------------------------------------");
		
		
		String sql = "SELECT REVIEW_NO, RPAD(RE_TITLE,23), RPAD(STAR_COUNT,10), RPAD(MEMBER_NICK,15), TO_CHAR(RE_ENROLL_DATE, 'YYYY-MM-DD') FROM REVIEW R JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO WHERE REVIEW_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4)+ "\t" +rs.getString(5));
		}
		rs.close();
		
		System.out.println("===========================================================================");
		
		sql = "SELECT RE_CONTENT FROM REVIEW WHERE REVIEW_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println("내용\n: " + rs.getString(1));
		}
		System.out.println("===========================================================================");
		rs.close();
		conn.close();
	}

}
