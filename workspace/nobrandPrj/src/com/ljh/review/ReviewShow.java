package com.ljh.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.nobrand.main.Main;

public class ReviewShow {
	
	private Connection conn;
	private ReviewService rvs = new ReviewService();
	
	
	public void writeReview() throws Exception {
		
		System.out.print("\n제목(뒤로가기:9) : ");
		String title = Main.SC.nextLine();
		if(title.equals("9")) {
			System.out.println("작성 취소");
			rvs.startService();
		}
		
		System.out.print("내용(뒤로가기:9) : ");
		String content = Main.SC.nextLine();
		if(content.equals("9")) {
			System.out.println("작성 취소");
			rvs.startService();
		}
		
		System.out.print("별점 : ");
		String rating = Main.SC.nextLine();
		
		conn = JdbcTemplate.getConnection();
		String sql = "INSERT INTO REVIEW (REVIEW_NO, PRODUCT_NO, MEMBER_NO, MANAGER_NO, RE_TITLE, RE_CONTENT, RE_ENROLL_DATE, STAR_COUNT) VALUES (SEQ_REVIEW_NO.NEXTVAL,'1','1','1',?,?,SYSDATE,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, rating);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("작성 완료");
			rvs.startService();
		}else {
			System.out.println("작성 실패");
		}
		
		conn.close();
	}
	
	public void deleteReview() throws Exception {
		//글 목록 보여주기
		showReviewList();
		//삭제할 글 정보 받아오기
		String no;
		while(true) {
			System.out.print("\n삭제할 글의 번호를 선택하세요(9. 뒤로가기) : ");
			no = Main.SC.nextLine();
			if("9".equals(no)) {
				break;
			}
			conn = JdbcTemplate.getConnection();
			String sql = "SELECT REVIEW_NO FROM REVIEW WHERE REVIEW_NO = ? AND MEMBER_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setString(2, Main.loginMemberNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				break;
			}else {
				System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
			}
			conn.close();
		}
		
		//삭제 여부 수정 
		conn = JdbcTemplate.getConnection();
		String sql = "UPDATE REVIEW SET RE_DEL_YN = 'Y' WHERE REVIEW_NO = ? AND MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		pstmt.setString(2, Main.loginMemberNo);
		int result = pstmt.executeUpdate();
		//글 목록으로 돌아가기
		showReviewList();
		
		if(result == 1) {
			System.out.println("\n삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
		conn.close();
	}
	
	public void updateReview() throws Exception {
		//글 목록 보여주기
		showReviewList();
		//수정할 글 정보 받아오기
		String no;
		while(true) {
			System.out.print("\n수정할 글의 번호를 입력하세요 : ");
			no = Main.SC.nextLine();
			if("9".equals(no)) {
				return;
			}
			String sql = "SELECT REVIEW_NO FROM REVIEW WHERE REVIEW_NO = ? AND MEMBER_NO = ?";
			conn = JdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setString(2, Main.loginMemberNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				break;
			}else {
				System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
			}
			conn.close();
		}
		//수정할 글 상세조회
		showDetailReview(no);
		//수정할 정보 받아오기
		System.out.println("\n수정할 제목을 입력하세요.(수정사항 없으면 9) : ");
		String title = Main.SC.nextLine();
		
		if(title.equals("9")) {
			return;
		}
		//제목 수정사항 없으면 내용 수정으로 넘어가기 
		if(true) {
			conn = JdbcTemplate.getConnection();
			String sql = "SELECT RE_TITLE FROM REVIEW WHERE REVIEW_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				title = rs.getString(1);
			}
			System.out.println("수정할 내용을 입력하세요 : ");
		//제목 수정 후 내용 수정
		}else {
			System.out.println("수정할 내용을 입력하세요 (수정사항 없으면 9) : ");
		}
		String content = Main.SC.nextLine();
		
		String sql = "UPDATE REVIEW SET RE_TITLE = ?, RE_CONTENT = ? WHERE REVIEW_NO = ?";
		conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, no);
		int result = pstmt.executeUpdate();
		
		showDetailReview(no);
		
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
		System.out.println("\n                             리뷰 게시판");
		System.out.println("");
		System.out.println("NO          TITLE             RATING           WRITER             DATE");
		System.out.println("---------------------------------------------------------------------------");
		
		String sql = "SELECT REVIEW_NO, RPAD(RE_TITLE,25), RPAD(STAR_COUNT,10), RPAD(MEMBER_NICK,10), TO_CHAR(RE_ENROLL_DATE, 'YYYY-MM-DD') "
				+ "FROM REVIEW R JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO WHERE RE_DEL_YN = 'N' ORDER BY REVIEW_NO ";
		conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
	         System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4)+ "\t" +rs.getString(5));
	      }
		rs.close();
		conn.close();
	      
		
	}
	
	public void requestDatail() throws Exception {
		while(true) {
			System.out.println("\n                         1.글 상세조회");
			System.out.println("                         2.글 목록 조회");
			System.out.println("                         9.리뷰로 돌아가기");
			System.out.println("");
			System.out.print("                        입력 >> ");
			String input = Main.SC.nextLine();
			if("1".equals(input)) {
				String no;
				while(true) {
					System.out.println("\n                         상세조회 할 글의 번호를 입력하세요(9.뒤로가기)");
					System.out.print("                        입력 >> ");
					no = Main.SC.nextLine();
					conn = JdbcTemplate.getConnection();
					String sql = "SELECT REVIEW_NO FROM REVIEW WHERE REVIEW_NO = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, no);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						showDetailReview(no);
						break;
					}else if("9".equals(no)){
						break;
					}else {
						System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
					}
				}
			}else if("2".equals(input)) {
				showReviewList();
			}else if("9".equals(input)) {
				break;
			}else {
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
	}
	
	public void showDetailReview(String no) throws Exception {
		
		System.out.println("\nNO          TITLE             RATING           WRITER             DATE");
		System.out.println("---------------------------------------------------------------------------");
		
		
		String sql = "SELECT REVIEW_NO, RPAD(RE_TITLE,23), RPAD(STAR_COUNT,10), RPAD(MEMBER_NICK,15), TO_CHAR(RE_ENROLL_DATE, 'YYYY-MM-DD') FROM REVIEW R JOIN MEMBER M ON R.MEMBER_NO = M.MEMBER_NO WHERE REVIEW_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4)+ "\t" +rs.getString(5));
		}
		rs.close();
		
		System.out.println("===========================================================================");
		
		sql = "SELECT RE_CONTENT FROM REVIEW WHERE REVIEW_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println("내용\n: " + rs.getString(1));
		}
		System.out.println("===========================================================================");
		rs.close();
		conn.close();
	
		
	
	}
	
	

}
