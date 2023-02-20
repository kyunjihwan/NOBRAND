package com.ljh.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.main.Main;

public class EventShow {
	
	private String sql;
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	private int result;
	private int no;
	private EventService es = new EventService();
	
	//홈페이지에 이벤트 보여주기
	public void homepageAd() {
		
	}

	//이벤트 추가
	public void insertEvent() throws Exception {
		System.out.print("이벤트명(뒤로가기:9) : ");
		String title = Main.SC.next();
		if(title.equals("9")) {
			System.out.println("작성 취소");
			es.startService();
		}
		Main.SC.nextLine();
		
		System.out.print("이벤트내용(뒤로가기:9) : ");
		String content = Main.SC.nextLine();
		if(content.equals("9")) {
			System.out.println("작성 취소");
			es.startService();
		}
		
		conn = JdbcTemplate.getConnection();
		sql = "INSERT INTO EVENT (E_NO, MANAGER_NO, E_TITLE, E_CONTENT) VALUES (SEQ_E_NO.NEXTVAL,'1',?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("등록완");
		}else {
			System.out.println("등록ㄴㄴ");
		}
		conn.close();
	}
	
	//이벤트 삭제
	public void deleteEvent() throws Exception {
		//이벤트 글 목록 보여주기
		showEventList();
		//이벤트 번호 없는 거 입력했을 때 
		while(true) {
			System.out.println("삭제할 이벤트 번호 : ");
			no = Main.SC.nextInt();
			conn = JdbcTemplate.getConnection();
			sql = "SELECT E_NO FROM EVENT WHERE E_NO = ?";
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
		//입력 받은 이벤트 번호 글 삭제 
		conn = JdbcTemplate.getConnection();
		sql = "DELETE FROM EVENT WHERE E_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		result = pstmt.executeUpdate();
		if(result == 1) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
		conn.close();
	}
	//이벤트 수정
	public void updateEvent() throws Exception {
		//이벤트 글 목록 보여주기
		showEventList();
		//이벤트 번호 없는 거 입력했을 때 
		while(true) {
			System.out.print("수정할 이벤트 번호 : ");
			no = Main.SC.nextInt();
			
			conn = JdbcTemplate.getConnection();
			sql = "SELECT E_NO FROM EVENT WHERE E_NO = ?";
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
		System.out.println("수정할 제목 (없으면 9) : ");
		String title = Main.SC.next();
		Main.SC.nextLine();
		//제목 수정 내용 없으면 내용 수정으로 넘어감 
		if(title.equals("9")) {
			conn = JdbcTemplate.getConnection();
			sql = "SELECT E_TITLE FROM EVENT WHERE E_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				title = rs.getString(1);
			}
			System.out.println("수정할 내용 : ");
		}else {
			System.out.println("수정할 내용 (없으면 9) : ");
		}
		String content = Main.SC.nextLine();
		
		conn = JdbcTemplate.getConnection();
		sql = "UPDATE EVENT SET E_TITLE = ?, E_CONTENT = ? WHERE E_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setInt(3, no);
		result = pstmt.executeUpdate();
		if(result == 1) {
			System.out.println("수정 완료");
		}else {
			System.out.println("수정 실패");
		}
		conn.close();
	}
	//이벤트 글 목록
	public void showEventList() throws Exception {
		conn = JdbcTemplate.getConnection();
		System.out.println("\nNO          TITLE                                    CONTENT           ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		sql = "SELECT E_NO, RPAD(E_TITLE,15), RPAD(E_CONTENT,200) FROM EVENT";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
		}
		rs.close();
		conn.close();
	}
}

