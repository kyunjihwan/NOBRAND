package com.ljh.inquirycenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.nobrand.main.Main;
import com.sys.MemberData;

public class InquiryShow {
	
	private Connection conn;
	private InquiryService is = new InquiryService();
	private InquiryInput ip = new InquiryInput();
	
	public void writeBoard () throws Exception {
		InquiryData data = ip.writeBoardInput();
		conn = JdbcTemplate.getConnection();
		String sql = "INSERT INTO INQUIRYCENTER (INQ_NO, MEMBER_NO, INQ_TITLE, INQ_CONTENT, INQ_ENROLL_DATE) VALUES (SEQ_INQ_NO.NEXTVAL, ? , ? , ? , SYSDATE)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Main.loginMemberNo);
		pstmt.setString(2, data.getTitle());
		pstmt.setString(3, data.getContent());
		int result = pstmt.executeUpdate();
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
		String sql = "UPDATE INQUIRYCENTER SET INQ_DEL_YN = 'Y' WHERE INQ_NO = ? AND MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, data.getNo());
		pstmt.setString(2, Main.loginMemberNo);
		int result = pstmt.executeUpdate();
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
		String no;
		while(true) {
			System.out.print("\n수정할 글의 번호를 입력하세요(9. 뒤로가기) : ");
			no = Main.SC.nextLine();
			if("9".equals(no)) {
				return ;
			}
			conn = JdbcTemplate.getConnection();
			String sql = "SELECT INQ_NO FROM INQUIRYCENTER WHERE INQ_NO = ? AND MEMBER_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setString(2, Main.loginMemberNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				break;
			}else {
				System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
			}
			rs.close();
			conn.close();
		}
		showDetailBoard(no);
		//수정 데이터 입력 받기 
		System.out.println("\n수정할 제목을 입력하세요.(수정 사항 없으면 9) : ");
		String title = Main.SC.next();
		Main.SC.nextLine();
		//제목 수정 사항 없으면 내용 수정으로 넘어 가기
		if(title.equals("9")) {
				conn = JdbcTemplate.getConnection();
				String sql = "SELECT INQ_TITLE FROM INQUIRYCENTER WHERE INQ_NO = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,no);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					title = rs.getString(1);
				}
				System.out.println("수정할 내용을 입력하세요. : ");
		//제목 수정 후 내용 수정		
		}else {
			System.out.println("수정할 내용을 입력하세요. (수정 사항 없으면 9) : ");
		}
		String content = Main.SC.nextLine();

		conn = JdbcTemplate.getConnection();
		String sql = "UPDATE INQUIRYCENTER SET INQ_TITLE = ?, INQ_CONTENT = ? WHERE INQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, no);
		int result = pstmt.executeUpdate();
		showDetailBoard(no);
		if(result == 1) {
			System.out.println("\n수정 완료");
		}else {
			System.out.println("수정 실패");
		}
		conn.close();
	}
	
	public void showBoardList() throws Exception {
		System.out.println("");
		System.out.println("                        문의 게시판");
		System.out.println("");
		System.out.println("NO        TITLE          WRITER           DATE");
		System.out.println("--------------------------------------------------");
		String sql = "SELECT INQ_NO, RPAD(INQ_TITLE,15), RPAD(MEMBER_NICK,10), TO_CHAR(INQ_ENROLL_DATE, 'YYYY-MM-DD') "
				+ "FROM INQUIRYCENTER I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE INQ_DEL_YN = 'N' ORDER BY INQ_NO";
		conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getString(4));
		}
		rs.close();
		conn.close();
	}
	
	public void requestDetail() throws Exception {
		while(true) {
			System.out.println("\n                         1.글 상세조회");
			System.out.println("                         2.글 목록 조회");
			System.out.println("                         9.고객센터로 돌아가기");
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
					String sql = "SELECT INQ_NO FROM INQUIRYCENTER WHERE INQ_NO = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, no);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						showDetailBoard(no);
						break;
					}else if("9".equals(no)) {
						break;
					}else {
						System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
					}
				}
			}else if ("2".equals(input)) {
				showBoardList();
			}else if("9".equals(input)) {
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
	
	public void showDetailBoard(String no) throws Exception {
		String sql;
		System.out.println("\nNO        TITLE          WRITER           DATE");
		System.out.println("--------------------------------------------------");
		conn = JdbcTemplate.getConnection();
		sql = "SELECT INQ_NO, RPAD(INQ_TITLE,15), RPAD(MEMBER_NICK,10), TO_CHAR(INQ_ENROLL_DATE, 'YYYY-MM-DD') FROM INQUIRYCENTER I JOIN MEMBER M ON I.MEMBER_NO = M.MEMBER_NO WHERE INQ_NO = ?";
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
