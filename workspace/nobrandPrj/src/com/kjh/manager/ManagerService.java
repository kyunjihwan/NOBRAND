package com.kjh.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.ljh.inquirycenter.InquiryShow;
import com.ljh.review.ReviewShow;
import com.nobrand.main.Main;

public class ManagerService {

	ManagerProduct mp = new ManagerProduct();
	ManagerKind mk = new ManagerKind();
	ManagerInquiry mi = new ManagerInquiry();
	ManagerEvent me = new ManagerEvent();
	InquiryShow is = new InquiryShow();
	ReviewShow rs = new ReviewShow();
	
	
	public boolean startManagerMenu() throws Exception {
		showManagerMenu();
		
		System.out.print("                        입력 >> ");
		String input = Main.SC.nextLine();
		
		if(input.equals("9")) {
			return true;
		}
		
		switch(input) {
		case "1" :
			while(true) {
				boolean isFinish = startMangerProduct();
				if(isFinish) {break;}
			}
			 break;
		case "2" : 
			while(true) {
				boolean isFinish = startManagerInquiry();
				if(isFinish) {break;}
			} break;
		case "3" : while(true) {
			boolean isFinish = startManagerEvent();
			if(isFinish) {break;}
		} break;
		default : System.out.println("잘못입력했습니다"); break;
		}
		
		
		return false;
		
	}
	
	public void showManagerMenu() {
		
		System.out.println("                         1. 상품 관련");
		System.out.println("                         2. 고객문의센터");
		System.out.println("                         3. 이벤트");
		System.out.println("                         9. 뒤로가기");
		System.out.println();
	}
	
	private boolean startManagerEvent() throws Exception {
		showManagerEvent();
		
		String input = Main.SC.nextLine();
		
		if(input.equals("9")) {
			return true;
		}
		
		switch(input) {
		case "1" : me.insertEvent(); break;
		case "2" : me.removeEvent(); break;
		}
		
		return false;
	}
	
	private void showManagerEvent() {
		System.out.println("1. 광고 등록");
		System.out.println("2. 광고 삭제");
		System.out.println("9. 뒤로가기");
	}
	
	private boolean startMangerProduct() throws Exception {
		showManagerProduct();
		
		System.out.print("                        입력 >> ");
		String input = Main.SC.nextLine();
		
		if(input.equals("9")) {
			return true;
		}
		
		switch(input) {
		case "1" : mp.insertProduct(); break;
		case "2" : mp.removeProduct(); break;
		case "3" : mk.insertKind(); break;
		case "4" : mk.removeKind(); break;
		case "5" : deleteReview();; break;
		}
		
		return false;
	}
	
	private void showManagerProduct() {
		System.out.println();
		System.out.println("                         1. 상품 등록");
		System.out.println("                         2. 상품 삭제");
		System.out.println("                         3. 카테고리 등록");
		System.out.println("                         4. 카테고리 삭제");
		System.out.println("                         5. 리뷰 삭제");
		System.out.println("                         9. 뒤로가기");
		System.out.println();
	}
	
	private boolean startManagerInquiry() throws Exception {
		showManagerInquiry();
		
		String input = Main.SC.nextLine();
		
		if(input.equals("9")) {
			return true;
		}
		
		switch(input) {
		case "1" : mi.showBoardList(); break;
		case "2" : mi.removeInquiry(); break;
		}
		
		return false;
	}
	
	private void showManagerInquiry() {
		System.out.println("====== 고객 답변 ======");
		System.out.println("1. 고객문의게시글 답변 등록");
		System.out.println("2. 고객문의게시글 삭제");
		System.out.println("9. 뒤로가기");
	}
	
	public void deleteReview() throws Exception {
		//글 목록 보여주기
		rs.showReviewList();
		//삭제할 글 정보 받아오기
		Connection conn = JdbcTemplate.getConnection();
		String no;
		while(true) {
			System.out.print("\n삭제할 글의 번호를 선택하세요(9. 뒤로가기) : ");
			no = Main.SC.nextLine();
			if("9".equals(no)) {
				break;
			}
			String sql = "SELECT REVIEW_NO FROM REVIEW WHERE REVIEW_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				break;
			}else {
				System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
			}
		}
		
		//삭제 여부 수정 
		String sql = "UPDATE REVIEW SET RE_DEL_YN = 'Y' WHERE REVIEW_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		//글 목록으로 돌아가기
		rs.showReviewList();
		
		if(result == 1) {
			System.out.println("\n삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
		conn.close();
	}
	
} // class
