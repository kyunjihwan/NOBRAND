package com.ljh.inquirycenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.nobrand.main.Main;
import com.sys.MemberData;

public class InquiryShow {
	
	private String sql;
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	private int result;
	private int no;
	private InquiryService is = new InquiryService();
	private InquiryInput ip = new InquiryInput();
	
	public void writeBoard () throws Exception {
		InquiryData data = ip.writeBoardInput();
		MemberData member = new MemberData();
		conn = JdbcTemplate.getConnection();
		sql = "INSERT INTO INQUIRYCENTER (INQ_NO, MEMBER_NO, INQ_TITLE, INQ_CONTENT, INQ_ENROLL_DATE) VALUES (SEQ_INQ_NO.NEXTVAL, ? , ? , ? , SYSDATE)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		pstmt.setString(2, data.getTitle());
		pstmt.setString(3, data.getContent());
		result = pstmt.executeUpdate();
		if(result == 1) {
			System.out.println("작성 완료");
		}else{
			System.out.println("작성 실패");
		}
		conn.close();
	}
	
	public void deleteBoard() throws Exception {
		showBoardList();
		InquiryData data = ip.deleteBoardInput(conn);
		conn = JdbcTemplate.getConnection();
		sql = "DELETE FROM INQUIRYCENTER WHERE INQ_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, data.getNo());
		result = pstmt.executeUpdate();
		if(result == 1) {
			System.out.println("삭제 완료");
		}else{
			System.out.println("삭제 실패");
		}
		conn.close();
	}

	public void updateBoard() throws Exception {
		showBoardList();
		//수정할 글 번호 입력 받기 
		while(true) {
			System.out.print("\n수정할 글의 번호를 입력하세요 : ");
			no = Main.SC.nextInt();
			conn = JdbcTemplate.getConnection();
			String sql = "SELECT INQ_NO FROM INQUIRYCENTER WHERE INQ_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				break;
			}else {
				System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
			}
			rs.close();
			conn.close();
		}
		showDetailBoard();
		//수정 데이터 입력 받기 
		System.out.println("\n수정할 제목을 입력하세요.(수정 사항 없으면 9) : ");
		String title = Main.SC.next();
		Main.SC.nextLine();
		if(title.equals("9")) {
				conn = JdbcTemplate.getConnection();
				sql = "SELECT INQ_TITLE FROM INQUIRYCENTER WHERE INQ_NO = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					title = rs.getString(1);
				}
				System.out.println("수정할 내용을 입력하세요. : ");
		}else {
			System.out.println("수정할 내용을 입력하세요. (수정 사항 없으면 9) : ");
		}
		String content = Main.SC.nextLine();

		conn = JdbcTemplate.getConnection();
		sql = "UPDATE INQUIRYCENTER SET INQ_TITLE = ?, INQ_CONTENT = ? WHERE INQ_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setInt(3, no);
		result = pstmt.executeUpdate();
		showDetailBoard();
		if(result == 1) {
			System.out.println("\n수정 완료");
		}else {
			System.out.println("수정 실패");
		}
		conn.close();
	}
	
	public void showBoardList() throws Exception {
		System.out.println("                   문의 게시판");
		System.out.println("");
		System.out.println("NO        TITLE          WRITER           DATE");
		System.out.println("--------------------------------------------------");
		sql = "SELECT INQ_NO, RPAD(INQ_TITLE,15), RPAD(MEMBER_NICK,10), TO_CHAR(INQ_ENROLL_DATE, 'YYYY-MM-DD') FROM INQUIRYCENTER I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO ORDER BY INQ_NO";
		conn = JdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4));
		}
		rs.close();
		conn.close();
	}
	
	public void requestDetail() throws Exception {
		while(true) {
			System.out.print("\n1.글 상세조회\n2.글 목록 조회\n9.고객센터로 돌아가기\n\n메뉴를 선택하세요 : ");
			no = Main.SC.nextInt();
			if(no == 1) {
				while(true) {
					System.out.print("\n상세조회 할 글의 번호를 입력하세요(9.뒤로가기) : ");
					no = Main.SC.nextInt();
					conn = JdbcTemplate.getConnection();
					sql = "SELECT INQ_NO FROM INQUIRYCENTER WHERE INQ_NO = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, no);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						showDetailBoard();
						break;
					}else if(no == 9) {
						break;
					}else {
						System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
					}
				}
			}else if (no == 2) {
				showBoardList();
			}else if(no == 9) {
				break;
			}else {
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
	}
	
	public void showAndRequestBoardList() throws Exception {
		showBoardList();
		requestDetail();
	}
	
	public void showDetailBoard() throws Exception {
		System.out.println("\nNO        TITLE          WRITER           DATE");
		System.out.println("--------------------------------------------------");
		conn = JdbcTemplate.getConnection();
		sql = "SELECT INQ_NO, RPAD(INQ_TITLE,15), RPAD(MEMBER_NICK,10), TO_CHAR(INQ_ENROLL_DATE, 'YYYY-MM-DD') FROM INQUIRYCENTER I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE INQ_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4));
		}
		rs.close();
		System.out.println("==================================================");
		sql = "SELECT INQ_CONTENT FROM INQUIRYCENTER WHERE INQ_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println("내용\n: " + rs.getString(1));
		}
		rs.close();
		System.out.println("==================================================");
		sql = "SELECT INQ_ANSWER FROM INQUIRYCENTER WHERE INQ_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
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
	
	public void answer_YN() {
//		sql = "SELECT INQ_ANSWER FROM INQUIRYCENTER WHERE INQ_NO = ?";
//		pstmt = conn.prepareStatement(sql);
//		rs = pstmt.executeQuery();
//		if(rs.next()) {
//			if(rs.wasNull())
//			sql = "INSERT INQ_"
//		}
		
	}
}
